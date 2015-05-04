package parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataParser {

	BufferedReader br;
	String line;
	String[] split;
	String data = "";
	int plcnt = 1;
	int graph = 1;
	
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
			System.out.println(graph);
			for(int i=0;i<split.length;i++){
				if(!split[i].isEmpty()){
					if(!split[i].contains(".")){
						data = data + "Grafo:" + split[i] + "\r\n";
					}else{
						
						data = data + "Jogador " + plcnt + " x:" + split[i] + " y:" + split[i+1] + "\r\n";
						plcnt++;
						i++;
					}
				}
			}
			writer();
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
				
	}
	
	public void writer(){
		{
			
			
	        try {
				
				File file = new File("C:\\Users\\Gil\\Desktop\\TCC\\parse " + graph + ".txt");
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(data);
				bufferedWriter.close();
				graph++;
				data = "";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
