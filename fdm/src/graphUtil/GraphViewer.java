package graphUtil;


import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.view.Viewer;

import ui.TabbedGraphs;

public class GraphViewer {
	
	/**
	 * Simply generates a viewer for the graph. Currently,. each graph 
	 * @param g
	 */
	
	public static void main(Graph g) {
	    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	    Viewer viewer = g.display(false);
	    DefaultView defaultViewer  = new DefaultView(viewer, null,null);
	    Viewer view = new Viewer(g,Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
	    view.addDefaultView(false);
	}

}
