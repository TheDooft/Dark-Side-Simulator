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
		int dmg = 0;
		log = CombatLog.getInstance();
		for (int i = 1 ; i <= 2 ; i++){
			log.write(" flury("+i+") ");
			dmg += engine.weapondamage(0.74, -0.505, 0, 0, 0.074, 0.074, 180, true);
			log.write(" / ");
		}
		log.write(" total : "+dmg+" damage)");
	}
}