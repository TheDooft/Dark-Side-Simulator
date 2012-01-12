package dss.abilities;

import dss.CombatEngine;
import dss.model.Ability;

public class Saberstrike extends Ability {
	
	static CombatEngine engine;
	
	public Saberstrike() {
		super("saberstrike", "abilities.name.saberstrike");
		engine = CombatEngine.getInstance();
	}
	
	public void doNext()
	{
		engine.weapondamage(1, 0, 0, 0, 0, 0, 180);
		engine.setGcd(300);
	}
	
}
