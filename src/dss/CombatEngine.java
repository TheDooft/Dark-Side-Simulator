package dss;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

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
		run();
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

	public void weapondamage(float coefficient, float amountmodifierpercent,
			float amountmodifiermin, float amountmodifiermax,
			float standardhealthpercentmin, float standardhealthpercentmax,
			int standardhealth) {

		int dmg_min;
		int dmg_max;
		int dmg;
		int damagebonus = 0;

		damagebonus = (int) (this.willpower * 0.2 + this.strenght * 0.2);
		dmg_min = (int) ((amountmodifierpercent + 1)
				* model.getStat("minweapondmg").getValue() + coefficient
				* damagebonus + standardhealthpercentmin * standardhealth);
		dmg_max = (int) ((amountmodifierpercent + 1)
				* model.getStat("maxweapondmg").getValue() + coefficient
				* damagebonus + standardhealthpercentmax * standardhealth);
		dmg = (int) (Math.random() * (1 + dmg_max - dmg_min)) + dmg_min;
		this.dmgDone += dmg;
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
		Saberstrike saberstrike = new Saberstrike();
		List<Ability> ability_list = new ArrayList<Ability>();
		Ability current_ability;
		Log log;
		
		log = Log.getInstance();
		log.write("test");
		log.writeln("ok?");
		log.writeln("seems goods !");
		this.dmgDone = 0;
		ability_list.add(saberstrike);
		while (time < maxtime) {
			if (this.gcd > 0)
				gcd--;
			else {
				for (i = 0; i < ability_list.size(); i++) {
					current_ability = ability_list.get(0);
					if (current_ability.cast(force, time) == 0) {
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
			log.close(); // TO FIX with thread, shutdown hook or whatever
		}
		float dps = Math.round((double) this.dmgDone
				/ ((double) maxtime / 1000.));
		System.out.println("DPS: " + dps);
	}

}
