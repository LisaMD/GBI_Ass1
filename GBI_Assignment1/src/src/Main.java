package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
	
	public static LinkedList<FastaRecord> readFastafile(String filename) throws IOException{
		
		LinkedList<FastaRecord> fastas = new LinkedList<FastaRecord>();

		String name = ""; 
		String sequence = "";
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line;
		
		boolean foundNext = false;
		
		while ((line = in.readLine()) != null){
			
			//all FastaRecord attributes found			
			if(line.startsWith(">") && foundNext){
				fastas.add(new FastaRecord(name, sequence));
				foundNext = false;
				}
			
			//new FastaName found 
			if (line.startsWith(">")){
				name = line.substring(1).trim();
				foundNext = true;
				}
			
			else {
				sequence = sequence + line.trim();
				}
			}	
		
		return fastas;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		String filename = "test.fasta";
		
		LinkedList<FastaRecord> listOfFastas;
		
		listOfFastas = readFastafile(filename);
		System.out.println(listOfFastas);
		
		System.out.println("Done");
	}

}
