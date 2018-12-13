import java.awt.*;
import javax.swing.*;

public class MakeLevel {
    static JPanel map;
    static ImageIcon[] imageIcons;
    static JLabel[] label;

    public MakeLevel(Level level, int pointx, int pointy) {
        int x = level.levelsize.width;
        int y = level.levelsize.height;
        map = new JPanel();
        map.setBounds(0, 0, level.levelsize.gridsize*x, level.levelsize.gridsize*y);
        map.setLayout(new GridLayout(y,x));
        ImageIcon icon1 = new ImageIcon(level.BackgroundImage);
        label = new JLabel[x*y];

        //make background --レベルの背景を作成
        for (int i = 0; i < x*y; i++) {
            label[i] = new JLabel(icon1);
            map.add(label[i]);
            label[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }




        int point_columnnum = pointx / level.levelsize.gridsize + 1;
        int point_rownum = pointy / level.levelsize.gridsize + 1;

        //click outside levelsize
        if (point_columnnum > level.levelsize.width || point_rownum > level.levelsize.height) {
            point_columnnum = 1;
            point_rownum = 1;
        }
        //change border color
        int point_address=(point_rownum-1)*x+(point_columnnum-1);
        label[point_address].setBorder(BorderFactory.createLineBorder(Color.red));

        //change object size
        int objectlength=level.objects.size();
        int instanceslength=level.ObjectMap.length;
        imageIcons=new ImageIcon[objectlength];
        String imagename;
        for (int objectid=1;objectid<objectlength+1;objectid++) {
            //System.out.println(objectid-1+" "+level.objects.get(objectid-1).Image);

            imageIcons[objectid-1] = new ImageIcon(level.objects.get(objectid-1).Image); // load the image to a imageIcon

            Image image = imageIcons[objectid-1].getImage(); // transform it
            Image newimg = image.getScaledInstance(level.objects.get(objectid-1).objectsize.anchorx, level.objects.get(objectid-1).objectsize.anchory, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcons[objectid-1] = new ImageIcon(newimg);  // transform it back

        }

        //add object
        for(int instanceid=1;instanceid<instanceslength;instanceid++){
            for(int rownum=1;rownum<y+1;rownum++){
                for(int columnnum=1;columnnum<x+1;columnnum++){
                    //System.out.print(level.ObjectMap[rownum-1][columnnum-1]);
                    int id=level.ObjectMap[rownum-1][columnnum-1];
                    if(id!=0){
                        int address=(rownum-1)*x+(columnnum-1);
                        //System.out.println("rownum"+rownum+"column"+columnnum+"address "+address);
                        //System.out.println(id);
                        label[address].setIcon(imageIcons[id-1]);
                        //System.out.println(imageIcons[id-1]);
                    }
                }
                //System.out.println(" ");
            }
        }

    }
}