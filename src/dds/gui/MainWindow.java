package dds.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import dds.gui.tabs.OptionsTab;
import dds.gui.tabs.ResultsTab;
import dds.gui.tabs.SpellsTab;
import dds.gui.tabs.StatsTab;
import dds.gui.tabs.SkillsTab;
import dds.gui.tabs.WeaponsTab;
import dds.model.DataModel;

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
		
		setSize(800,600);
		setTitle("Dark Side Simulator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Stats", new StatsTab(model));
		tabs.addTab("Skills", new SkillsTab());
		tabs.addTab("Weapons", new WeaponsTab());
		tabs.addTab("Spells", new SpellsTab());
		tabs.addTab("Options", new OptionsTab());
		tabs.addTab("Results", new ResultsTab());
		getContentPane().add(tabs);
		
	}
	
}
