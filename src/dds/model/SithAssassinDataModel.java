package dds.model;

import java.util.ArrayList;
import java.util.List;

import dds.model.skills.ChargeMastery;
import dds.model.skills.LightningReflexes;

public class SithAssassinDataModel extends DataModel {

	public Stat willpower;
	public Stat strenght;
	public Stat alacrity;
	public Stat accuracy;
	public Stat power;
	public Stat critrate;
	public Stat surge;

	public SithAssassinDataModel() {

		// Stats
		willpower = new Stat("Willpower");
		strenght = new Stat("Strenght");
		alacrity = new Stat("Alacrity");
		accuracy = new Stat("Accuracy");
		power = new Stat("Power");
		critrate = new Stat("Crit rate");
		surge = new Stat("Surge");

		stats.add(willpower);
		stats.add(strenght);
		stats.add(alacrity);
		stats.add(accuracy);
		stats.add(power);
		stats.add(critrate);
		stats.add(surge);
		
		//Skills
		skills.add(new ChargeMastery());
		skills.add(new LightningReflexes());
	}

	public List<Stat> getStats() {
		return stats;
	}
}
