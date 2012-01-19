package dss.gui.tabs.skills;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dss.model.Skill;
import dss.model.SkillTree;

public class SkillTreePanel extends JPanel {

	private static final long serialVersionUID = 4555339590477285307L;

	public SkillTreePanel(SkillTree skillTree) {
		List<Skill> skills = skillTree.getSkills();

		setLayout(new BorderLayout());

		add(new JLabel(skillTree.getName()), BorderLayout.NORTH);

		add(generateTreePanel(skills), BorderLayout.CENTER);

	}

	private JPanel generateTreePanel(List<Skill> skills) {

		JPanel treePanel = new JPanel();
		treePanel.setLayout(new GridLayout(7, 4));

		
		
		JComponent[] components = new JComponent[28];
		
		for(int i = 0; i< 28; i++) {
			components[i] =  new JLabel("");
		}
		
		for (Skill skill : skills) {
			int index = (7 - skill.getRank())*4 + (skill.getPosition() - 1);
			System.out.println("" + skill.getPosition() + " , " + skill.getRank()+ " = "+index);
			components[index] = new SkillField(skill);
			
		}
		
		for(JComponent c : components) {
			treePanel.add(c);
		}
		
		

		return treePanel;
	}
}
