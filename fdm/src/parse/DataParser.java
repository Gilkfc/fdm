package parse;

import graphUtil.Frame;
import graphUtil.GraphMLGenerator;
import graphUtil.GraphViewer;
import graphUtil.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.graphstream.graph.Graph;

import ui.TabbedGraphs;
import animationPack.AnimationBuilder;

public class DataParser {

	BufferedReader br, br2;
	String line;
	String[] split;
	String data = "";
	int plcnt = 1;
	int graphIndex;
	int initialFrame,finalFrame;
	int initPos, finalPos;
	int ballCarrier = 99;
	boolean team1C = true;
	List<Frame> frameList = new ArrayList<Frame>();
	List<String> screenShotPathList = new ArrayList<String>();
	List<Graph> graphStreamList = new ArrayList<Graph>();
	AnimationBuilder ab = new AnimationBuilder();
	Frame tempFrame;
	Player tempPlayer;

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

	public void graphVisualizer(int initial, int end, TabbedGraphs tb)
	{
		Graph g = null;
		initialFrame = initial;
		finalFrame = end;
		for(int i = initial-1;i<end;i++)
		{
			g = frameList.get(i).createGraphStream();
			//GraphViewer.main(g);
			tb.insertGraphStream(g, i);
			String ss = frameList.get(i).getScreenShotPath();
			screenShotPathList.add(ss);
			frameList.remove(i);
		}
	}

	public void convertAnimation()
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
	
	public void newDataParser(File data, File scout){
		sourceReader(data);
		teamConsidered();
		try {
			while(br.ready()){
				line = br.readLine();
				split = line.split(" ");
				for(int i=0;i<split.length;i++){
					if(!split[i].isEmpty()){
						if(!split[i].contains(".")){
							graphIndex = Integer.parseInt(split[i]);
							tempFrame = new Frame(graphIndex,team1C);
							System.out.println("Made Frame " + graphIndex);
						}else{
							tempPlayer = new Player(Float.valueOf(split[i]),Float.valueOf(split[i+1]));
							tempFrame.addPlayer(tempPlayer);
							System.out.println("\tAdded player to Frame" + graphIndex);
							i++;
						}
					}
				}
				newScoutParser(scout,graphIndex);
				graphStreamList.add(tempFrame.createGraphStream());
				System.out.println("\t\t\tCreated graphStream. GodBless");
			}
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void newScoutParser(File file, int index){
		try {
			br2 = new BufferedReader(new FileReader(file));
			//line = br.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't find file.");
		}

		try {
			while(br2.ready()){
				line = br2.readLine();
				split = line.split(" ");
				if(Integer.parseInt(split[0]) <= index){
					if(split[5].equals("1") && ballCarrier != Integer.parseInt(split[1]))
					{

						ballCarrier = Integer.parseInt(split[1]);
					}
					tempFrame.determineBallCarrier(ballCarrier);
					System.out.println("\t\tDetermined BC for Frame " + index);
				}
				br2.close();
			}
			br2.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}
	
}













