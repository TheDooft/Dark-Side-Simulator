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
		int dmg = 0;
		log = CombatLog.getInstance();
		// engine.weapondamage(1, 0, 0, 0, 0, 0, 180, false);
		for (int i = 1 ; i <= 3 ; i++){
			log.write("flury("+i+") ");
			dmg += engine.weapondamage(0.33, -0.66, 0, 0, 0, 0, 180, false);
			log.write(" / ");
		}
		log.write("total : "+dmg + " damage");
	}

}
