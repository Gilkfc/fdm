package graphUtil;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	protected int index;
	List<Player> playerList = new ArrayList<Player>();
	protected int playerIndex = 1;
	
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
