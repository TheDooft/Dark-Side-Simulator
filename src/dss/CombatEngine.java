package dss;

import java.text.NumberFormat;
import java.util.List;

import dss.model.Ability;
import dss.model.Ability.CastResult;
import dss.model.DataModel;
import dss.model.Entity;
import dss.model.GenerationListener;
import dss.tools.MathTools;

public class CombatEngine {

	int willpower;
	int strenght;
	int critical;
	int alacrity;
	int power;
	int forcepower;
	int surge;
	int dmgDone;
	int gcd;
	int time;
	private Entity player;
	private Entity enemy;
	double normalHitChance;
	double critChance;
	double critSize;
	double meleeDamageBonus;
	double forceDamageBonus;
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
		// run();
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
	
	public String getTimeStr(){
		NumberFormat format = NumberFormat.getInstance();
		int ms = this.time;
		int m = (int) Math.floor((double)ms / 60000.);
		ms -= m * 60000;
		int s = (int) Math.floor((double)ms / 1000.);
		ms -= s * 1000;
		format.setMinimumIntegerDigits(2);
		String min = format.format(m);
		String sec = format.format(s);
		format.setMinimumIntegerDigits(3);
		String msec = format.format(ms);
		return "["+min+":"+sec+"."+msec+"]";
		
	}

	public void calculatePercent() {
		MathTools math = new MathTools();
		
		double hitFromAccuracy = math.round(30. * (1. - Math.pow((1. - (0.01 / 0.3)), (((double)this.accuracyRating / 50.) / 0.55))),2);
		this.normalHitChance = 90. + hitFromAccuracy ;	
		
		double critFromPrimary = 30. * (1. - Math.pow(1. - (0.01 / 0.3),((double)this.willpower / 50.) / 2.5));
		double critFromSecondary = 30. * (1. - Math.pow(1. - (0.01 / 0.3),((double)this.strenght / 50.) / 2.5));
		double critFromRating = 30. * (1. - Math.pow(1. - (0.01 / 0.3),((double)this.critical / 50.) / 0.45));
		double critFromSkill = 3;	
		this.critChance = math.round(5 + critFromRating + critFromSkill + critFromSecondary + critFromPrimary,2);
		
		double sizeFromSurge = math.round(50. * (1. - Math.pow(1. - (0.01 / 0.5), ((double)this.surge / 50.) / 0.1)),2);
		this.critSize = 50. + sizeFromSurge;
		
		double dmgBonusFromPrimary = this.willpower * 0.2;
		double dmgBonusFromSecondary = this.strenght * 0.2;
		double dmgBonusFromPower = this.power * 0.23;
		this.meleeDamageBonus = math.round(dmgBonusFromPower + dmgBonusFromPrimary + dmgBonusFromSecondary,1);
		
		double dmgBonusFromForcePower = this.forcepower * 0.23;
		this.forceDamageBonus = math.round(dmgBonusFromPower + dmgBonusFromPrimary + dmgBonusFromForcePower,1);
		
		System.out.println("Melee Damage Bonus : " + this.meleeDamageBonus);
		System.out.println("Crit Chance : " + this.critChance + "%");
		System.out.println("Hit Chance : " + this.normalHitChance + "%");
		System.out.println("Crit Size : " + this.critSize + "%");
		System.out.println("Force Damage Bonus : " + this.forceDamageBonus);
	}

	public boolean basicHit() {
		CombatLog log;

		log = CombatLog.getInstance();
		double rand = Math.random();
		if (rand <= (this.normalHitChance / 100)) {
			return true;
		} else {
			log.write("miss");
			return false;
		}
	}

	public boolean crit() {
		double rand = Math.random();
		if (rand <= (this.critChance / 100))
			return true;
		else
			return false;
	}

	public int weapondamage(double coefficient, double amountmodifierpercent, double amountmodifiermin,
			double amountmodifiermax, double standardhealthpercentmin, double standardhealthpercentmax,
			int standardhealth, boolean special) {

		int dmgMin;
		int dmgMax;
		int dmg;

		CombatLog log = CombatLog.getInstance();
		
		if (!special && !this.basicHit()) {
			log.writeln(" miss.");
			return 0;
		}

		dmgMin = (int) ((amountmodifierpercent + 1) * model.getStat("minweapondmg").getValue() + coefficient * this.meleeDamageBonus + standardhealthpercentmin
				* standardhealth);
		dmgMax = (int) ((amountmodifierpercent + 1) * model.getStat("maxweapondmg").getValue() + coefficient * this.meleeDamageBonus + standardhealthpercentmax
				* standardhealth);
		dmg = (int) (Math.random() * (1 + dmgMax - dmgMin)) + dmgMin;
		if (this.crit()) {
			log.write("crits");
			dmg *= 1 + (this.critSize / 100);
		} else {
			log.write("hits");
		}
		this.dmgDone += dmg;
		log.writeln(" for " + dmg + " damage.");
		return dmg;
	}

	public Entity getPlayer(){
		return this.player;
	}
	
	public Entity getEnemy(){
		return this.enemy;
	}
	
	public void addAlteration(String name,Entity e){
		e.addAlteration(model.getAlteration(name), this.time);
	}
	
	public void run() {
		
		int maxTime = 60000;
		int force = 100;
		int lastForceRegen = 0;
		willpower = model.getStat("willpower").getValue();
		strenght = model.getStat("strenght").getValue();
		critical = model.getStat("critrate").getValue();
		alacrity = model.getStat("alacrity").getValue();
		power = model.getStat("power").getValue();
		forcepower = model.getStat("forcepower").getValue();
		surge = model.getStat("surge").getValue();
		this.accuracyRating = model.getStat("accuracy").getValue();
		List<Ability> abilityList = model.getSelectedAbilities();
		CombatLog log;

		log = CombatLog.getInstance();
		log.init();
		time = 0;
		calculatePercent();
		this.dmgDone = 0;
		player = new Entity("Player");
		enemy = new Entity("Enemy");
		enemy.init(50000, 9000);
		player.init(0, 0);
		while (time < maxTime) {
			if (this.gcd > 0)
				gcd--;
			else {
				for (Ability current_ability : abilityList) {
					if (current_ability.cast(force, time) == CastResult.SUCCESS) {
						force -= current_ability.getCost();
						current_ability.doNext();
						this.setGcd(1500);
						break;
					}
				}
			}
			// Alteration management
			player.refreshAlterations(this.time);
			enemy.refreshAlterations(this.time);
			// Resource management
			if (lastForceRegen == 0) {
				force += 8;
				lastForceRegen = 1000;
				if (force > 100)
					force = 100;
			} else {
				lastForceRegen--;
			}
			time++;

		}
		log.close();
		float dps = Math.round((double) this.dmgDone / ((double) maxTime / 1000.));
		System.out.println("DPS: " + dps);
	}

}
