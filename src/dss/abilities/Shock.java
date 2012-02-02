package dss.abilities;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Ability;
import dss.model.Alteration;
import dss.tools.MathTools;

public class Shock extends Ability {
	static CombatEngine engine;

	public Shock(String tag, String name) {
		super(tag, name);
		engine = CombatEngine.getInstance();
	}
	
	public void doNext() {
		CombatLog log;
		int dmg;
		int procChance;
		Alteration voltaicSlash = engine.getPlayer().getAlterations().get("voltaicslash");
		MathTools math = new MathTools();
		
		log = CombatLog.getInstance();
		log.write(engine.getTimeStr() + this.getName() + " ");
		dmg = engine.spellDamage(1.85,0.01,0,0,0.165,0.205,7085,true);
		// Cracking Blast Skill
		if (engine.getLastCrit() && (engine.talentRank("cracklingBlasts") > 0))
			dmg *= 1 + 0.1 * engine.talentRank("cracklingBlasts");
		// Voltaic Slash buff
		if (voltaicSlash != null){
			dmg *= 1 + 0.15 * voltaicSlash.getNumberOfStack();
			engine.getPlayer().removeAlteration("voltaicslash");
		}
		
		if (dmg > 0){
			log.writeln(" for " + dmg + " damage.");
			engine.dealDamage(dmg);
		}
		
		// Chain Shock
		procChance = engine.talentRank("chainShock");
		int rand = (int) math.round(Math.random()*100.0,0) + 1;
		if (rand <= procChance) {
			log.write(engine.getTimeStr() + this.getName() + "(Bonus) ");
			dmg = engine.spellDamage(0.925,0.01,0,0,0.073,0.113,7085,true);
			if (engine.getLastCrit() && engine.talentRank("cracklingBlasts") > 0)
				dmg *= 1 + 0.1 * engine.talentRank("cracklingBlasts");
			if (dmg > 0){
				log.writeln(" for " + dmg + " damage.");
				engine.dealDamage(dmg);
			}
		}
	}
}
