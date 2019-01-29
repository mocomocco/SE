import javax.swing.*;
import java.awt.*;

public class Property {
    static JPanel pro;

    private void SizeChange(ImageIcon icon){
        icon=new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }

    public Property(Level level) {
        //change object size

        ImageIcon ForDisplay = new ImageIcon(level.BackgroundImage);
        SizeChange(ForDisplay);

        pro = new JPanel();
        pro.setBounds(0, 0, 50, 50);
        pro.setLayout(new GridLayout(1,1));

        JLabel label = new JLabel(ForDisplay);
        pro.add(label);
        label.setBorder(BorderFactory.createLineBorder(Color.lightGray));

    }
}