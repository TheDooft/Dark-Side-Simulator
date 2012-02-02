package dss.model;

public class Ability {

	private final String name;
	private final String tag;
	private String iconName;
	private int cost;
	private int cooldown;
	private int casttime;
	private int cooldownEndTime;
	private int number_of_effect;

	public enum CastResult {
		SUCCESS, NOT_ENOUGH_FORCE, STILL_ON_COOLDOWN,
	}

	protected Ability(String tag, String name) {
		this.tag = tag;
		this.name = name;
		this.cost = 0;
		this.cooldown = 0;
		this.casttime = 0;
		this.cooldownEndTime = 0;
	}
	
	public void init(){
		this.cooldown = 0;
		this.cooldownEndTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setCost(int value) {
		this.cost = value;
	}

	public void setCasttime(int casttime) {
		this.casttime = casttime;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getCost() {
		return cost;
	}

	public String getTag() {
		return tag;
	}

	public int getCasttime() {
		return this.casttime;
	}

	public int getNumberOfEffect() {
		return this.number_of_effect;
	}

	// Cast
	public CastResult cast(int ressource, int time) {
		if (ressource <= this.cost) {
			return CastResult.NOT_ENOUGH_FORCE;
		}
		if (time < cooldownEndTime)
			return CastResult.STILL_ON_COOLDOWN;
		this.cooldownEndTime = time + this.cooldown;
		return CastResult.SUCCESS;
	}

	public void doNext() {

	}

	public String getIconName() {
		return iconName;
	}

	public String getIconPath() {
		return "data/img/large/" + getIconName() + ".jpg";
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
}
