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
		CombatEngine engine;
		CombatLog log;
		log = CombatLog.getInstance();
		engine = CombatEngine.getInstance();
		for (int i = 1 ; i <= 2 ; i++){
			log.write(engine.getTimeStr() + this.getName() + " flury("+i+") ");
			engine.weapondamage(0.8, -0.465, 0, 0, 0.08, 0.08, 1380, true);
		}
		engine.addAlteration("voltaicslash", engine.getPlayer());
	}
}
