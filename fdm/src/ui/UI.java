package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import parse.DataParser;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UI {

	private JFrame frmFdm;
	DataParser dp = new DataParser();
	File file;
	private JTextField initialFrameTxt;
	private JTextField finalFrameTxt;

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
		frmFdm.setTitle("fdm\r\n");
		frmFdm.setBounds(100, 100, 191, 231);
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
			}
		});
		mnFile.add(mntmOpen);
		
		JButton btnFrame = new JButton("Frames");
		
		JButton btnParse = new JButton("Parse");
		btnParse.setBounds(50, 11, 86, 23);
		btnParse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{

				dp.Parse();
				initialFrameTxt.setEnabled(true);
				finalFrameTxt.setEnabled(true);
				btnFrame.setEnabled(true);
				
			}
		});
		frmFdm.getContentPane().setLayout(null);
		frmFdm.getContentPane().add(btnParse);
		
		initialFrameTxt = new JTextField();
		initialFrameTxt.setEnabled(false);
		initialFrameTxt.setBounds(50, 68, 86, 20);
		frmFdm.getContentPane().add(initialFrameTxt);
		initialFrameTxt.setColumns(10);
		
		finalFrameTxt = new JTextField();
		finalFrameTxt.setEnabled(false);
		finalFrameTxt.setBounds(50, 110, 86, 20);
		frmFdm.getContentPane().add(finalFrameTxt);
		finalFrameTxt.setColumns(10);
		
		
		btnFrame.setEnabled(false);
		btnFrame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				dp.writer(Integer.parseInt(initialFrameTxt.getText()), Integer.parseInt(finalFrameTxt.getText()));
			}
		});
		btnFrame.setBounds(47, 141, 89, 23);
		frmFdm.getContentPane().add(btnFrame);
		
		JLabel lblInitialFrame = new JLabel("Initial Frame");
		lblInitialFrame.setEnabled(false);
		lblInitialFrame.setBounds(50, 54, 86, 14);
		frmFdm.getContentPane().add(lblInitialFrame);
		
		JLabel lblFinalFrame = new JLabel("Final Frame");
		lblFinalFrame.setEnabled(false);
		lblFinalFrame.setBounds(50, 96, 86, 14);
		frmFdm.getContentPane().add(lblFinalFrame);
	}
}
