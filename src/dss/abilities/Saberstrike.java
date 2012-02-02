package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;

public class Saberstrike extends Ability {

	static CombatEngine engine;

	public Saberstrike(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}

	public void doNext() {
		CombatLog log;
		int dmg;
		log = CombatLog.getInstance();
		for (int i = 1 ; i <= 3 ; i++){
			log.write(engine.getTimeStr() + this.getName() + " flury("+i+") ");
			dmg = engine.weaponDamage(0.33, -0.66, 0, 0, 0, 0, 180, false);
			engine.dealDamage(dmg);
			if (dmg > 0)
				log.writeln(" for " + dmg + " damage.");
		}
	}

}
