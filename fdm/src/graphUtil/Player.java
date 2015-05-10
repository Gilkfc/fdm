package graphUtil;

public class Player {
	
	protected int number;
	protected float xPosition, yPosition;
	
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
	
	

}
