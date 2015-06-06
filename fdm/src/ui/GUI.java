package ui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame
{
	
	JButton btnParse = new JButton();
	JButton btnScout = new JButton();
	JButton btnGraph = new JButton();
	JButton btnVideo = new JButton();

	public GUI(){

		super("Football Miner");
		
		try
		{
			Image btnPontoImg = ImageIO.read(getClass().getResource("res/parseBtn.png"));
			btnParse.setIcon(new ImageIcon(btnPontoImg));
		}
		catch (IOException e)
		{
			System.out.println("damn nigga");
		}

	}

}
