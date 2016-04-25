package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.view.Viewer;

import parse.DataParser;
import bibliothek.gui.DockController;
import bibliothek.gui.DockFrontend;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.DefaultDockable;
import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.StackDockStation;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.station.split.SplitDockProperty;


public class GUI
{
	private JFrame jf;
	File graphData;
	File scoutData;
	Integer initFrame;
	Integer finalFrame;
	static DataParser dp = new DataParser();
	static JButton redB = new JButton("red");
	static JButton greenB = new JButton("green");
	static JButton blueB = new JButton("blue");
	static JButton cyanB = new JButton("cyan");
	static JTextArea cyanTextArea = new JTextArea();
	Dockable red = createDockable("red", Color.RED); 		// Will contain the visualizations
	Dockable green = createDockable("green", Color.GREEN);	// Will contain the graph tree. I still nee to see how I'm doing this
	Dockable blue = createDockable("blue", Color.BLUE);		// Will contain available actions. probably.
	Dockable cyan = createDockable("cyan", Color.CYAN);	// Will show data or graphics or something else.
	Dockable black = createDockable("black", Color.BLACK);
	Dockable orange = createDockable("orange", Color.ORANGE);


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


	@SuppressWarnings("deprecation")
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

		DockController controller = new DockController();
		DockController.disableCoreWarning();
		DockFrontend frontend = new DockFrontend(jf);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.setRootWindow(jf);

		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("Menu");
		menuBar.add(menuFile);

		StackDockStation station = new StackDockStation();
		SplitDockStation splitDockStation = new SplitDockStation();
		controller.add(splitDockStation);
		jf.add(splitDockStation);
		
		JMenu menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		menuAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
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
				getIntervalAndDisplayGraphs(station);
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
				getIntervalAndDisplayGraphs(station);
				//TODO plan and implement.
			}
		});

		CControl control = new CControl(jf);
			
		splitDockStation.drop(red);
		splitDockStation.drop(green, new SplitDockProperty(0.3, 0, 0.7, 0.5 ));
		splitDockStation.drop(blue, new SplitDockProperty(0.3, 0, 0.7, 0.5 ));
		//splitDockStation.drop(cyan, new SplitDockProperty(0.75, 0.5, 0.25, 0.5 ));
						
        station.drop(black);
        station.drop(orange);
        //splitDockStation.drop(station, new SplitDockProperty(0.4, 0, 0.4, 0.5 ));
		
        

		jf.setBounds(0,0,900,900);
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
	
    private static Dockable createDockable(String title, Color color)
    {
        DefaultDockable dockable = new DefaultDockable();
        dockable.setTitleText(title);
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(color);
        dockable.add(panel);
        return dockable;
    }
    
    private static Dockable createDockableGraph(String title, int index)
    {
        DefaultDockable dockable = new DefaultDockable();
        dockable.setTitleText(title);
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        Graph g = dp.newGraphVisualizer(index);
        
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Viewer viewer = new Viewer(g,Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.addDefaultView(false);
		//g.display();
		//View view = viewer.addDefaultView(false);
	    DefaultView defaultViewer = new DefaultView(viewer, "graph", Viewer.newGraphRenderer());
        panel.add(defaultViewer);
        dockable.add(panel);
        return dockable;
    }
	

	public void getIntervalAndDisplayGraphs(StackDockStation sds){
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
					sds.drop(createDockableGraph("Frame " + index,index));
				}
			}
		});
	}
	
}
