package dss.gui.tabs.skills;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import dss.model.DataModel;
import dss.model.SkillTree;

public class SkillsTab extends JPanel {

	private static final long serialVersionUID = 8799701636473178811L;

	public SkillsTab(DataModel model) {
		

		List<SkillTree> skillTrees = model.getSkillTrees();
		
		setLayout(new GridLayout(1, skillTrees.size()));
		
		
		
		for (SkillTree skillTree : skillTrees) {
			this.add(new SkillTreePanel(skillTree));
		}
	}
}
