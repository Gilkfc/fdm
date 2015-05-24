package graphUtil;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public class Frame {
	
	protected int index;
	List<Player> playerList = new ArrayList<Player>();
	protected int playerIndex = 1;
	Graph graph = new TinkerGraph();
	
	String testText = "";
	
	public Frame (int i)
	{
		this.index = i;
	}
	
	public void addPlayer (Player p)
	{
		p.setNumber(playerIndex);
		playerList.add(p);
		playerIndex++;
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
	public Graph createGraph()
	{
		for(int i = 0; i<playerList.size();i++)
		{
			addPlayerVertex(playerList.get(i));
		}
		
		return graph;
	}
	
	public void addPlayerVertex(Player p)
	{
		Vertex v = this.graph.addVertex(null);
		v.setProperty("number", p.getNumber());
		v.setProperty("x", p.getxPosition());
		v.setProperty("y", p.getyPosition());
	}
	
	public String print()
	{
		testText = "Frame #" + index + "\r\n";
		for(int i = 0; i<playerList.size();i++)
		{
			testText = testText + "Player " + playerList.get(i).getNumber() + "\r\n";
			testText = testText + "x: " + playerList.get(i).getxPosition() + " y: " + playerList.get(i).getyPosition() + "\r\n";
		}
		
		return testText;
	}
}
