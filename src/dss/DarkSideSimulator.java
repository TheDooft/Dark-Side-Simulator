package dss;

import dss.gui.Gui;
import dss.model.DataModel;
import dss.model.DataModelFactory;

public class DarkSideSimulator {
	public static void main(String[] args) {

		DataModelFactory modelFactory = new DataModelFactory();

		DataModel dataModel = modelFactory.load("sith_assassin");

		Gui gui = new Gui(dataModel);
		CombatEngine combeng = CombatEngine.getInstance();
		combeng.SetModel(dataModel);
		gui.start();

	}
}
