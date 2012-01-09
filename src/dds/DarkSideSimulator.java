package dds;

import dds.gui.Gui;
import dds.model.DataModel;
import dds.model.DataModelFactory;

public class DarkSideSimulator {
	public static void main(String[] args) {
		
		
		
		DataModelFactory modelFactory = new DataModelFactory();
		
		DataModel dataModel = modelFactory.load("sith_assassin");
		
		Gui gui = new Gui(dataModel);
		CombatEngine combeng = new CombatEngine(dataModel);
		gui.start();
		
	}
}
