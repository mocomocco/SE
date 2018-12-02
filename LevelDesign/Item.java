import javax.swing.*;
import java.awt.*;

public class Item {
    static JPanel items;

    public Item() {
        Object object;
        object = new Object();

        //change object size
        ImageIcon imageIcon = new ImageIcon(object.Image); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back


        int x = 1; // number of item
        items = new JPanel();
        items.setBounds(0, 0, 50, 50);
        items.setLayout(new GridLayout(1,1));

        JLabel label[] = new JLabel[x];

        for (int i = 0; i < x; i++) {
            label[i] = new JLabel(imageIcon);
            items.add(label[i]);
            label[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }
        items.setBorder(BorderFactory.createLineBorder(Color.lightGray));;

    }
}