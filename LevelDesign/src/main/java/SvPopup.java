import java.awt.*;
import javax.swing.*;

public class SvPopup extends JDialog {
	JDialog save;
	JPanel p1, p2, p3;
	JLabel nameLabel, formatLabel;
	JTextField nameText;
	JComboBox<String> list;
	JButton ok, cancel;
	String namet;
	Level thislevel;

	public SvPopup(Level level)
	{
		String extension[] = {"json", "exe"};
		save = new JDialog(this, "Save", true);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		nameLabel = new JLabel("Name:", JLabel.CENTER);
		nameText = new JTextField("Untitled", 13);
		formatLabel = new JLabel("Format:", JLabel.CENTER);
		list = new JComboBox<>(extension);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		save.setLayout(new FlowLayout());
		save.setSize(200, 230); 
		save.setLocation(500, 250); 
		pack();
		
		p1.setLayout(new GridLayout(2,1));
		p2.setLayout(new GridLayout(2,1));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		p1.add(nameLabel);
		p1.add(nameText);
		p2.add(formatLabel);
		p2.add(list);
		p3.add(cancel);
		p3.add(ok);
		
		save.add(p1);
		save.add(p2);
		save.add(p3);
		

		ok.addActionListener(e -> {
			namet = nameText.getText();
			System.out.println(namet);
			save.setVisible(false);
			String extName = list.getSelectedItem().toString();
			if (extName == "json") {
				Level2JsonAdapter.WriteJson(level,namet);//<-json
				//new SvFileDialogs(namet,thislevel);
			}
			else if(extName =="exe") {
				//some codes
			}
		});
		
		cancel.addActionListener(e -> {
			save.setVisible(false);
		});

		save.setVisible(true);
		this.setResizable(false);
	}
}
