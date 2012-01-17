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
		log = CombatLog.getInstance();
		for (int i = 1 ; i <= 3 ; i++){
			log.write(engine.getTimeStr() + this.getName() + " flury("+i+") ");
			engine.weapondamage(0.33, -0.66, 0, 0, 0, 0, 180, false);
		}
	}

}
