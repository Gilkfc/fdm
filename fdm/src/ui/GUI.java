package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame
{
	
	JButton btnParse = new JButton();
	JButton btnScout = new JButton();
	JButton btnGraph = new JButton();
	JButton btnVideo = new JButton();

	public GUI()
	{

		super("Football Miner");
		
		try
		{
			Image btnParseImg = ImageIO.read(new File("res\\parseBtn.png"));
			btnParse.setIcon(new ImageIcon(btnParseImg));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find parseBtn.png");
		}
		
		try
		{
			Image btnScoutImg = ImageIO.read(new File("res\\scoutBtn.png"));
			btnScout.setIcon(new ImageIcon(btnScoutImg));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find scoutBtn.png");
		}
		
		try
		{
			Image btnGraphImg = ImageIO.read(new File("res\\graphBtn.png"));
			btnGraph.setIcon(new ImageIcon(btnGraphImg));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find graphBtn.png");
		}
		
		try
		{
			Image btnVideoImg = ImageIO.read(new File("res\\videoBtn.png"));
			btnVideo.setIcon(new ImageIcon(btnVideoImg));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find videoBtn.png");
		}
		
		btnParse.setBorderPainted(false); 
		btnParse.setContentAreaFilled(false); 
		btnParse.setFocusPainted(false); 
		btnParse.setOpaque(false);
		
		btnScout.setBorderPainted(false); 
		btnScout.setContentAreaFilled(false); 
		btnScout.setFocusPainted(false); 
		btnScout.setOpaque(false);
		
		btnGraph.setBorderPainted(false); 
		btnGraph.setContentAreaFilled(false); 
		btnGraph.setFocusPainted(false); 
		btnGraph.setOpaque(false);
		
		btnVideo.setBorderPainted(false); 
		btnVideo.setContentAreaFilled(false); 
		btnVideo.setFocusPainted(false); 
		btnVideo.setOpaque(false);
		
		JPanel pnlButtons = new JPanel();
		BoxLayout brdButtons = new BoxLayout(pnlButtons,BoxLayout.Y_AXIS);
		pnlButtons.setLayout(brdButtons);
		
		pnlButtons.add(btnParse);
		pnlButtons.add(btnScout);
		pnlButtons.add(btnGraph);
		pnlButtons.add(btnVideo);
		
		Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
		
        cntForm.add(pnlButtons, BorderLayout.CENTER);
        
		this.setSize(200,200);
		this.setVisible(true);

	}

}
