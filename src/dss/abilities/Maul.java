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
		int dmg;
		log = CombatLog.getInstance();
		log.write(engine.getTimeStr() + this.getName() + " ");
		dmg = engine.weaponDamage(0.236, 0.58, 0, 0, 0.236, 0.236, 500, true);
		engine.dealDamage(dmg);
		if (dmg > 0)
			log.writeln(" for " + dmg + " damage.");
	}
}
