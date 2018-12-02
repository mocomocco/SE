import java.awt.*;
import javax.swing.*;

public class MakeLevel {
    static JPanel map;
    static ImageIcon[] imageIcons;

    public MakeLevel(Level level) {
        int x = level.levelsize.width;
        int y = level.levelsize.height;
        map = new JPanel();
        map.setBounds(0, 0, level.levelsize.gridsize*x, level.levelsize.gridsize*y);
        map.setLayout(new GridLayout(y,x));
        ImageIcon icon1 = new ImageIcon(level.BackgroundImage);
        JLabel label[] = new JLabel[x*y];

        //make background --レベルの背景を作成
        for (int i = 0; i < x*y; i++) {
            label[i] = new JLabel(icon1);
            map.add(label[i]);
            label[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }


        //change object size
        int objectlength=level.objects.size();
        int instanceslength=level.ObjectMap.length;
        imageIcons=new ImageIcon[objectlength];

        for (int objectid=1;objectid<objectlength+1;objectid++) {
            //System.out.println(objectid-1+" "+level.objects.get(objectid-1).Image);
            imageIcons[objectid-1] = new ImageIcon(level.objects.get(objectid-1).Image); // load the image to a imageIcon
            Image image = imageIcons[objectid-1].getImage(); // transform it
            Image newimg = image.getScaledInstance(level.objects.get(objectid-1).objectsize.anchorx, level.objects.get(objectid-1).objectsize.anchory, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcons[objectid-1] = new ImageIcon(newimg);  // transform it back

        }

        //add object to row[m] column[n]  levelのm行n列目にオブジェクトを追加
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
                    }
                }
                //System.out.println(" ");
            }
        }

    }
}