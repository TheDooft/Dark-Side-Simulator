package dss.model;

import java.util.ArrayList;
import java.util.List;

import dss.CombatEngine;
import dss.CombatLog;
import dss.classmechanics.SithAssassin;

public class Entity {
	private MappedList<String, Alteration> alterations;
	private int maxHealth;
	private int health;
	private int armor;
	private String name;
	private ClassMechanics classMech;

	public Entity(String name) {
		this.name = name;
		this.alterations = new MappedList<String, Alteration>();
	}

	public MappedList<String, Alteration> getAlterations() {
		return alterations;
	}

	public ClassMechanics getClassMechanics() {
		return this.classMech;
	}

	public void addAlteration(Alteration alteration) {
		CombatLog log;
		CombatEngine engine;
		log = CombatLog.getInstance();
		engine = CombatEngine.getInstance();
		int time = engine.getTime();

		alteration.apply(time);
		this.alterations.add(alteration.getTag(), alteration);
		log.write(engine.getTimeStr() + this.name + " gains " + alteration.getName());
		if (alteration.getNumberOfStack() > 1)
			log.write("(" + alteration.getNumberOfStack() + ")");
		log.writeln(".");
	}

	public void refreshAlterations(int time) {
		List<String> list = new ArrayList<String>(); 
		
		if (alterations != null)
			for (Alteration alteration : this.alterations) {
				if (!alteration.refresh(time))
					list.add(alteration.getTag());
			}
		
		for (String alteration : list)
			this.alterations.remove(alteration);
	}

	public void removeAlteration(String name) {
		CombatLog log;
		CombatEngine engine;

		log = CombatLog.getInstance();
		engine = CombatEngine.getInstance();
		log.write(engine.getTimeStr() + this.alterations.get(name).getName() + " fades on " + this.name + ".");
		this.alterations.remove(name);
	}

	public void init(int health, int armor) {
		this.maxHealth = health;
		this.health = maxHealth;
		this.armor = armor;
		if (this.name == "Player") {
			this.classMech = new SithAssassin();
			this.classMech.init();
		}
	}

	public int getArmor() {
		return this.armor;
	}

	public double getPercentLife() {
		return ((double) this.health / (double) this.maxHealth) * 100.;
	}

	public boolean doDamage(int dmg) {
		this.health -= dmg;
		return (this.health > 0);
	}
}
