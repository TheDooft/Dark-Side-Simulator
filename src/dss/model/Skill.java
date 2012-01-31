package dss.model;

public class Skill {

	private final String name;
	private final String tag;
	private int value;
	private int maxValue;
	private int rank;
	private int position;
	private String iconName;
	private Skill dependency;
	private SkillTree parentTree;

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

	public void setDependency(Skill dependency) {
		this.dependency = dependency;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setParentTree(SkillTree parentTree) {
		this.parentTree = parentTree;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Skill getDependency() {
		return dependency;
	}

	public String getIconName() {
		return iconName;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public SkillTree getParentTree() {
		return parentTree;
	}

	public int getPosition() {
		return position;
	}

	public int getRank() {
		return rank;
	}

	public String getIconPath() {
		return "data/img/medium/" + getIconName() + ".jpg";
	}
	
	public boolean canDecrement() {
		if (getValue() <= 0) {
			return false;
		}
		return true;
	}

	public boolean canIncrement() {
		if (!isValid()) {
			return false;
		}

		if (getValue() >= getMaxValue()) {
			return false;
		}

		return true;
	}

	public boolean isValid() {
		if (getParentTree().getTotalPoints(getRank()) / 5 < getRank() - 1) {
			return false;
		}
		if (getDependency() != null && getDependency().getValue() < getDependency().getMaxValue()) {
			return false;
		}

		return true;
	}
}
