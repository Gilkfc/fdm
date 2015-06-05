package graphUtil;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;


public class Frame {
	
	protected int index;
	List<Player> playerList = new ArrayList<Player>();
	protected int playerIndex = 1;
	boolean t1C;
	TinkerGraph tinkerGraph = new TinkerGraph();
	Graph graphStream = new MultiGraph(Integer.toString(index));
	
	String testText = "";
	
	public Frame (int i, boolean tC)
	{
		this.index = i;
		this.t1C = tC;	
	}
	
	public void addPlayer (Player p)
	{
		p.setNumber(playerIndex);
		if(t1C)
		{
			if(playerIndex < 15) p.setTeam(Team.Considered); else p.setTeam(Team.NotConsidered);			
		}
		else
		{
			if(playerIndex < 15) p.setTeam(Team.NotConsidered); else p.setTeam(Team.Considered);
		}
		playerList.add(p);
		playerIndex++;
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
	public TinkerGraph createTinkerGraph()
	{
		for(int i = 0; i<playerList.size();i++)
		{
			addPlrVrtxTinkerGraph(playerList.get(i));
		}
		
		return tinkerGraph;
	}
	
	public void addPlrVrtxTinkerGraph(Player p)
	{
		Vertex v = this.tinkerGraph.addVertex(null);
		v.setProperty("number", p.getNumber());
		v.setProperty("x", p.getxPosition());
		v.setProperty("y", p.getyPosition());
		v.setProperty("team", p.getTeam());
	}
	
	public Graph createGraphStream()
	{
		String styleSheet = "node {"+
				   " fill-color: red;"+
				   "}"+
				   "node.considered {"+
				   " fill-color: green;"+
				   "}";
		
		graphStream.addAttribute("ui.stylesheet", styleSheet);
		
		for(int i=0; i<playerList.size();i++)
		{
			Player plr = playerList.get(i);
			String id = Integer.toString(plr.getNumber());
			
			if(plr.getxPosition() != -9999.0000)
			{
			graphStream.addNode(id);
			System.out.println(id);
			Node n = graphStream.getNode(id);			
			n.addAttribute("ui.label", plr.number);
			n.addAttribute("z_level", 0);
			n.addAttribute("xyz",plr.getxPosition(),plr.getyPosition(),0);
			if(plr.getTeam() == Team.Considered)
			{
				n.addAttribute("ui.class", "considered");
			}
			//n.addAttribute("Team", plr.getTeam());
			//System.out.println(graphStream.getNode(id).getAttribute("xyz").toString());
			}
	
		}
		
		graphStream.addEdge("A1", 1, 2);
		graphStream.addEdge("A2", 4, 6);
		graphStream.addEdge("A3", 2, 7);
		graphStream.addEdge("A4", 8, 10);
		graphStream.addEdge("A5", 5, 9);
		graphStream.addEdge("A6", 7, 9);
		graphStream.addEdge("A7", 3, 9);
		
		return graphStream;
		 
	}
	

	

}
