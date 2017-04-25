package src;

public class FastaRecord{
	public String name;
	public String sequence;
	
	public FastaRecord(String name, String sequence){
		this.name = name;
		this.sequence = sequence;
	}

	public String getName(){
		return this.name;
	}
	
	public String getSequence(){
		return this.sequence;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
	
	@Override
	public String toString(){
		String str = "";
		str = this.name + ": " + this.sequence; 
		return str;
	}
	
	
		
		
}