package parse;

import graphUtil.Frame;
import graphUtil.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataParser {

	BufferedReader br;
	String line;
	String[] split;
	String data = "";
	int plcnt = 1;
	int graphIndex;
	List<Frame> frameList = new ArrayList<Frame>();
	
	/*
	 * This is madly archaic, but at least shows I can actually parse the data.
	 * As of it 03/May, it takes way too much time to create simple txt files, even though there is a crapload of them..
	 * I don't expect to generate GraphMLs any faster with this method, so I'll have to think it through.
	 */
	
	/*
	 * Not all GraphMLs need to be created. The user now will be prompted for a input with the range
	 * of frames he needs.
	 */
	
	
	
	public void sourceReader(File file){
		try {
			br = new BufferedReader(new FileReader(file));
			//line = br.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't find file.");
		}

	}
	
	public void Parse(){
		try {
			while(br.ready()){
			line = br.readLine();
			split = line.split(" ");
			System.out.println(graphIndex);
			for(int i=0;i<split.length;i++){
				if(!split[i].isEmpty()){
					if(!split[i].contains(".")){
						//data = data + "Grafo:" + split[i] + "\r\n";
						graphIndex = Integer.parseInt(split[i]);
						frameList.add(new Frame(graphIndex));
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
	
	public void writer(int initial, int end){
		{	
			
			for(int i = initial;i<=end;i++)
			{
				data = frameList.get(i).print();
				try {
					File file = new File("frame " + i + ".txt");
					FileWriter fileWriter = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(data);
					bufferedWriter.close();
					//graphIndex++;
					data = "";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        
	    }
	}
	
	/*
	 * This will be used only for testing purposes. 
	 */	
	
	public void printFrame()
	{
		String test = JOptionPane.showInputDialog("Frame");
		frameList.get(Integer.parseInt(test)).print();
	}
}
