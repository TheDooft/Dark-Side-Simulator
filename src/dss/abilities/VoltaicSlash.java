package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;

public class VoltaicSlash extends Ability {
	static CombatEngine engine;

	public VoltaicSlash(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		int dmg = 0;
		log = CombatLog.getInstance();
		for (int i = 1 ; i <= 2 ; i++){
			log.write(" flury("+i+") ");
			dmg += engine.weapondamage(0.8, -0.465, 0, 0, 0.08, 0.08, 1380, true);
			log.write(" / ");
		}
		log.write(" total : "+dmg+" damage)");
	}
}
