import javax.swing.*;
import java.awt.*;

/**
 * Created by HINARI on 2019/01/24.
 */
public class myMainPanel extends JPanel {
    protected Level mylevel;
    protected int x,y;

    myMainPanel(Level l){
        super();
        mylevel=l;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }


}
