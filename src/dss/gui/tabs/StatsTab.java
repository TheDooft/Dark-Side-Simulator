package dss.gui.tabs;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import dss.model.DataModel;
import dss.model.Stat;

public class StatsTab extends JPanel {

	private static final long serialVersionUID = 2162071573513203343L;

	public StatsTab(DataModel model) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		for (Stat stat : model.getStats()) {
			this.add(new StatField(stat));
		}
	}

}
