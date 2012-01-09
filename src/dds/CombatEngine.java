package dds;

import dds.model.DataModel;
import dds.model.GenerationListener;

public class CombatEngine {

	int willpower;
	int strenght;
	int critical;
	int alacrity;
	int power;
	int surge;
	private final DataModel model;

	public CombatEngine(DataModel model) {
		this.model = model;
		this.model.addGenerationListener(new GenerationListener() {
			
			@Override
			public void generate() {
				run();
			}
		});
		run();
	}
	
	
	public void run() {
		willpower = model.getStat("willpower").getValue();
		strenght = model.getStat("strenght").getValue();
		critical = model.getStat("critrate").getValue();
		alacrity = model.getStat("alacrity").getValue();
		power = model.getStat("power").getValue();
		surge = model.getStat("surge").getValue();

		System.out.println("willpower: "+ willpower);
		System.out.println("strenght: "+ strenght);
		System.out.println("critical: "+ critical);
		System.out.println("alacrity: "+ alacrity);
		System.out.println("power: "+ power);
		System.out.println("surge: "+ surge);
	}
	
}
