package graphUtil;

public class Player {

	protected int number;
	protected float xPosition, yPosition;
	protected Team team;
	protected boolean ballCarrier = false;

	/**
	 * Contains information of a single player. Aside from x,y coordinates, it can store the number (which should
	 * be unique per player) and which team he belongs to
	 * 
	 * The team can be Consideredm ot NotConsidered. Which is determined by the user.
	 * 
	 * @param x the position on the x-axis
 	 * @param y the position on the y-axis
	 */
	

	public Player (float x, float y)
	{
		this.xPosition = x;
		this.yPosition = y;		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public boolean getBallCarrier(){
		return ballCarrier;
	}
	
	public void isBallCarrier(){
		this.ballCarrier = true;
		System.out.println("player " + this.number + " is ball carrier");
	}



}
