package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;

public class Maul extends Ability {
	static CombatEngine engine;

	public Maul(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		log = CombatLog.getInstance();
		log.write(engine.getTimeStr() + this.getName() + " ");
		engine.weapondamage(0.236, 0.58, 0, 0, 0.236, 0.236, 500, true);
	}
}
