import javax.swing.*;
import java.awt.*;

public class Item {
    static JPanel items;

    public Item(Level level,ImageIcon[] imageIcons) {
        //change object size

        ImageIcon[] ForDisplay=new ImageIcon[imageIcons.length];
        for(int i=0;i<imageIcons.length;i++) {
            Image newimg = imageIcons[i].getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale it the smooth way
            ForDisplay[i]=new ImageIcon(newimg);
        }

        int x = level.objects.size(); // number of item
        items = new JPanel();
        items.setBounds(0, 0, 50*x, 50);
        items.setLayout(new GridLayout(1,x));

        JLabel label[] = new JLabel[x];

        for (int i = 0; i < x; i++) {
            label[i] = new JLabel(ForDisplay[i]);
            items.add(label[i]);
            label[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }
        items.setBorder(BorderFactory.createLineBorder(Color.lightGray));;

    }
}