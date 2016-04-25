package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	private JFrame jf;
	File graphData;
	File scoutData;
	Integer initFrame;
	Integer finalFrame;
	DataParser dp = new DataParser();
	static JButton redB = new JButton("red");
	static JButton greenB = new JButton("green");
	static JButton blueB = new JButton("blue");
	static JButton cyanB = new JButton("cyan");
	static JTextArea cyanTextArea = new JTextArea();
	SingleCDockable red = create("red", Color.RED); 		// Will contain the visualizations
	SingleCDockable green = create("green", Color.GREEN);	// Will contain the graph tree. I still nee to see how I'm doing this
	SingleCDockable blue = create("blue", Color.BLUE);		// Will contain available actions. probably.
	SingleCDockable cyan = create("cyan", Color.CYAN);	// Will show data or graphics or something else.
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}
	
	
	public void initialize()
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
		jf = new JFrame("Soccer Miner");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("Menu");
		menuBar.add(menuFile);
		
		JMenu menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		menuAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO simple about dialog :)
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Load Files");
		menuFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("2d Data File", new String[] {"2d"});
				fileChooser.setFileFilter(filter);
				Component frame = null;
				fileChooser.showOpenDialog(frame);
				graphData = fileChooser.getSelectedFile();
				dp.sourceReader(graphData);
				dp.dataParser();
				cyanTextArea.setText("game data loaded\n");
				FileFilter filter2 = new FileNameExtensionFilter("ant scout data", new String[] {"ant"});
				fileChooser.setFileFilter(filter2);
				fileChooser.showOpenDialog(frame);
				scoutData = fileChooser.getSelectedFile();
				dp.sourceReader(scoutData);
				dp.scoutParser();
				cyanTextArea.setText("scout data loaded");
				getIntervalAndDisplayGraphs();
				//dp.newDataParser(graphData, scoutData);
				//TODO Flie Choosing shenanigans. Prolly do both choosings in sequence
				/*
				 * Still need to figure out how to exacly do this
				 */
			}
		});
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		menuFile.add(mntmReset);
		mntmReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getIntervalAndDisplayGraphs();
				//TODO plan and implement.
			}
		});
		
		CControl control = new CControl(jf);
		jf.setLayout(new GridLayout(1,1));
		jf.add(control.getContentArea());
		
		control.addDockable(red);
		control.addDockable(green);
		control.addDockable(blue);
		control.addDockable(cyan);
		
		red.setLocation(CLocation.base().normalNorth(0.6));
		red.setVisible(true);
		
		green.setLocation(CLocation.base().normalWest(0.23));
		green.setVisible(true);
		
		blue.setLocation(CLocation.base().normalEast(0.23));
		blue.setVisible(true);
		
		cyan.setLocation(CLocation.base().normalSouth(0.23));
		cyan.setVisible(true);
		
		jf.setBounds(100, 100, 805, 611);
		jf.setVisible(true);
	}
	
	public static SingleCDockable create(String title, Color color){
		JPanel background = new JPanel();
		background.setOpaque(true);
		background.setBackground(color);
		switch(title){
		case "red":
			background.add(redB);
			break;
		case "green":
			background.add(greenB);
			break;
		case "blue":
			background.add(blueB);
			break;
		case "cyan":
			background.add(cyanB);
			background.add(cyanTextArea);
			break;
		}
		
		return new DefaultSingleCDockable (title, title, background);
		
	}
	
	public void getIntervalAndDisplayGraphs(){
		JTextField initialFrameTxt = new JTextField();
		JTextField finalFrameTxt = new JTextField();
		JButton ok = new JButton("OK");
		JFrame tempFrame = new JFrame("Soccer Miner");
		JLabel tempLbl = new JLabel("Informe o intervalo de Frames para visualização.");
		initialFrameTxt.setColumns(10);
		finalFrameTxt.setColumns(10);
		tempFrame.setBounds(50, 50, 289, 99);
		tempFrame.setLayout(new FlowLayout());
		tempFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tempFrame.getContentPane().add(tempLbl);
		tempFrame.getContentPane().add(initialFrameTxt);
		tempFrame.getContentPane().add(finalFrameTxt);
		tempFrame.getContentPane().add(ok);
		tempFrame.setResizable(false);
		tempFrame.setVisible(true);
		
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initFrame = Integer.parseInt(initialFrameTxt.getText());
				finalFrame = Integer.parseInt(finalFrameTxt.getText());
				System.out.println(jf.getHeight() + " " + jf.getWidth());
				System.out.println(tempFrame.getHeight() + " " + tempFrame.getWidth());
				tempFrame.dispose();
				int index = initFrame;
				while(index <= finalFrame)
				{				
				dp.newGraphVisualizer(index);
				index++;
				}
			}
		});
	}	
}
