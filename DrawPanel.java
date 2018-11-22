import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.io.ByteArrayOutputStream;

 /*
 */
//copy getgraphics(x,y,width,height)
/**
 * @author g1620520
 *simple draw panel class povides drawLine method
 */
public class DrawPanel extends JPanel{
    private static final long serialVersionUID = 42L;
    BufferedImage bufferImage=null;
    Graphics2D bufferGraphics=null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int windoww=screenSize.width;
    int windowh=screenSize.height;
    double paperw=2480.0,paperh=3508.0;


    public BufferedImage createBuffer(int width, int height,BufferedImage newbufferImage) {
        //バッファ用のImageとGraphicsを用意する
        newbufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        bufferGraphics=newbufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraphics2D。

        bufferGraphics.setBackground(Color.white);
        //Color color1 = new Color(1.0f, 0.0f, 0.0f, 0.5f); // 透過率50%の赤
        //bufferGraphics.setPaint(color1);
        //super.setOpaque(false);
        //RGB値を0(透明色)に置換

        //bufferGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
        return newbufferImage;
    }

    public void backet(int x,int y,int orginRGB,int currentColor){
        try{
        if(x<windowh * 2480/3508 && 0<x && y<windowh && 0<y) {
            if (bufferImage.getRGB(x, y) == orginRGB) {
                bufferImage.setRGB(x, y, currentColor);
                if (x + 1 <= paperw) {
                    if (bufferImage.getRGB(x + 1, y) == orginRGB) backet(x + 1, y, orginRGB, currentColor);
                }
                if (x - 1 >= 0) {
                    backet(x - 1, y, orginRGB, currentColor);
                }
                if (y + 1 <= paperh) {
                    if (bufferImage.getRGB(x, y + 1) == orginRGB) backet(x, y + 1, orginRGB, currentColor);
                }
                if (y - 1 >= 0) {
                    backet(x, y - 1, orginRGB, currentColor);
                }
            }
        }}catch (Exception e){

        }
    }

    public int getRGB(int x,int y){
        return(bufferImage.getRGB(x,y));
    }

        Color currentColor = Color.black;
        Float currentwidthlevel=3.0f;

    public void setPenColor(Color newColor) {
        currentColor = newColor;
    }

    public void setPenWidth(Float newwidthlevel){
        currentwidthlevel=newwidthlevel;
    }

    public void drawLine(int x1, int y1, int x2, int y2){
        if(bufferGraphics==null) {


            //bufferImage=this.createBuffer(windoww,windowh,bufferImage);
            bufferImage=this.createBuffer(windoww,windowh,bufferImage);  //バッファをまだ作ってなければ作る
        }
        bufferGraphics.setColor(currentColor);
        BasicStroke wideStroke = new BasicStroke(currentwidthlevel);
        bufferGraphics.setStroke(wideStroke);
        bufferGraphics.drawLine(x1, y1, x2, y2); // バッファに描画する
        repaint();//再描画するためpaintComponentを呼び出す。
    }

    public static BufferedImage resizeImage(final BufferedImage image, final double scale) throws IOException {

        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        BufferedImage resizedImage = new BufferedImage(width, height, image.getType());

        // アフィン変換でリサイズ（画質優先）
        AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        op.filter(image, resizedImage);
        //System.out.println("return");
        return resizedImage;
    }

    public void setpapersize (double neww,double newh)throws IOException{
        System.out.println("neww:"+neww+" newh:"+newh+" paperw:"+paperw+" paperh:"+paperh);

        double wscale=neww/paperw;
        double hscale=newh/paperh;
        System.out.println("wscale:"+wscale+" hscale:"+hscale);
        double scale=Double.max(wscale,hscale);
        System.out.println("scele"+scale);
        BufferedImage destImage=this.resizeImage(this.bufferImage,1/scale);
        bufferGraphics.drawImage(destImage,0,0,this);
        if (neww>newh){
            System.out.println("横");
            this.setPreferredSize(new Dimension(windoww , windowh));
        }else{
            System.out.println("縦");
            this.setPreferredSize(new Dimension(windoww, windowh));
        }
        repaint();
        paperw=neww;
        paperh=newh;
//this.setPreferredSize(new Dimension(windoww , windoww * newh/neww));
        //this.setPreferredSize(new Dimension(windowh * neww/newh, windowh));
    }

    public void setsize(int neww,int newh) {
        BufferedImage newsizebufferimage = null;
        newsizebufferimage=this.createBuffer(neww,newh,newsizebufferimage);;
        bufferGraphics.drawImage(bufferImage,0,0,this);
        repaint();
    }

    public void openFile(BufferedImage destImage) throws IOException{
        //System.out.println("");
        bufferGraphics.drawImage(destImage,0,0,this);
        //System.out.println("");
        repaint(); //画像を表示するためにpaintComponentを呼ぶ
        //System.out.println("");
    }


    public void saveFile(File file2save) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            BufferedImage destImage = resizeImage(bufferImage,1);
            ImageIO.write(destImage, "jpg", file2save);
        } catch (Exception e) {
            System.out.println("Error: writing file=" + file2save.getName());
            return;
        }
    }

    public void newFile(){
        bufferImage=null;
        bufferGraphics=null;
        //bufferImage=createBuffer(windoww,windowh,bufferImage);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);//他に描画するものがあるかもしれないので親を呼んでおく
        if(null!=bufferImage) g.drawImage(bufferImage, 0,0,this);//バッファを表示する
    }
}

