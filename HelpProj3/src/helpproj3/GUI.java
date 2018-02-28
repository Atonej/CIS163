package helpproj3;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

	private JMenuBar menus;
	private JMenu fileMenu;

	private JMenuItem openSerItem;
	private JMenuItem exitItem;
	private JMenuItem saveSerItem;
	private JMenuItem openTextItem;
	private JMenuItem saveTextItem;
	private JScrollPane scroll;

	private JTable jListTable;
	private Model dList;

	//private 
	public GUI () {	
		fileMenu = new JMenu("File");
		openSerItem = new JMenuItem("Open Serial...");
		saveSerItem = new JMenuItem("Save Serial...");
		exitItem = new JMenuItem("Exit!");
		openTextItem = new JMenuItem("Add book");
		saveTextItem = new JMenuItem("Save Text...");

		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.addSeparator();
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.addSeparator();		
		fileMenu.add(exitItem);

		openSerItem.addActionListener(this);
		exitItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);

		menus = new JMenuBar();

		menus.add(fileMenu); 
		setJMenuBar(menus);

		dList = new Model();
		jListTable = new JTable(dList);
		 scroll = new JScrollPane(jListTable);


		add(scroll);

		setVisible(true);
		setSize(400,200);
	}

	public static void main (String[] args) {
		new GUI();
	}

	public void actionPerformed(ActionEvent e) {

		if (exitItem == e.getSource()) 
			System.exit(0);

		if (openSerItem == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.loadFromSerialized(filename);

			}
		}

		if (saveSerItem == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.saveAsSerialized(filename);

			}
		}
		
		if(openTextItem == e.getSource()) {
			dList.add();
			
		}
	}
}

