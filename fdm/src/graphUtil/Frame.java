package graphUtil;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	protected int index;
	List<Player> playerList = new ArrayList<Player>();
	protected int playerIndex = 1;
	
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
	
	public void print()
	{
		System.out.println("Frame #" + index);
		for(int i = 0; i<playerList.size();i++)
		{
			System.out.println("Player " + playerList.get(i).getNumber());
			System.out.println("x: " + playerList.get(i).getxPosition() + " y: " + playerList.get(i).getyPosition() );
		}
	}
}
