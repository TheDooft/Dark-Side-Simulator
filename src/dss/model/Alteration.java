package dss.model;

public class Alteration {
	private AlterationType type;
	private int maxDuration;
	private int ttl;
	private int curStack;
	private int maxStack;
	private int lastRefresh;
	private String name;
	private int period;
	private int lastTick;
	private String tag;
	
	public Alteration(String name,AlterationType type, int maxDuration, int maxStack) {
		this.name = name;
		this.type = type;
		this.maxDuration = maxDuration;
		this.curStack = 0;
		this.maxStack = maxStack;
		this.lastRefresh = -1;
		this.period = -1;
		this.lastTick = -1;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getName(){
		return name;
	}
	
	public void apply(int time){
		this.ttl = this.maxDuration;
		this.lastRefresh = time;
		this.lastTick = time;
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
	
	public boolean refresh(int time){
		if (ttl < 0)
			return true;
		int diff = time - this.lastRefresh;
		ttl -= diff;
		
		if (this.lastTick + this.period >= time){
			this.lastTick += this.period;
			this.tick();
		}
		
		if (ttl <= 0){
			this.curStack = 0;
			return false;
		}
		return true;
	}
	
	public void tick() {
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
}
