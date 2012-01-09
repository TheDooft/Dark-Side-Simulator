package dds.model;

public class Skill {

	private final String name;
	private int value;
	
	public Skill(String name) {
		this.name = name;
		this.value = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
		
}
