import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Display 
{
	JFrame jf;
	MenuBar mb;
	JPanel p1, p2; // top panel, bottom panel
	JPanel pa, pb; // left panel, right panel in top panel
	JPanel prop, layerp, disp, itp; //property panel, layer panel, display panel, item panel

	public Display(String msg) 
	{
		jf = new JFrame(msg);

		jf.setVisible(true);
		jf.setSize(800,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mb = new Menus();
		jf.setMenuBar(mb);

		jf.setLayout(new BorderLayout());

		p1 = new JPanel();	p2 = new JPanel();
		pa = new JPanel();	pb = new JPanel();
		
		p1.setLayout(new BorderLayout());
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

		pa.add(prop);
		pa.add(layerp);
		pa.setPreferredSize(new Dimension(300,0));

		p1.add(pa, BorderLayout.WEST);
		p1.add(disp, BorderLayout.CENTER);

		jf.add(p1, BorderLayout.CENTER);
		jf.add(itp, BorderLayout.SOUTH);

		Level level;
		level = new Level();
		Json2LevelAdapter.Load(level,"json/level1.json");

		disp.setLayout(null);
		MakeLevel makelevel;
		makelevel = new MakeLevel(level);
		disp.add(makelevel.map);
		disp.setBorder(BorderFactory.createLineBorder(Color.lightGray));;

		itp.setLayout(null);
		Item item;
		item = new Item(level,makelevel.imageIcons);
		itp.add(item.items);
		itp.setBorder(BorderFactory.createLineBorder(Color.lightGray));;

	}

}