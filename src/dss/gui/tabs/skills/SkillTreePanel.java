package dss.gui.tabs.skills;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dss.model.DataModel;
import dss.model.Skill;
import dss.model.SkillTree;

public class SkillTreePanel extends JPanel {

	private static final long serialVersionUID = 4555339590477285307L;
	private final DataModel model;

	public SkillTreePanel(SkillTree skillTree, DataModel model) {
		this.model = model;
		List<Skill> skills = skillTree.getSkills();

		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel title = new JLabel(skillTree.getName());
		title.setFont(title.getFont().deriveFont(16f));
		title.setFont(title.getFont().deriveFont(title.getFont().getStyle() ^ Font.BOLD));
		
		add(title, BorderLayout.NORTH);

		add(generateTreePanel(skills), BorderLayout.CENTER);

	}

	private JPanel generateTreePanel(List<Skill> skills) {

		JPanel treePanel = new JPanel();
		treePanel.setLayout(new GridLayout(7, 4));
		treePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		
		
		JComponent[] components = new JComponent[28];
		
		for(int i = 0; i< 28; i++) {
			components[i] =  new JLabel("");
		}
		
		for (Skill skill : skills) {
			int index = (7 - skill.getRank())*4 + (skill.getPosition() - 1);
			components[index] = new SkillField(skill, model);
			
		}
		
		for(JComponent c : components) {
			treePanel.add(c);
		}
		
		

		return treePanel;
	}
}
