package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	// get number of fastaRecords from given file 
	public static int getNumber(String filename){
		int total  = 0;
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
		
			while ((line = in.readLine()) != null){
				if (line.startsWith(">")){
					total++;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	
	// generate fasta file from given FastaRecordArray
	public static void writeFasta(FastaRecord[] Seques, String filename){
		try{
			File file = new File(filename);
		    file.createNewFile();

			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < Seques.length; i++) {
				out.write(">"+ Seques[i].getIdentifier()); 
				out.write(Seques[i].makePrettySeq() + "\n");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	// read single fasta file with and without identifier
	public static FastaRecord readSingleFasta(String filename) throws IOException{
		String identifier = "Sequence_" + filename; 
		String line = "";
		String sequence = "";
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		line = in.readLine();
		if (line.startsWith(">")){
			identifier = line.substring(1).trim();
		}
		
		while((line = in.readLine()) != null){
			sequence = sequence + line.trim();
		}
		in.close();
		
		sequence = sequence.replace(" ", "").replace("/t ", "").replace("/n", "");
				
		return new FastaRecord(identifier, sequence);
	}
	
	// read fasta file 
	public static FastaRecord[] parseFasta(String filename){
		
		String line, sequence, identifier;
		sequence = "";
		
		FastaRecord[] Seques = null;
		try{
			int length = getNumber(filename);
			// in case of single file without identifier
			if (length == 0){ length = 1;}
					
			Seques = new FastaRecord[length];
	
			BufferedReader in = new BufferedReader(new FileReader(filename));
	
			boolean foundNext = false;
			int counter = 0;
			
			line = in.readLine();
			
			if(line.startsWith(">")){
					identifier = line.substring(1).trim();
				} else {
					// if sequence without identifier
					identifier = "Sequence_" + counter;
					sequence = line;
				}
			
			while ((line = in.readLine()) != null){
		
				// add to Array if next identifier found
				if (line.startsWith(">")){
					sequence = sequence.replace(" ", "").replace("/t ", "").replace("/n", "");
					Seques[counter] = new FastaRecord(identifier, sequence);
					counter++;
					
					identifier = line.substring(1).trim();
					sequence = "";
				} else { 				
					// include line in sequence
					sequence = sequence + line.trim();
					}
				}
	
			// include last or only sequence
			sequence = sequence.replace(" ", "").replace("/t ", "").replace("/n", "");
			Seques[counter] = new FastaRecord(identifier, sequence);
			
			in.close();

		} catch (IOException e){
			e.printStackTrace();
			}
		return Seques;
	}	
	
	// reverse order of FastaFile array
	public static FastaRecord[] reverseOrder(FastaRecord[] fastas){
//		FastaRecord temp = null;
//
//		for (int i = 0; i < fastas.length/2; i++) {
//			temp = fastas[i];
//			fastas[i] = fastas[fastas.length-1-i];
//			fastas[fastas.length-1-i] = temp;
//		}
//		
//		return fastas;
		
		FastaRecord[] temp = new FastaRecord[fastas.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = fastas[temp.length-1-i];
		}
		return temp;
	}
	
	public static void printArray(FastaRecord[] fastas){
		for (int i = 0; i < fastas.length; i++) {
			if (fastas[i] == null){
				System.out.println(i);
			} else {
				System.out.println(fastas[i].getIdentifier());
			}
		}
	}
	
	public static void main(String[] args) {
		FastaRecord test = new FastaRecord("test", "ATCT");
//		System.out.println(test);
//		System.out.println(test.toReverseComplement());
		
		if (args[2] == "single"){
			// read single file and write it as reverse complement
			FastaRecord[] singlefasta = parseFasta(args[0]);
			singlefasta[0].setSequence(singlefasta[0].toReverseComplement());
			writeFasta(singlefasta, args[1]);
		} else if (args[2] == "multiple"){
			// read multiple file and write it as fasta in reverse Order
			FastaRecord[] fastas = parseFasta(args[0]);
			FastaRecord[] fastas_switched = reverseOrder(fastas);
			writeFasta(fastas_switched, args[1]);
		}
		
	
		System.out.println("Done");
	}

}
