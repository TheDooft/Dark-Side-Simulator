package dss.gui.tabs;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import dss.model.DataModel;
import dss.model.Skill;

public class SkillsTab extends JPanel {

	private static final long serialVersionUID = 8799701636473178811L;

	
	public SkillsTab(DataModel model) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		for (Skill stat : model.getSkills()) {
			this.add(new SkillField(stat));
		}
	}
}
