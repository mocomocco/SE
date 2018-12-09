import javax.swing.*;
import java.awt.*;

public class Property {
    static JPanel pro;

    public Property(Level level) {
        //change object size

        ImageIcon imageIcon = new ImageIcon(level.BackgroundImage);
        Image newimg = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon ForDisplay=new ImageIcon(newimg);

        pro = new JPanel();
        pro.setBounds(0, 0, 50, 50);
        pro.setLayout(new GridLayout(1,1));

        JLabel label = new JLabel(ForDisplay);
        pro.add(label);
        label.setBorder(BorderFactory.createLineBorder(Color.lightGray));

    }
}