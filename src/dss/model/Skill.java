package dss.model;

public class Skill {

	private final String name;
	private final String tag;
	private int value;

	public Skill(String tag, String name) {
		this.tag = tag;
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

	public String getTag() {
		return tag;
	}
}
