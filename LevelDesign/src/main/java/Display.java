import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;


public class Display extends JFrame implements MouseListener
{
	JFrame jf;
	MenuBar mb;
	JPanel p1, p2; // top panel, bottom panel
	JPanel pa, pb; // left panel, right panel on top panel
	JPanel prop, layerp, disp, itp; //property panel, layer panel, display panel, item panel
	MakeLevel makelevel;
	PopupDialog pop;
	static int pointx = 1;
	static int pointy = 1;
	static int count = 0;

	String targetjson="./json/level1.json";
	Level thislevel;


	public Display(String msg){
		this(msg,null);
	}

	private void setjf(String msg){
		jf = new JFrame(msg);
		jf.setVisible(true);
		jf.setSize(800,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setFoundation(){
		jf.add(p1, BorderLayout.CENTER);
		jf.add(itp, BorderLayout.SOUTH);

		p1.add(pa, BorderLayout.WEST);
		p1.add(disp, BorderLayout.CENTER);

		pa.add(prop);
		pa.add(layerp);
		pa.setPreferredSize(new Dimension(300,0));
	}

	private void initPanels(){
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		pa = new JPanel();
		pa.setLayout(new GridLayout(2,1));

		prop = new JPanel();
		prop.setBorder(BorderFactory.createEtchedBorder());

		layerp = new JPanel();
		layerp.setBorder(BorderFactory.createEtchedBorder());

		disp = new JPanel();
		disp.setBorder(BorderFactory.createEtchedBorder());

		itp = new JPanel();
		itp.setBorder(BorderFactory.createEtchedBorder());
		itp.setPreferredSize(new Dimension(400,200));

		pop = new PopupDialog(count);
	}

	private void setprops(Level level){
		prop.setLayout(null);
		Property property;
		property = new Property(level);
		prop.add(property.pro);
		prop.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}

	private void setDisplay(String msg,Level level){
		thislevel=level;

		setjf(msg);
		jf.setLayout(new BorderLayout());

		mb = new Menus(level);
		jf.setMenuBar(mb);

		initPanels();

		setFoundation();

		setprops(level);

		disp.setLayout(null);
		makelevel = new MakeLevel(level, pop, pointx, pointy);
		disp.add(makelevel.map);
		disp.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		itp.setLayout(null);
		Item item;
		item = new Item(level,makelevel.imageIcons);
		itp.add(item.items);
		itp.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		layerp.setLayout(null);
		Layer layer;
		layer = new Layer(level, makelevel.imageIcons, pointx, pointy);
		layerp.add(layer.layers);
		layerp.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		disp.addMouseListener(this);


	}

	public Display(String msg, String target)
	{
		if (target != null)
		{
			targetjson = target;
		}

		Level level;
		level = new Level();
		Json2LevelAdapter.Load(level,targetjson);
		setDisplay(msg,level);
	}

	public Display(String msg,Level level,int i)
	{
		setDisplay(msg,level);
	}

	public void mouseClicked(MouseEvent e){
		Point point = e.getPoint();
		Level level;
		level = new Level();
		Json2LevelAdapter.Load(level,targetjson);
		pointx = point.x;
		pointy = point.y;
		jf.setVisible(false);
		int i;
		i=(int)((pointx / thislevel.levelsize.gridsize + 1)*4+(pointy / thislevel.levelsize.gridsize + 1));
		//makelevel.label[i].setIcon();
				//[(pointx / thislevel.levelsize.gridsize + 1)*4+(pointy / thislevel.levelsize.gridsize + 1)];
		/*Display display;
		setDisplay("Level Design",level);
		display = new Display("Level Design");*/
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}



}