package parse;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 *	Just for testing purposes, this won't be used on the final project at all. 
		 */
		
		DataParser dp = new DataParser();
		File file = new File("C:\\Users\\Gil\\Desktop\\TCC\\CapSpoT1Suav (1).2d");
		
		dp.sourceReader(file);
		dp.Parse();
		dp.printFrame();
		
	}

}
