package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import parse.DataParser;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CLocation;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;


public class GUI
{
	static File graphData;
	File scoutData;
	static DataParser dp = new DataParser();
	
	public static void main(String[] args)
	{
		String className = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame jf = new JFrame("Soccer Miner");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem mntmOpen = new JMenuItem("Load Files");
		menuFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO Flie Choosing shenanigans. Prolly do both choosings in sequence
				/*
				 * Still need to figure out how to exacly do this
				 */
			}
		});
		
		CControl control = new CControl(jf);
		jf.setLayout(new GridLayout(1,1));
		jf.add(control.getContentArea());
		
		SingleCDockable red = create("red", Color.RED); 		// Will contain the visualizations
		SingleCDockable green = create("green", Color.GREEN);	// Will contain the graph tree. I still nee to see how I'm doing this
		SingleCDockable blue = create("blue", Color.BLUE);		// Will contain available actions. probably.
		SingleCDockable yellow = create("cyan", Color.CYAN);	// Will show data or graphics or something else.
		
		control.addDockable(red);
		control.addDockable(green);
		control.addDockable(blue);
		control.addDockable(yellow);
		
		red.setVisible(true);
		yellow.setVisible(true);
		
		green.setLocation(CLocation.base().normalSouth(0.4));
		green.setVisible(true);
		
		blue.setLocation(CLocation.base().normalEast(0.3));
		blue.setVisible(true);
		
		jf.setBounds(20,20,400,400);
		jf.setVisible(true);
	}
	
	public static SingleCDockable create(String title, Color color){
		JPanel background = new JPanel();
		background.setOpaque(true);
		background.setBackground(color);
		switch(title){
		case "red":
			background.add(new JButton("Maybe Graphs"));
			break;
		case "green":
			background.add(new JButton("the graphs"));
			break;
		case "blue":
			background.add(new JButton("actions?"));
			break;
		case "yellow":
			background.add(new JButton("graphics, data, whatever"));
			break;
		}
		
		return new DefaultSingleCDockable (title, title, background);
		
	}
	
	public static File loadFile(){
		JFileChooser fileChooser = new JFileChooser(".2d");
		FileFilter filter = new FileNameExtensionFilter(".2d Data File", new String[] {"2d"});
		fileChooser.setFileFilter(filter);
		Component temp = null;
		fileChooser.showOpenDialog(temp);
		return fileChooser.getSelectedFile();
	}

}
