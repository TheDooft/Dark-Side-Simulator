package dss.ClassMechanics;

import dss.CombatEngine;
import dss.model.Alteration;
import dss.model.ClassMechanics;

public class SithAssassin extends ClassMechanics {
	CombatEngine engine;
	int lastRegen;
	
	public SithAssassin() {
		engine = CombatEngine.getInstance();
		this.maxRessource = 100 + engine.talentRank("deceptivePower") * 10;
		this.ressource = this.maxRessource;
		this.lastRegen = 0;
	}
	
	public boolean spendRessource(int amount){
		if (amount <= this.ressource)
			this.ressource -= amount;
		else
			return false;
		return true;
	}
	
	public void regen (){
		Alteration buff = this.engine.getPlayer().getAlterations().get("darkEmbrace");
		int amount = 8;
		
		if (buff != null)
			amount *= 1 + engine.talentRank("darkEmbrace") * 0.25; 
		if (this.lastRegen == 0) {
			this.ressource += amount;
			this.lastRegen = 1000;
			if (this.ressource > this.maxRessource)
				this.ressource = this.maxRessource;
		} else {
			this.lastRegen--;
		}
	}
}
