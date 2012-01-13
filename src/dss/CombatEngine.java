package dss;

import java.util.ArrayList;
import java.util.List;

import dss.abilities.Saberstrike;
import dss.model.Ability;
import dss.model.DataModel;
import dss.model.GenerationListener;

public class CombatEngine {

	int willpower;
	int strenght;
	int critical;
	int alacrity;
	int power;
	int surge;
	int dmgDone;
	int gcd;
	double normalHitChance;
	double critChance;
	double critSize;
	int accuracyRating;
	private DataModel model;
	private static CombatEngine combatEngine;
	
	private CombatEngine() {
	}

	public void SetModel(DataModel model) {
		this.model = model;
		this.model.addGenerationListener(new GenerationListener() {

			@Override
			public void generate() {
				run();
			}
		});
		//run();
	}

	public static CombatEngine getInstance() {
		if (null == combatEngine) {
			combatEngine = new CombatEngine();
		}
		return combatEngine;
	}

	// Effects
	// 1 = WeaponDamage

	public void setGcd(int p_gcd) {
		this.gcd = p_gcd;
	}

	public void calculatePercent(){
		this.normalHitChance = 90 + 30 * ( 1 - Math.pow(( 1 - ( 0.01 / 0.3 ) ),( ( this.accuracyRating / 50 ) / 0.55 )) );
		this.critChance = 5 + 30 * ( 1 - Math.pow( 1 - ( 0.01 / 0.3 ),( this.willpower / 50 ) / 2.5 ) ) + 30 * ( 1 - Math.pow( 1 - ( 0.01 / 0.3 ) , ( critical / 50 ) / 0.45 ) );
		this.critSize = 50 + 50 * ( 1 - Math.pow( 1 - ( 0.01 / 0.5 ) , ( this.surge/ 50 ) / 0.1 ) );
		System.out.println("Crit Chance : " + this.critChance + "%");
		System.out.println("Hit Chance : " + this.normalHitChance + "%");
		System.out.println("Crit Size : " + this.critSize + "%");
	}
	
	public boolean basic_hit(){
		Log log;
		
		log = Log.getInstance();
		double rand = Math.random();
		if (rand <= (this.normalHitChance / 100)){
			return true;
		}else{
			log.writeln("miss.");
			return false;
		}
	}
	
	public boolean crit()
	{
		double rand = Math.random();
		if (rand <= (this.critChance / 100))
			return true;
		else
			return false;
	}
	
	public void weapondamage(float coefficient, float amountmodifierpercent,
			float amountmodifiermin, float amountmodifiermax,
			float standardhealthpercentmin, float standardhealthpercentmax,
			int standardhealth, boolean special) {

		int dmgMin;
		int dmgMax;
		int dmg;
		int dmgBonus = 0;
		Log log = Log.getInstance();
		
		if (!special && !this.basic_hit()){
			return;
		}
		
		dmgBonus = (int) (this.willpower * 0.2 + this.strenght * 0.2);
		dmgMin = (int) ((amountmodifierpercent + 1)
				* model.getStat("minweapondmg").getValue() + coefficient
				* dmgBonus + standardhealthpercentmin * standardhealth);
		dmgMax = (int) ((amountmodifierpercent + 1)
				* model.getStat("maxweapondmg").getValue() + coefficient
				* dmgBonus + standardhealthpercentmax * standardhealth);
		dmg = (int) (Math.random() * (1 + dmgMax - dmgMin)) + dmgMin;
		if (this.crit())
		{
			log.write("crits");
			dmg *= 1 + (this.critSize / 100);
		}else{
			log.write("hits");
		}
		this.dmgDone += dmg;
		log.writeln(" for " + dmg + " damage.");
		
	}

	public void run() {
		int time = 0;
		int i;
		int maxtime = 60000;
		int force = 140;
		int last_force_regen = 0;
		willpower = model.getStat("willpower").getValue();
		strenght = model.getStat("strenght").getValue();
		critical = model.getStat("critrate").getValue();
		alacrity = model.getStat("alacrity").getValue();
		power = model.getStat("power").getValue();
		surge = model.getStat("surge").getValue();
		this.accuracyRating = model.getStat("accuracy").getValue();
		Saberstrike saberstrike = new Saberstrike();
		List<Ability> ability_list = new ArrayList<Ability>();
		Ability current_ability;
		Log log;
		
		log = Log.getInstance();
		calculatePercent();
		this.dmgDone = 0;
		ability_list.add(saberstrike);
		while (time < maxtime) {
			if (this.gcd > 0)
				gcd--;
			else {
				for (i = 0; i < ability_list.size(); i++) {
					current_ability = ability_list.get(0);
					if (current_ability.cast(force, time) == 0) {
						log.write("player ");
						force -= current_ability.getCost();
						current_ability.doNext();
					}
				}
			}
			// here periodic management;
			if (last_force_regen == 0) {
				force += 8;
				last_force_regen = 1000;
				if (force > 140)
					force = 140;
			} else {
				last_force_regen--;
			}
			time++;
			
		}
		log.close(); // TO FIX with thread, shutdown hook or whatever
		float dps = Math.round((double) this.dmgDone
				/ ((double) maxtime / 1000.));
		System.out.println("DPS: " + dps);
	}

}
