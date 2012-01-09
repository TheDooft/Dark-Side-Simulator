package dds;

import dds.model.DataModel;

public class CombatEngine {
	private final DataModel model;

	int willpower;
	int strenght;
	int critical;
	int alacrity;
	int power;
	int surge;

	public CombatEngine(DataModel model) {
		this.model = model;
	}
}
