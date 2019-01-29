import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.util.List;

public class MakeLevel {
    static JPanel map;
    static List<ImageIcon> imageIcons;
    static JLabel[] label;
    int x, y,point_columnnum, point_rownum,point_address;
    Level targetlevel;
    LineBorder red=new LineBorder(Color.RED, 2, true);
    LineBorder gray=new LineBorder(Color.LIGHT_GRAY,1, true);

    private void initmap(Level level){
        map = new JPanel();
        map.setBounds(0, 0, level.levelsize.gridsize*x, level.levelsize.gridsize*y);
        map.setLayout(new GridLayout(y,x));
    }

    private void MakeFoundation(ImageIcon icon1){
        //make background --レベルの背景を作成
        for (int i = 0; i < x*y; i++) {
            label[i] = new JLabel(icon1);
            map.add(label[i]);
            label[i].setBorder(gray);
        }
    }

    public int GetPointaddress(int newpointx,int newpointy){
        point_columnnum=newpointx / targetlevel.levelsize.gridsize + 1;
        point_rownum=newpointy / targetlevel.levelsize.gridsize + 1;
        //click outside levelsize
        if (point_columnnum > targetlevel.levelsize.width || point_rownum > targetlevel.levelsize.height) {
            point_columnnum = 1;
            point_rownum = 1;
        }
        return ((point_rownum-1)*x+(point_columnnum-1));
    }

    private void setxy(PopupDialog pop){

        if (pop.wid != 0 && pop.hei != 0){
            x = pop.wid;
            y = pop.hei;
        } else{
            x = targetlevel.levelsize.width;
            y = targetlevel.levelsize.height;
        }
    }

    public void setRedBorder(int address){
        label[point_address].setBorder(gray);
        point_address=address;
        label[address].setBorder(red);
    }

    private void SizeChange(ImageIcon icon,int address){
        icon=new ImageIcon(icon.getImage().getScaledInstance(targetlevel.objects.get(address).objectsize.anchorx,  targetlevel.objects.get(address).objectsize.anchory, java.awt.Image.SCALE_SMOOTH));
    }

    private void setIcon(int address,ImageIcon icon){
        imageIcons.add(icon); // load the image to a imageIcon
        SizeChange(imageIcons.get(address),(address));
    }

    public void changeIcon(int newpointx,int newpointy,int iconid){
        GetPointaddress(newpointx,newpointy);
        label[point_address].setIcon(imageIcons.get(iconid-1));
    }

    public MakeLevel(Level level, PopupDialog pop, int pointx, int pointy) {
        targetlevel=level;
        setxy(pop);
        GetPointaddress(pointx,pointy);

        initmap(level);

        label = new JLabel[x*y];
        ImageIcon icon1 = new ImageIcon(level.BackgroundImage);
        MakeFoundation(icon1);

        //change border color
        setRedBorder(point_address);

        //change object size
        imageIcons=new ArrayList<ImageIcon>();

        for (int objectid=1;objectid<level.objects.size()+1;objectid++) {
            setIcon((objectid-1),new ImageIcon(level.objects.get(objectid-1).Image));
        }

        //add object
            for(int rownum=1;rownum<y+1;rownum++){
                for(int columnnum=1;columnnum<x+1;columnnum++){
                    int id=level.ObjectMap[rownum-1][columnnum-1];
                    if(id!=0){
                        int address=(rownum-1)*x+(columnnum-1);
                        label[address].setIcon(imageIcons.get(id-1));
                    }
                }
            }

    }
}