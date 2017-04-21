package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
	
	// get number of fastaRecords from given file 
	public static int getNumber(String filename) throws IOException{
		int total  = 0;
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line;
		
		while ((line = in.readLine()) != null){
			if (line.startsWith(">")){
				total++;
			}
		}
		in.close();
		return total;
	}
	
	// generate List of FastaRecords from given file
	public static LinkedList<FastaRecord> readFastaToList(String filename) throws IOException{
		
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
		
		in.close();
		return fastas;
	}

	// generate Array of FastaRecords from given file 
	public static FastaRecord[] readFastaToArray(String filename) throws IOException{
		
		int length = getNumber(filename);
				
		FastaRecord[] fastas = new FastaRecord[length];

		String name = ""; 
		String sequence = "";
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line;
		
		boolean foundNext = false;
		int counter = 0;
		
		while ((line = in.readLine()) != null){
			
			//all FastaRecord attributes found			
			if(line.startsWith(">") && foundNext){
				fastas[counter] = new FastaRecord(name, sequence);
				counter++;
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
		
		in.close();
		return fastas;
	}

	
	public static void main(String[] args) throws IOException{
		
		String filename = "test.fasta";
		
		LinkedList<FastaRecord> listOfFastas;
		FastaRecord[] ArrayOfFastas;
		
		listOfFastas = readFastaToList(filename);
		ArrayOfFastas = readFastaToArray(filename);
		System.out.println(listOfFastas.get(1));
		System.out.println(ArrayOfFastas[1]);
		System.out.println(getNumber(filename));
		System.out.println("Done");
	}

}
