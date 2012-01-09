package dds;

import dds.gui.Gui;
import dds.model.DataModel;

public class DarkSideSimulator {
	public static void main(String[] args) {
		
		
		
		DataModel model = new DataModel();
		
		Gui gui = new Gui(model);
		CombatEngine combeng = new CombatEngine(model);
		gui.start();
		
	}
}
