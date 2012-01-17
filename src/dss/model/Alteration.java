package dss.model;

public class Alteration {
	private AlterationType type;
	private int maxDuration;
	private int ttl;
	private int curStack;
	private int maxStack;
	private String name;
	
	public Alteration(String name,AlterationType type, int maxDuration, int maxStack) {
		this.name = name;
		this.type = type;
		this.maxDuration = maxDuration;
		this.curStack = 0;
		this.maxStack = maxStack;
	}
	
	public String getName(){
		return name;
	}
	
	public void apply(){
		this.ttl = this.maxDuration;
		if (this.curStack < this.maxStack)
			this.curStack++;
	}
	
	public int getTTL(){
		return this.ttl;
	}
	
	public int getNumberOfStack(){
		return this.curStack;
	}
	
	public AlterationType getType(){
		return type;
	}
}
