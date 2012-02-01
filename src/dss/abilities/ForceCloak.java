package dss.abilities;

import dss.CombatEngine;
import dss.model.Ability;

public class ForceCloak extends Ability {
	static CombatEngine engine;

	public ForceCloak(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	@Override
	public void doNext() {
		engine.addAlteration("stealth", engine.getPlayer());
	}
}
