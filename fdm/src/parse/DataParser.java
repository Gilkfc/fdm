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

public class DataParser {

	BufferedReader br;
	String line;
	String[] split;
	String data = "";
	int plcnt = 1;
	int graphIndex;
	boolean team1C = true;
	List<Frame> frameList = new ArrayList<Frame>();
	
		
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
				"A Silly Question",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		
		if(n==1) team1C = false;		
	}
	
	public void Parse()
	{		
		teamConsidered();
		try {
			while(br.ready()){
			line = br.readLine();
			split = line.split(" ");
			//System.out.println(graphIndex);
			for(int i=0;i<split.length;i++){
				if(!split[i].isEmpty()){
					if(!split[i].contains(".")){
						//data = data + "Grafo:" + split[i] + "\r\n";
						graphIndex = Integer.parseInt(split[i]);
						frameList.add(new Frame(graphIndex,team1C));
						
					}else{
						frameList.get(graphIndex-1).addPlayer(
								new Player(Float.valueOf(split[i]),Float.valueOf(split[i+1])));
						//data = data + "Jogador " + plcnt + " x:" + split[i] + " y:" + split[i+1] + "\r\n";
						//plcnt++;
						i++;
					}
				}
			}
			//writer();
			//System.out.println(data);
			//data = "";
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
				
	}
	
	public void writer(int initial, int end)
	{
		{	
			
			for(int i = initial;i<=end;i++)
			{				
				GraphMLGenerator gml = new GraphMLGenerator(frameList.get(i).createTinkerGraph(),frameList.get(i).getIndex());
				gml.generateGraphML();
			}
	        
	    }
	}
	
	public void dude()
	{
		
		Graph g = frameList.get(1).createGraphStream();
		GraphViewer gv = null;
		gv.main(g);
	}
	
	/*
	 * This will be used only for testing purposes. 
	 */	
	
	public void printFrame()
	{
		String test = JOptionPane.showInputDialog("Frame");
	}
}
