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
	boolean t1C;
	Graph graph = new TinkerGraph();
	
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
		v.setProperty("team", p.getTeam());
	}
	

}
