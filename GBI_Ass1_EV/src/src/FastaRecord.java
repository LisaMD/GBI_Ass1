package src;

public class FastaRecord {
	static String DNA_Alphabet = "ACTGactg";
	
	private String identifier;
	private String sequence;
	
	
	// standard stuff
	public FastaRecord(String identifier, String sequence) {
		this.identifier = identifier;
		this.sequence = sequence;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	// print Record readable
	@Override
	public String toString() {
		String str = this.identifier;
		str += makePrettySeq();
		return str;
	}
	
	// print Sequence readable
	public String makePrettySeq() {
		String str = this.sequence;
		
		String new_str = "";
		
		for (int i = 0; i <  this.sequence.length(); i++) {	
			if (i % 60 != 0){
				new_str = new_str + str.charAt(i);
			} else {
				new_str = new_str + "\n" + str.charAt(i);
			}
		}	
		return new_str;
	}

	// generate reverseComplement to Sequence
	public String toReverseComplement(){
		String str = this.sequence;
		int length = str.length();
		
		char[] reverse = new char[length];
		
		for (int i = 0; i < length; i++) {
			
			switch(str.charAt(i)) {
			case 'A': 
				reverse[length - i - 1] = 'T';
				break;
		    case 'T': 
		    	reverse[length - i - 1] = 'A';
		    	break;
		    case 'C': 
		    	reverse[length - i - 1] = 'G';
		    	break;
		    case 'G': 
		    	reverse[length - i - 1] = 'C';
		    	break;
		    default: 
		    	reverse[length - i - 1] = 'N';
		    	break;
		  }
		}
		return new String(reverse);
	}
	
}

