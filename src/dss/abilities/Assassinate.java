package dss.abilities;
import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;


public class Assassinate extends Ability {
	static CombatEngine engine;

	public Assassinate(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		int dmg;
		log = CombatLog.getInstance();
		log.write(engine.getTimeStr() + this.getName() + " ");
		dmg = engine.weaponDamage(3.09,1.06,0,0,0.309,0.309,1610,false);
		engine.dealDamage(dmg);
		if (dmg > 0)
			log.writeln(" for " + dmg + " damage.");
		engine.getPlayer().getClassMechanics().onAttack();
	}
}
