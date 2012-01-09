package dds;

import dds.model.Model;

public class CombatEngine {
	private final Model model;

	int willpower;
	int strenght;
	int critical;
	int alacrity;
	int power;
	int surge;

	public CombatEngine(Model model) {
		this.model = model;
	}

}
