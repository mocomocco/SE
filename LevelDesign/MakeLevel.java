import java.awt.*;
import javax.swing.*;

public class MakeLevel {
    static JPanel map;

    public MakeLevel() {
        Level level;
        level = new Level();
        Object object;
        object = new Object();
        int x = level.levelsize.gridsize;
        int y = x * x;
        map = new JPanel();
        map.setBounds(0, 0, level.levelsize.width, level.levelsize.height);
        map.setLayout(new GridLayout(x, x));
        ImageIcon icon1 = new ImageIcon(level.BackgroundName);
        JLabel label[] = new JLabel[y];

        //make background --レベルの背景を作成
        for (int i = 0; i < y; i++) {
            label[i] = new JLabel(icon1);
            map.add(label[i]);
            label[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
        }


        //change object size
        ImageIcon imageIcon = new ImageIcon(object.Image); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(object.objectsize.anchorx, object.objectsize.anchory,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        //add object to row[m] column[n]  levelのm行n列目にオブジェクトを追加
        int m, n, p;
        m = 2;
        n = 3;
        p = (m - 1) * x + (n - 1);
        label[p].setIcon(imageIcon);
    }
}