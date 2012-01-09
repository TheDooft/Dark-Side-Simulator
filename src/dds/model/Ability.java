package dds.model;

public class Ability {

	private final String name;
	private final String tag;
	private int value;

	Ability(String tag, String name){
		this.tag = tag;
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
	
	public String getTag() {
		return tag;
	}
	
}
