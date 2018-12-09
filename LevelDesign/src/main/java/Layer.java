import javax.swing.*;
import java.awt.*;

public class Layer {
    static JPanel layers;

    public Layer(Level level, ImageIcon[] imageIcons, int pointx, int pointy) {

        int columnnum = pointx / level.levelsize.gridsize + 1;
        int rownum = pointy / level.levelsize.gridsize + 1;

        //click outside levelsize
        if (columnnum > level.levelsize.width || rownum > level.levelsize.height) {
            columnnum = 1;
            rownum = 1;
        }

        ImageIcon icon1 = new ImageIcon(level.BackgroundImage);

        int id=level.ObjectMap[rownum-1][columnnum-1];
        if(id!=0){
            icon1 = imageIcons[id-1];
            //System.out.println(id);
            //System.out.println(imageIcons[id-1]);
        }


        //change object size
        //ImageIcon icon1 = imageIcons[id-1];
        Image newimg = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon ForDisplay=new ImageIcon(newimg);

        layers = new JPanel();
        layers.setBounds(0, 0, 50, 50);
        layers.setLayout(new GridLayout(1,1));

        JLabel newlabel = new JLabel(ForDisplay);
        layers.add(newlabel);
        newlabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));

    }

}
