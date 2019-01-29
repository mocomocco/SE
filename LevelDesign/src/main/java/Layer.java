import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layer {
    static JPanel layers;
    int point_columnnum,point_rownum,point_address;
    Level targetlevel;
    ImageIcon ForDisplay;
    JLabel newlabel;

    public void GetPointaddress(int newpointx,int newpointy){
        point_columnnum=newpointx / targetlevel.levelsize.gridsize + 1;
        point_rownum=newpointy / targetlevel.levelsize.gridsize + 1;
        //click outside levelsize
        if (point_columnnum > targetlevel.levelsize.width || point_rownum > targetlevel.levelsize.height) {
            point_columnnum = 1;
            point_rownum = 1;
        }
    }

    private void SizeChange(ImageIcon icon){
        icon=new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }

    public void change(ImageIcon ForDisplay){
        //change object size
        SizeChange(ForDisplay);
        newlabel.setIcon(ForDisplay);
    }

    public Layer(Level level, List<ImageIcon> imageIcons, int pointx, int pointy) {
        targetlevel=level;

        ImageIcon ForDisplay = new ImageIcon(level.BackgroundImage);

        GetPointaddress(pointx,pointy);

        int id=level.ObjectMap[point_rownum-1][point_columnnum-1];
        if(id!=0){
            ForDisplay = imageIcons.get(id-1);
        }

        //change object size
        SizeChange(ForDisplay);

        layers = new JPanel();
        layers.setBounds(0, 0, 50, 50);
        layers.setLayout(new GridLayout(1,1));

        newlabel = new JLabel(ForDisplay);
        layers.add(newlabel);
        newlabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));

    }

}
