package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;
import dss.model.Alteration;

public class Discharge extends Ability {
	static CombatEngine engine;

	public Discharge(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}

	public void doNext() {
		CombatLog log;
		int dmg;
		log = CombatLog.getInstance();
		Alteration charge = engine.getPlayer().getAlterations().get("surgingCharge");

		if (charge != null) {
			log.write(engine.getTimeStr() + this.getName() + " ");
			dmg = engine.weaponDamage(1.74, 0.01, 0, 0, 0.154, 0.194, 1610, false);
			engine.dealDamage(dmg);
			if (dmg > 0)
				log.writeln(" for " + dmg + " damage.");
		}

		charge = engine.getPlayer().getAlterations().get("lightningCharge");
		if (charge != null) {
			log.write(engine.getTimeStr() + this.getName() + " casted.");
			engine.addAlteration("shocked", engine.getEnemy());
		}
	}
}
