package parse;

import graphUtil.Frame;
import graphUtil.GraphMLGenerator;
import graphUtil.GraphViewer;
import graphUtil.Player;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.graphstream.graph.Graph;

import animationPack.AnimationBuilder;

public class DataParser {

	BufferedReader br;
	String line;
	String[] split;
	String data = "";
	int plcnt = 1;
	int graphIndex;
	int initialFrame,finalFrame;
	int initPos, finalPos, ballCarrier;
	boolean team1C = true;
	List<Frame> frameList = new ArrayList<Frame>();
	List<String> screenShotPathList = new ArrayList<String>();
	AnimationBuilder ab = new AnimationBuilder();

	/*
	 * Not all GraphMLs need to be created. The user now will be prompted for a input with the range
	 * of frames he needs.
	 * 
	 * And also asked which team we'll take into consideration
	 */	

	public void sourceReader(File file)
	{
		try {
			br = new BufferedReader(new FileReader(file));
			//line = br.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't find file.");
		}

	}

	public void teamConsidered()
	{
		Object[] options = {"Team 1","Team 2"};
		int n = JOptionPane.showOptionDialog(null,"Which team will be considered on the data?",
				"Soccer Miner",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);

		if(n==1) team1C = false;		
	}

	public void dataParser()
	{		
		teamConsidered();
		try {
			while(br.ready()){
				line = br.readLine();
				split = line.split(" ");
				for(int i=0;i<split.length;i++){
					if(!split[i].isEmpty()){
						if(!split[i].contains(".")){
							graphIndex = Integer.parseInt(split[i]);
							frameList.add(new Frame(graphIndex,team1C));

						}else{
							frameList.get(graphIndex-1).addPlayer(
									new Player(Float.valueOf(split[i]),Float.valueOf(split[i+1])));
							i++;
						}
					}
				}

			}
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	public void scoutParser()
	{
		initPos = 1;
		ballCarrier = 99;
		try {
			while(br.ready()){
				line = br.readLine();
				split = line.split(" ");
				if(split[5].equals("1") && ballCarrier != Integer.parseInt(split[1]))
				{
					finalPos = Integer.parseInt(split[0]);
					for(int i = initPos; i < finalPos; i++)
					{
						frameList.get(i).determineBallCarrier(ballCarrier);
					}
					ballCarrier = Integer.parseInt(split[1]);
					initPos = Integer.parseInt(split[0]);
				}
			}			
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void writer(int initial, int end)
	{
		{	

			for(int i = initial-1;i<end;i++)
			{				
				GraphMLGenerator gml = new GraphMLGenerator(frameList.get(i).createTinkerGraph(),
						frameList.get(i).getIndex());
				gml.generateGraphML();
			}

		}
	}

	public void graphVisualizer(int initial, int end)
	{
		Graph g = null;
		initialFrame = initial;
		finalFrame = end;
		for(int i = initial-1;i<end;i++)
		{
			g = frameList.get(i).createGraphStream();
			GraphViewer.main(g);
			String ss = frameList.get(i).getScreenShotPath();
			screenShotPathList.add(ss);
		}
	}

	public void dude()
	{
		/*File imgFile = new File(screenShotPathList.get(1));
		while (!imgFile.exists())
		{
			imgFile = new File(screenShotPathList.get(1));
		}*/		
		ab.setOutputFilenamePath("Animation " + initialFrame + "-" + finalFrame + ".wmv");
		ab.setJpegFilePathList(screenShotPathList);
		ab.convertJpegFramesToMpegVideo();
	}

	/*
	 * This will be used only for testing purposes. 
	 */	

	public void deleteFrameList()
	{
		frameList.clear();
	}
}
