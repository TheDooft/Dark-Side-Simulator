package dds.gui.tabs;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import dds.model.Model;
import dds.model.Stat;

public class StatsTab extends JPanel {

	private static final long serialVersionUID = 2162071573513203343L;

	public StatsTab(Model model) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		for (Stat stat : model.getStats()) {
			this.add(new StatField(stat));
		}
	}

}
