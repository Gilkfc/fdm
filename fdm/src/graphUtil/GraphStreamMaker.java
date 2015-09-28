package graphUtil;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;


public class GraphStreamMaker {
	
	protected int index;
	int playerIndex = 1;
	Graph graphStream = new MultiGraph(Integer.toString(index));
	String screenShotPath = "";
		
	public GraphStreamMaker(){
		this.screenShotPath = "screenshots\\ss" + index + ".jpg";
	}
	
	public void addPlayer(int x, int y, boolean team1c){
		if(x != -9999.0000)
		{
			graphStream.addNode(Integer.toString(playerIndex));

			Node n = graphStream.getNode(playerIndex);			
			n.addAttribute("ui.label", playerIndex);
			n.addAttribute("z_level", 0);
			n.addAttribute("xyz",x,y,0);
			if(team1c)
			{
				n.addAttribute("ui.class", "considered");
			}
			
			
		}
	}
	
	public Graph createGraphStream()
	{
		String styleSheet = "node {"+
				" fill-color: red;"+
				"}"+
				"node.considered {"+
				" fill-color: green;"+
				"}"+
				"node.ballcarrier {"+
				" fill-color: blue;"+
				"}";

		graphStream.addAttribute("ui.stylesheet", styleSheet);

		

		graphStream.addEdge("A1", 1, 2);
		graphStream.addEdge("A2", 4, 6);
		graphStream.addEdge("A3", 2, 7);
		graphStream.addEdge("A4", 8, 10);
		graphStream.addEdge("A5", 5, 9);
		graphStream.addEdge("A6", 7, 9);
		graphStream.addEdge("A7", 3, 9);
		graphStream.addAttribute("ui.screenshot", screenShotPath);
		return graphStream;

	}
}
