package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.graphstream.graph.Graph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.*;

public class TabbedGraphs extends JFrame{

	private int initialFrame;
	private int finalFrame;
	Graph g;
	ArrayList<JPanel> panelArray = new ArrayList<JPanel>();
	JTabbedPane jt;
	
	TabbedGraphs(int i, int f, Graph gra)
	{
		setTitle("asdasd");
		jt = new JTabbedPane();
		this.initialFrame = i;
		this.finalFrame = f;
		this.g = gra;
	    jt.setVisible(true);
	}
	
	public void createJFrames()
	{
		for(int k = initialFrame; k<=finalFrame;k++)
		{
			JPanel panel = new JPanel();
			panelArray.add(panel);
		}
		
		/*for(int k = 0; k<panelArray.size();k++)
		{
			jt.addTab("Frame " + (k+initialFrame),panelArray.get(k));
			jt.setSelectedIndex(0);			
		}*/
		
		add(jt);
	}
	
	public void insertGraphStream(Graph g, int k)
	{
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Viewer viewer = new Viewer(g,Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.addDefaultView(false);
		//g.display();
		//View view = viewer.addDefaultView(false);
	    DefaultView defaultViewer = new DefaultView(viewer, "graph", Viewer.newGraphRenderer());
	    /*
	    panelArray.get(k).add(defaultViewer);
	    panelArray.get(k).updateUI();*/
	    jt.addTab("Frame " + (k+initialFrame), defaultViewer);
	   
	}
	
	
	
}
