package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;

public class DeathField extends Ability {
	static CombatEngine engine;

	public DeathField(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		int dmg;
		log = CombatLog.getInstance();

		if (engine.getTalentRank("deathField") > 0)
			return; // related skill not here !
		log.write(engine.getTimeStr() + this.getName() + " ");
		dmg = engine.spellDamage(1.87,0.01,0,0,0.167,0.207,790,false);
		engine.dealDamage(dmg);
		if (dmg > 0)
			log.writeln(" for " + dmg + " damage.");
		engine.getPlayer().getClassMechanics().onAttack();
	}
}
