import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

/**
 * from https://ateraimemo.com/Swing/ZoomAndPanPanel.html
 */



public class ZoomAndPanePanel extends DrawPanel {

    private final AffineTransform zoomTransform = new AffineTransform();
    private final transient Image img;
    private final Rectangle imgrect;
    private transient ZoomHandler handler;
    private transient DragScrollListener listener;

    protected ZoomAndPanePanel(Image img) {
        super();
        this.img = img;
        this.imgrect = new Rectangle(img.getWidth(this), img.getHeight(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new Color(0x55FF0000, true));
        Rectangle r = new Rectangle(500, 140, 150, 150);

        //use: AffineTransform#concatenate(...) and Graphics2D#setTransform(...)
        //AffineTransform at = g2.getTransform();
        //at.concatenate(zoomTransform);
        //g2.setTransform(at);
        //g2.drawImage(img, 0, 0, this);
        //g2.fill(r);

        //or use: Graphics2D#drawImage(Image, AffineTransform, ImageObserver)
        g2.drawImage(img, zoomTransform, this);
        //or: g2.drawRenderedImage((RenderedImage) img, zoomTransform);
        g2.fill(zoomTransform.createTransformedShape(r));

        //BAD EXAMPLE
        //g2.setTransform(zoomTransform);
        //g2.drawImage(img, 0, 0, this);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Rectangle r = zoomTransform.createTransformedShape(imgrect).getBounds();
        return new Dimension(r.width, r.height);
    }

    @Override
    public void updateUI() {
        removeMouseListener(listener);
        removeMouseMotionListener(listener);
        removeMouseWheelListener(handler);
        super.updateUI();
        listener = new DragScrollListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        handler = new ZoomHandler();
        addMouseWheelListener(handler);
    }

    protected class ZoomHandler extends MouseAdapter {
        private static final double ZOOM_MULTIPLICATION_FACTOR = 1.2;
        private static final int MIN_ZOOM = -10;
        private static final int MAX_ZOOM = 10;
        private static final int EXTENT = 1;
        private final BoundedRangeModel zoomRange = new DefaultBoundedRangeModel(
                0, EXTENT, MIN_ZOOM, MAX_ZOOM + EXTENT);
        @Override public void mouseWheelMoved(MouseWheelEvent e) {
            int dir = e.getWheelRotation();
            int z = zoomRange.getValue();
            zoomRange.setValue(z + EXTENT * (dir > 0 ? -1 : 1));
            if (z != zoomRange.getValue()) {
                Component c = e.getComponent();
                Container p = SwingUtilities.getAncestorOfClass(JViewport.class, c);
                if (p instanceof JViewport) {
                    JViewport vport = (JViewport) p;
                    Rectangle ovr = vport.getViewRect();
                    double s = dir > 0 ? 1d / ZOOM_MULTIPLICATION_FACTOR : ZOOM_MULTIPLICATION_FACTOR;
                    zoomTransform.scale(s, s);
                    //double s = 1d + zoomRange.getValue() * .1;
                    //zoomTransform.setToScale(s, s);
                    Rectangle nvr = AffineTransform.getScaleInstance(s, s).createTransformedShape(ovr).getBounds();
                    Point vp = nvr.getLocation();
                    vp.translate((nvr.width - ovr.width) / 2, (nvr.height - ovr.height) / 2);
                    vport.setViewPosition(vp);
                    c.revalidate();
                    c.repaint();
                }
            }
        }
    }

}
