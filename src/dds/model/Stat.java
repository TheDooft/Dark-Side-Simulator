package dds.model;

public class Stat {

	private final String name;
	private int value;

	Stat(String name){
		this.name = name;
		this.value = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
