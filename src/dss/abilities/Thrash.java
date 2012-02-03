package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;

public class Thrash extends Ability {
	static CombatEngine engine;

	public Thrash(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		int dmg;
		log = CombatLog.getInstance();
		for (int i = 1 ; i <= 2 ; i++){
			log.write(engine.getTimeStr() + this.getName() + " flury("+i+") ");
			dmg = engine.weaponDamage(0.74, -0.505, 0, 0, 0.074, 0.074, 1610, true);
			engine.dealDamage(dmg);
			if (dmg > 0)
				log.writeln(" for " + dmg + " damage.");
		}
	}
}