import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */

/**
 * @author g1620520
 *
 */
public class SimpleDraw extends JFrame implements MouseListener, MouseMotionListener,ActionListener {
    private static final long serialVersionUID = 42L;

    int lastx=0,lasty=0,newx,newy;
    //ZoomAndPanePanel base=new DrawPanel();
    JPanel background=new JPanel();
    DrawPanel panel=new DrawPanel();
    DrawPanel currentpanel=new DrawPanel();
    JLayeredPane layerPane=new JLayeredPane();
    Color backgroundcolor=Color.white;
    int paperw,paperh;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int windoww = screenSize.width;
    int windowh = screenSize.height;
    Float[] widthlevel=new Float[10];
    int currenttoolnum=0;
    JFileChooser fileChooser=new JFileChooser();
    Color currentcolor=Color.black;
    JSliderPanel weigthframe=new JSliderPanel("weigth level",this);
    JLabel paperlabel = new JLabel("紙のサイズ");
    JPanel status = new JPanel();
    JLabel toollabel = new JLabel("tool:pen");
    JLabel toolwidth=new JLabel("width:3.0");
    JLabel toolcolor=new JLabel("pencolor");

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        newx=arg0.getX();
        newy=arg0.getY();
        if(currenttoolnum<=2){currentpanel.drawLine(lastx, lasty, newx, newy);}
        if(currenttoolnum==3){
            int rgb=panel.getRGB(newx,newy);
            currentcolor=new Color(rgb);
            System.out.println(rgb);
            currentpanel.setPenColor(currentcolor);
            toolcolor.setForeground(currentcolor);
            currenttoolnum=0;
            toollabel.setText("tool:pen");}
        else if(currenttoolnum==4){
            currentpanel.backet(newx,newy,panel.getRGB(newx,newy),currentcolor.getRGB());
            currenttoolnum=0;
            toollabel.setText("tool:pen");}
        lastx=newx;
        lasty=newy;
    }

    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        lastx=arg0.getX();
        lasty=arg0.getY();
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        if(currenttoolnum==3){currentcolor=new Color(panel.getRGB(newx,newy));
        currentpanel.setPenColor(currentcolor);
        toolcolor.setForeground(currentcolor);
            currenttoolnum=0;
        toollabel.setText("tool:pen");}
    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        if(currenttoolnum==3){currentcolor=new Color(panel.getRGB(newx,newy));
            currentpanel.setPenColor(currentcolor);
            toolcolor.setForeground(currentcolor);
            currenttoolnum=0;
            toollabel.setText("tool:pen");}
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        if(currenttoolnum==3){currentcolor=new Color(panel.getRGB(newx,newy));
            currentpanel.setPenColor(currentcolor);
            toolcolor.setForeground(currentcolor);
            currenttoolnum=0;
            toollabel.setText("tool:pen");}

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private void AddItemToMenu(JMenu menu,String itemlabel,String command){
        JMenuItem item = new JMenuItem(itemlabel);
        item.addActionListener(this);
        item.setActionCommand(command);
        menu.add(item);
    }

    private JMenu AddItemWithSubitemToMenu(JMenu menu,String itemlabel,String command){
        JMenu item = new JMenu(itemlabel);
        menu.add(item);
        return item;
    }

    private void settoolmenu(JMenuBar menubar){
        JMenu toolmenu = new JMenu("tool" );
        JMenu penmenu=AddItemWithSubitemToMenu(toolmenu,"pen","tool_pen");
        JMenu erasermenu=AddItemWithSubitemToMenu(toolmenu,"eraser","tool_eraser");
        JMenu colormenu=AddItemWithSubitemToMenu(penmenu,"color","pen_color");
        setcolormenu(colormenu);
        JMenu penweigthmenu=AddItemWithSubitemToMenu(penmenu,"weigth","pen_weigth");
        setweigthmenu(penweigthmenu,0);
        JMenu eraserweigthmenu=AddItemWithSubitemToMenu(erasermenu,"weigth","eraser_weigth");
        setweigthmenu(eraserweigthmenu,1);
        AddItemToMenu(toolmenu,"backet","tool_backet");
        menubar.add(toolmenu);
    }

    private void setcolormenu(JMenu colormenu){
        AddItemToMenu(colormenu,"red","color_255000000");
        AddItemToMenu(colormenu,"blue","color_000000255");
        AddItemToMenu(colormenu,"green","color_000255000");
        AddItemToMenu(colormenu,"yellow","color_255255000");
        AddItemToMenu(colormenu,"black","color_000000000");
        AddItemToMenu(colormenu,"others","color_other");
        AddItemToMenu(colormenu,"spoit","color_spoit");
    }

    private void setweigthmenu(JMenu weigthmenu,int toolnum){
        AddItemToMenu(weigthmenu,"thin","weigth_"+toolnum+"_0.5");
        AddItemToMenu(weigthmenu,"standard","weigth_"+toolnum+"_3");
        AddItemToMenu(weigthmenu,"thick","weigth_"+toolnum+"_10");
        AddItemToMenu(weigthmenu,"others","weigth_"+toolnum+"_other");
    }

    private void setfilemenu(JMenuBar menubar){
        JMenu filemenu = new JMenu("File");

        AddItemToMenu(filemenu,"New","file_new");
        AddItemToMenu(filemenu,"Open","file_open");
        AddItemToMenu(filemenu,"Save","file_save");
        //JMenu sizemenu=AddItemWithSubitemToMenu(filemenu,"print_size","file_size");
        //setsizemenu(sizemenu);

        menubar.add(filemenu);
    }

   /* private void setsizemenu(JMenu sizemenu){
        AddItemToMenu(sizemenu,"A4縦","size_24803508_A4縦");
        AddItemToMenu(sizemenu,"A4横","size_35082480_A4横");
        AddItemToMenu(sizemenu,"B5縦","size_21503035_B5縦");
        AddItemToMenu(sizemenu,"B5横","size_30352150_B5横");
        AddItemToMenu(sizemenu,"others","size_others");
    }
*/

    private void setnewPanel(){
        currentpanel=panel;
        //currentpanel.setOpaque(false);
        JScrollPane scrollpane = new JScrollPane(panel);
        currentpanel.bufferImage=currentpanel.createBuffer(windoww,windowh,currentpanel.bufferImage);
        currentpanel.setBackground(Color.white);
        currentpanel.addMouseMotionListener(this);
        scrollpane.setBounds(windoww/2-windowh * 2480/7016 , 0 , windowh * 2480/3508 , windowh);
        layerPane.add(scrollpane);
        layerPane.setLayer(scrollpane,1);

    }



    private void init(){
        this.setTitle("SimpleDraw");
        this.setSize(windoww,windowh);

        JMenuBar menubar = new JMenuBar();
        this.settoolmenu(menubar);
        this.setfilemenu(menubar);

        setJMenuBar(menubar);

        widthlevel[0]=3.0f;
        widthlevel[1]=3.0f;
        paperw=2480;
        paperh=3508;




        //panel.setpaperSize(new Dimension(2480, 3508));

        JPanel back=new JPanel();
        back.setBackground(Color.gray);
        back.setBounds(0 , 0 , windoww+100, windowh+500);
        paperlabel.setText("紙のサイズ：" + "A4縦");
        back.add(paperlabel, BorderLayout.PAGE_END);

        layerPane.add(back);
        layerPane.setLayer(back , -2);


        JButton button = new JButton();

       /* button.setText("レイヤ : " + 50);
        button.setBounds(0 , 0 , 200 , 50);
        layerPane.add(button);
        layerPane.setLayer(button , -1);*/

        //BevelBorder border = new BevelBorder(BevelBorder.RAISED);
        //status.setBorder(border);
        //status.setBackground(new Color(200,200,200));
        //status.setBounds(10 , 10 , windoww/4 , windowh-200);
        //this.add(status,BorderLayout.PAGE_END);
        //setstatus();
        toollabel.setBounds(10 , windowh-210 , windoww/4 , 30);
        layerPane.add(toollabel);
        layerPane.setLayer(toollabel , 0);
        toolwidth.setBounds(10 , windowh-180 , windoww/4 , 30);
        layerPane.add(toolwidth);
        layerPane.setLayer(toolwidth , 0);
        toolcolor.setBounds(10 , windowh-150 , windoww/4 , 30);
        layerPane.add(toolcolor);
        layerPane.setLayer(toolcolor , 0);
        //layerPane.add(status,BorderLayout.PAGE_END);
        //layerPane.setLayer(status , 0);

        background.setBackground(Color.pink);
        background.setBounds(windoww/2-windowh * 2480/7016 , 0 , windowh * 2480/3508 , windowh);
        layerPane.add(background);
        layerPane.setLayer(background,0);
        setnewPanel();


      //background.add(layerPane);
        //JScrollPane scrollpane = new JScrollPane(layerPane);
        //JScrollBar vscrollbar=new JScrollBar(JScrollBar.VERTICAL);
        //scrollpane.setVerticalScrollBar(vscrollbar);

        //this.getContentPane().add(scrollpane);
        setContentPane(layerPane);
        //this.getContentPane().add(panel);

        //background.setSize(new Dimension(1296, 678));
        //background.setBackground(Color.blue);
        //panel.setBackground(new Color(0,0,0,0));

        //panel.setBackground(Color.pink);

        //base.add(background);
       // panel.setPreferredSize(new Dimension(200, 678));

        //this.getContentPane().add(panel);;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void colorset(String command) {
        toollabel.setText("tool:pen");
        if (command.equals("other")) {
            JColorChooser colorchooser = new JColorChooser();
            currentcolor = colorchooser.showDialog(this, "choose a color", Color.blue);
            currentpanel.setPenColor(currentcolor);

        } else if (command.equals("spoit")) {
            toollabel.setText("tool:spoit");
            currenttoolnum=3;
        } else{
            int R = Integer.parseInt(command.substring(0, 3));
            int G = Integer.parseInt(command.substring(3, 6));
            int B = Integer.parseInt(command.substring(6, 9));
            currentcolor = new Color(R, G, B);
            currentpanel.setPenColor(currentcolor);
        }
        toolcolor.setForeground(currentcolor);
    }

    private void callweigthslider(){
        weigthframe.setVisible(true);

    }

    public void valueofslider(float fps){
        currentpanel.setPenWidth(fps);
        toolwidth.setText("width:"+fps);
    }

    private void weigthset(String command,int toolnum){
        if(toolnum==0){
            currentpanel.setPenColor(currentcolor);
            toollabel.setText("tool:pen");
        }else if (toolnum==1){
            currenttoolnum=1;
            toollabel.setText("tool:eraser");
            toolcolor.setForeground(Color.gray);
            currentpanel.setPenWidth(widthlevel[currenttoolnum]);
            currentpanel.setPenColor(backgroundcolor);

           // panel.openFile(new File("../../../Desktop/a.png"));//..(java)/..(Documents)/Desktop/a.jpg
        }

        if (command.equals("other")) {
            this.callweigthslider();
        } else {
            widthlevel[toolnum] = Float.parseFloat(command);
            currentpanel.setPenWidth(widthlevel[toolnum]);
            toolwidth.setText("width:"+widthlevel[toolnum]);
        }
    }

    private void aboutfile(String command){
        if(command.equals("new")){
            currentpanel.newFile();
        }else if (command.equals("save")){
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentpanel.saveFile(fileChooser.getSelectedFile());
            }
        }else if (command.equals("open")){
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    /*panel.setBackground(Color.pink);
                    panel.addMouseMotionListener(this);
                    this.getContentPane().add(background);
                    panel.setPreferredSize(new Dimension(1296, 678));
                    background.add(panel);*/
                    currentpanel.setLayout(new BorderLayout());
                    //layer.setBackground(Color.green);
                    //layer.setPreferredSize(new Dimension(1296, 678));
                    //panel.add(layer,BorderLayout.EAST);
                    BufferedImage pictureImage;
                    File file2open =fileChooser.getSelectedFile();
                    this.openFile(file2open);
                    this.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (command.equals("size")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentpanel.saveFile(fileChooser.getSelectedFile());
            }
        }
    }

    public void openFile(File file2open)throws IOException{
        BufferedImage pictureImage;
        try {
            pictureImage = ImageIO.read(file2open);

            int width=pictureImage.getWidth();
            int height=pictureImage.getHeight();
            double scale=Double.max(1,Double.max(width/windoww,height/windowh));
            BufferedImage destImage=currentpanel.resizeImage(pictureImage,1/scale);
            //System.out.println("returned");
            currentpanel.openFile(destImage);
        } catch(Exception e){
            System.out.println("Error: reading file="+file2open.getName());
            return;
        }

    }



    private void toolset(String command){
        if (command.equals("backet")) {
            currenttoolnum=4;
            toollabel.setText("backet");
        }
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.substring(0,5).equals("color")){
            currenttoolnum=0;
            currentpanel.setPenWidth(widthlevel[currenttoolnum]);
            colorset(command.substring(6));
        }else if(command.substring(0,6).equals("weigth")){
            weigthset(command.substring(9), Integer.parseInt(command.substring(7,8)));

        }else if(command.substring(0,4).equals("file")){
            aboutfile(command.substring(5));
        }else if(command.substring(0,4).equals("tool")){
            toolset(command.substring(5));
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleDraw frame=new SimpleDraw();
        frame.init();
    }

}
