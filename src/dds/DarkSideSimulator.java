package dds;

import dds.gui.Gui;
import dds.model.Model;

public class DarkSideSimulator {
	public static void main(String[] args) {
		
		
		
		Model model = new Model();
		
		Gui gui = new Gui(model);
		CombatEngine combeng = new CombatEngine(model);
		gui.start();
		
	}
}
