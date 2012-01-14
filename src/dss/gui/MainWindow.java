package dss.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import dss.gui.tabs.OptionsTab;
import dss.gui.tabs.ResultsTab;
import dss.gui.tabs.SkillsTab;
import dss.gui.tabs.StatsTab;
import dss.gui.tabs.WeaponsTab;
import dss.gui.tabs.abilities.AbilitiesTab;
import dss.model.DataModel;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 327992969446679713L;
	private final DataModel model;

	public MainWindow(DataModel model) {
		this.model = model;
		init();
	}

	private void init() {

		setSize(800, 600);
		setTitle("Dark Side Simulator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Stats", new StatsTab(model));
		tabs.addTab("Skills", new SkillsTab(model));
		tabs.addTab("Weapons", new WeaponsTab());
		tabs.addTab("Abilities", new AbilitiesTab(model));
		tabs.addTab("Options", new OptionsTab());
		tabs.addTab("Results", new ResultsTab(model));
		getContentPane().add(tabs);

	}

}
