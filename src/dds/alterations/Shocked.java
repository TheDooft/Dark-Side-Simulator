package dds.alterations;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Alteration;
import dss.model.AlterationType;

public class Shocked extends Alteration {
	
	public Shocked(String name,AlterationType type, int maxDuration, int maxStack) {
		super(name,type,maxDuration,maxStack);
	}
	
	@Override
	public void tick() {
		CombatLog log;
		CombatEngine engine = CombatEngine.getInstance();
		int dmg;
		log = CombatLog.getInstance();
		log.write(engine.getTimeStr() + this.getName() + " ");
		dmg = engine.spellDamage(0.435,0.01,0,0,0.044,0.044,1610,false);
		engine.dealDamage(dmg);
		log.writeln(" for " + dmg + " damage.");
	}
}
