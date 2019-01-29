import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Item {
    static JPanel items;
    public static List<JLabel> labels=new ArrayList<JLabel>();

    private ImageIcon SizeChange(ImageIcon icon){
        return new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }

    public void addItem(ImageIcon icon){
        icon=SizeChange(icon);
        JLabel newlabel=new JLabel(icon);
        labels.add(newlabel);
        newlabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        items.add(newlabel);
        items.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }

    public Item(Level level,List<ImageIcon> imageIcons) {
        //change object size

        ImageIcon[] ForDisplay=new ImageIcon[imageIcons.size()];
        for(int i=0;i<imageIcons.size();i++) {
            ForDisplay[i]=SizeChange(imageIcons.get(i));
        }

        int x = level.objects.size(); // number of item

        items = new JPanel();
        items.setBounds(0, 0, 50*x, 50);
        items.setLayout(new GridLayout(1,x));

        for (int i = 0; i < x; i++) {
            //labels.add(new JLabel(ForDisplay[i]));
            addItem(ForDisplay[i]);
        }
        items.setBorder(BorderFactory.createLineBorder(Color.lightGray));

    }
}