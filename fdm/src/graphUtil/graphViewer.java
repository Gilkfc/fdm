package graphUtil;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class graphViewer {
	
	public static void main(String args[]) {
	    System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	    
	    Graph graph = new SingleGraph("I can see dead pixels");
	    Viewer viewer = graph.display();
	    
	}

}
