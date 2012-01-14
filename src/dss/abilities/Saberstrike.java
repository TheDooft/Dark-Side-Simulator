package dss.abilities;

import dss.CombatEngine;
import dss.model.Ability;

public class Saberstrike extends Ability {

	static CombatEngine engine;

	public Saberstrike(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}

	public String getName() { // TO FIX - to delete when trad works
		return "Saber Strike";
	}

	public void doNext() {
		engine.weapondamage(1, 0, 0, 0, 0, 0, 180, false);
		engine.setGcd(1500);
	}

}
