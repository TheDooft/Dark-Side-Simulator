package dss.model;

import dss.CombatEngine;
import dss.CombatLog;

public class Entity {
	private MappedList<String, Alteration> alterations;
	private int maxHealth;
	private int health;
	private int armor;
	private String name;
	
	public Entity(String name){
		this.name = name;
		this.alterations = new MappedList<String, Alteration>();
	}
	
	public void addAlteration(Alteration alteration, int time){
		CombatLog log;
		CombatEngine engine;
		
		log = CombatLog.getInstance();
		engine = CombatEngine.getInstance();
		
		alteration.apply(time);
		this.alterations.add(alteration.getName(), alteration);
		log.write(engine.getTimeStr() + this.name + " gains " + alteration.getName());
		if (alteration.getNumberOfStack() > 1)
			log.write("(" + alteration.getNumberOfStack() + ")");
		log.writeln(".");
	}
	
	public void refreshAlterations(int time){
		if (alterations == null)
		for (Alteration alteration : this.alterations){
			if (!alteration.refresh(time))
				this.removeAlteration(alteration.getName());
		}
	}
	
	public void removeAlteration(String name){
		CombatLog log;
		CombatEngine engine;
		
		log = CombatLog.getInstance();
		engine = CombatEngine.getInstance();
		log.write(engine.getTimeStr()+ this.alterations.get(name).getName() + " fades on " + this.name + ".");
		this.alterations.remove(name);
	}
	
	public void init(int health,int armor){
		this.maxHealth = health;
		this.health = maxHealth;
		this.armor = armor;
	}
	
	public int getArmor(){
		return this.armor;
	}
	
	public double getPercentLife(){
		return ((double)this.health / (double)this.maxHealth) * 100.;
	}
	
	public boolean doDamage(int dmg){
		this.health -= dmg;
		return (this.health > 0);
	}
}
