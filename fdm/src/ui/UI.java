package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import parse.DataParser;
import java.awt.Canvas;


public class UI {

	private JFrame frmFdm;
	DataParser dp = new DataParser();
	File file;
	private JTextField initialFrameTxt;
	private JTextField finalFrameTxt;
	JButton btnParse = new JButton();
	JButton btnScout = new JButton();
	JButton btnFrame = new JButton();
	JButton btnVideo = new JButton();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmFdm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFdm = new JFrame();
		frmFdm.setResizable(false);
		frmFdm.setTitle("Soccer Miner\r\n");
		frmFdm.setBounds(100, 100, 268, 261);
		frmFdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmFdm.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser jf = new JFileChooser(".2d");
				Component frame = null;
				jf.showOpenDialog(frame);
				file = jf.getSelectedFile();
				dp.sourceReader(file);
				btnParse.setEnabled(true);
			}
		});
		mnFile.add(mntmOpen);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textPane.setEditable(false);
		textPane.setBounds(139, 136, 66, 20);
		frmFdm.getContentPane().add(textPane);
		
		
		btnScout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnScout.setIcon(new ImageIcon("res\\scoutBtnClick.png"));
				JFileChooser jf = new JFileChooser(".2d");
				Component frame = null;
				jf.showOpenDialog(frame);
				file = jf.getSelectedFile();
				dp.sourceReader(file);
				textPane.setBackground(Color.RED);
				textPane.setText("Please wait..");
				dp.scoutParser();
				btnFrame.setEnabled(true);
				btnScout.setEnabled(false);
				textPane.setBackground(Color.GREEN);
				textPane.setText("Complete");
			}
		});
		btnScout.setBorderPainted(false); 
		btnScout.setContentAreaFilled(false); 
		btnScout.setFocusPainted(false); 
		btnScout.setOpaque(false);
		btnScout.setIcon(new ImageIcon("res\\scoutBtn.png"));
		btnScout.setEnabled(false);
		btnScout.setBounds(36, 46, 66, 23);
		frmFdm.getContentPane().add(btnScout);

		

		JLabel lblInitialFrame = new JLabel("Initial Frame");
		lblInitialFrame.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInitialFrame.setBounds(39, 80, 86, 14);
		frmFdm.getContentPane().add(lblInitialFrame);

		JLabel lblFinalFrame = new JLabel("Final Frame");
		lblFinalFrame.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFinalFrame.setBounds(39, 122, 86, 14);
		frmFdm.getContentPane().add(lblFinalFrame);

		btnParse.setBorderPainted(false); 
		btnParse.setContentAreaFilled(false); 
		btnParse.setFocusPainted(false); 
		btnParse.setOpaque(false);
		btnParse.setIcon(new ImageIcon("res\\parseBtn.png"));
		btnParse.setEnabled(false);
		btnParse.setBackground(new Color(240, 240, 240));
		btnParse.setBounds(36, 11, 66, 23);
		btnParse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				btnParse.setIcon(new ImageIcon("res\\parseBtnClicked.png"));
				textPane.setBackground(Color.RED);
				textPane.setText("Please wait..");
				dp.dataParser();
				btnScout.setEnabled(true);
				initialFrameTxt.setEnabled(true);
				finalFrameTxt.setEnabled(true);
				
				btnParse.setEnabled(false);
				textPane.setBackground(Color.GREEN);
				textPane.setText("Complete");

			}
		});
		frmFdm.getContentPane().setLayout(null);
		frmFdm.getContentPane().add(btnParse);

		initialFrameTxt = new JTextField();
		initialFrameTxt.setEnabled(false);
		initialFrameTxt.setBounds(36, 94, 66, 20);
		frmFdm.getContentPane().add(initialFrameTxt);
		initialFrameTxt.setColumns(10);

		finalFrameTxt = new JTextField();
		finalFrameTxt.setEnabled(false);
		finalFrameTxt.setBounds(36, 136, 66, 20);
		frmFdm.getContentPane().add(finalFrameTxt);
		finalFrameTxt.setColumns(10);


		btnFrame.setBorderPainted(false); 
		btnFrame.setContentAreaFilled(false); 
		btnFrame.setFocusPainted(false); 
		btnFrame.setOpaque(false);
		btnFrame.setIcon(new ImageIcon("res\\graphBtn.png"));
		btnFrame.setEnabled(false);
		btnFrame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnFrame.setIcon(new ImageIcon("res\\graphBtnClick.png"));
				textPane.setBackground(Color.RED);
				textPane.setText("Wait for the files to be created");
				dp.writer(Integer.parseInt(initialFrameTxt.getText()), Integer.parseInt(finalFrameTxt.getText()));
				dp.graphVisualizer(Integer.parseInt(initialFrameTxt.getText()), Integer.parseInt(finalFrameTxt.getText()));
				textPane.setBackground(Color.GREEN);
				textPane.setText("Files created");
				dp.deleteFrameList();
				btnFrame.setEnabled(false);
				btnVideo.setEnabled(true);
			}
		});
		btnFrame.setBounds(36, 167, 66, 23);
		frmFdm.getContentPane().add(btnFrame);		
		
		btnVideo.setBorderPainted(false); 
		btnVideo.setContentAreaFilled(false); 
		btnVideo.setFocusPainted(false); 
		btnVideo.setOpaque(false);
		btnVideo.setIcon(new ImageIcon("res\\videoBtn.png"));
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				btnVideo.setIcon(new ImageIcon("res\\videoBtnClick.png"));
				textPane.setBackground(Color.RED);
				textPane.setText("Please wait...");
				dp.dude();
				textPane.setBackground(Color.GREEN);
				textPane.setText("Encoded");
				btnVideo.setEnabled(false);
				
			}
		});
		
		
		btnVideo.setEnabled(false);
		btnVideo.setBounds(135, 167, 66, 23);
		frmFdm.getContentPane().add(btnVideo);
		
		JLabel label = new JLabel("");
		label.setBounds(132, 21, 120, 105);
		frmFdm.getContentPane().add(label);
		label.setIcon(new ImageIcon("res\\FMlogoSmall1.png"));
		

	}
}
