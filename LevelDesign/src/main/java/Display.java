import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;


public class Display extends JFrame implements MouseListener
{
	JFrame jf;
	MenuBar mb;
	JPanel p1, p2; // top panel, bottom panel
	JPanel pa, pb; // left panel, right panel on top panel
	JPanel prop, layerp, disp, itp; //property panel, layer panel, display panel, item panel
	Property property;
	MakeLevel makelevel;
	Item item;
	PopupDialog pop;
	Layer layer;
	static int pointx = 1;
	static int pointy = 1;
	static int count = 0;

	String targetjson="./json/level1.json";
	Level thislevel;


	public Display(String msg){
		this(msg,null);
	}

	private void setjf(String msg){
		jf = new JFrame(msg);
		jf.setVisible(true);
		jf.setSize(800,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setFoundation(){
		jf.add(p1, BorderLayout.CENTER);
		jf.add(itp, BorderLayout.SOUTH);

		p1.add(pa, BorderLayout.WEST);
		p1.add(disp, BorderLayout.CENTER);

		pa.add(prop);
		pa.add(layerp);
		pa.setPreferredSize(new Dimension(300,0));
	}

	private void initPanels(){
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		pa = new JPanel();
		pa.setLayout(new GridLayout(2,1));

		prop = new JPanel();
		prop.setBorder(BorderFactory.createEtchedBorder());

		layerp = new JPanel();
		layerp.setBorder(BorderFactory.createEtchedBorder());

		disp = new JPanel();
		disp.setBorder(BorderFactory.createEtchedBorder());

		itp = new JPanel();
		itp.setBorder(BorderFactory.createEtchedBorder());
		itp.setPreferredSize(new Dimension(400,200));
	}

	private void setprop(){
		prop.setLayout(null);
		property = new Property(thislevel);
		prop.add(property.pro);
		prop.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}

	private void setdisp(){
		pop = new PopupDialog(count);
		disp.setLayout(null);
		makelevel = new MakeLevel(thislevel, pop, pointx, pointy);
		disp.add(makelevel.map);
		disp.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		disp.addMouseListener(this);
	}

	private void setitp(){
		itp.setLayout(null);
		item = new Item(thislevel,makelevel.imageIcons);
		itp.add(item.items);
		itp.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}

	private void setlayerp(){
		layerp.setLayout(null);
		layer = new Layer(thislevel, makelevel.imageIcons, pointx, pointy);
		layerp.add(layer.layers);
		layerp.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}

	private void setDisplay(String msg,Level level){
		thislevel=level;

		setjf(msg);
		jf.setLayout(new BorderLayout());

		mb = new Menus(level);
		jf.setMenuBar(mb);

		initPanels();

		setFoundation();
		//ImageIcon test=new ImageIcon("./image/tree3_2.gif");

		setprop();//左上のやつ
		//property.Change(test);

		setdisp();//真ん中のやつ
		//makelevel.changeIcon(15,15,1);

		setitp();
		//item.addItem(test);

		setlayerp();//左真ん中のやつ
		//layer.change(test);

	}

	public Display(String msg, String target) {
		if (target != null)
		{
			targetjson = target;
		}

		Level level;
		level = new Level();
		Json2LevelAdapter.Load(level,targetjson);
		setDisplay(msg,level);
	}

	public Display(String msg,Level level,int i)
	{
		setDisplay(msg,level);
	}

	public void mouseClicked(MouseEvent e){
		ImageIcon ForLayer;
		Point point = e.getPoint();
		pointx = point.x;
		pointy = point.y;
		System.out.println(pointx+" "+pointy);
		int i;
		layer.GetPointaddress(pointx,pointy);
        int id=thislevel.ObjectMap[layer.point_rownum-1][layer.point_columnnum-1];
        if(id!=0){
            ForLayer = makelevel.imageIcons.get(id-1);
        }else{
            ForLayer=new ImageIcon(thislevel.BackgroundImage);
        }
        layer.change(ForLayer);
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}



}