package dss.classmechanics;

import dss.CombatEngine;
import dss.CombatLog;
import dss.model.Alteration;
import dss.model.ClassMechanics;
import dss.tools.MathTools;

public class SithAssassin extends ClassMechanics {
	CombatEngine engine;
	int lastRegen;
	int lastSurgingCharge;
	
	public SithAssassin() {
		engine = CombatEngine.getInstance();
	}
	
	public boolean spendRessource(int amount){
		if (amount <= this.ressource)
			this.ressource -= amount;
		else
			return false;
		return true;
	}
	
	public void init (){
		this.maxRessource = 100 + engine.getTalentRank("deceptivePower") * 10;
		this.ressource = this.maxRessource;
		this.lastRegen = 0;
		this.lastSurgingCharge = -1501;
		// 
		if (engine.getTalentRank("surgingStrike") > 1)
			engine.getPlayer().addAlteration(engine.getModel().getAlteration("surgingCharge"));
		else
			engine.getPlayer().addAlteration(engine.getModel().getAlteration("lightningCharge"));
	}
	
	public void regen (){
		Alteration buff = this.engine.getPlayer().getAlterations().get("darkEmbrace");
		int amount = 8;
		
		if (buff != null)
			amount *= 1 + engine.getTalentRank("darkEmbrace") * 0.25; 
		if (this.lastRegen == 0) {
			this.ressource += amount;
			this.lastRegen = 1000;
			if (this.ressource > this.maxRessource)
				this.ressource = this.maxRessource;
		} else {
			this.lastRegen--;
		}
	}
	
	public void onAbilityUse(){
		Alteration buff;
		
		buff = engine.getPlayer().getAlterations().get("stealth");
		if (buff != null){
			if (engine.getTalentRank("darkEmbrace") > 0)
				engine.getPlayer().addAlteration(engine.getModel().getAlteration("darkEmbrace"));
			engine.getPlayer().removeAlteration("stealth");
		}
	}
	
	@Override
	public void onMeleeAttack() {
		Alteration buff;
		MathTools math = new MathTools();
		CombatLog log = CombatLog.getInstance();
		int dmg;
		
		// Proc surging charge
		buff = engine.getPlayer().getAlterations().get("surgingCharge");
		if (buff != null && (engine.getTime() - this.lastSurgingCharge) >= 0){
			if (math.chance(25.0)){
				log.write(engine.getTimeStr() + "Surging Charge ");
				dmg = engine.weaponDamage(0.344,0.01,0,0,0.034,0.034,790,false);
				engine.dealDamage(dmg);
				log.writeln(" for " + dmg + " damage.");
			}
		}
		
		// Proc lightning charge
		
	}
}
