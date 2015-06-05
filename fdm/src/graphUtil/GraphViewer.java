package graphUtil;


import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;

public class GraphViewer {
	
	public static void main(Graph g) {
	    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	    
	  
	    Viewer viewer = g.display(false);
	    
	}

}