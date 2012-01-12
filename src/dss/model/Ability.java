package dss.model;

public class Ability {

	private final String name;
	private final String tag;
	private int cost;
	private int	cooldown;
	private int casttime;
	private int gcd;
	private int timer;
	private int previous_time;
	private int number_of_effect;
	
	protected Ability(String tag, String name){
		this.tag = tag;
		this.name = name;
		this.cost = 0;
		this.cooldown = 0;
		this.casttime = 0;
		this.gcd = 0;
		this.previous_time = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCost(int value) {
		this.cost = value;
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
	
	public int getGcd(){
		return this.gcd;
	}

	public int getNumberOfEffect(){
		return this.number_of_effect;
	}
	
	// 	Cast 
	public int cast(int ressource, int time){
		this.timer -= time - this.previous_time;
		this.previous_time = time;
		if (ressource <= this.cost){
			return 1;	// not enough force
		}
		if (timer > 0)
			return 2; // still on cooldown
		this.timer = this.cooldown;
		return 0; // Success !
	}
	
	public void doNext(){
		
	}
}
