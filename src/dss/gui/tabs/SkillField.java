package dss.gui.tabs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dss.model.Skill;

public class SkillField extends JPanel {

	private static final long serialVersionUID = -4455285130313141604L;
	private final Skill skill;
	private JTextField skillField;

	public SkillField(final Skill skill) {
		this.skill = skill;
		add(new JLabel(skill.getName() + ":"));
		skillField = new JTextField(Integer.toString(skill.getValue()));
		skillField.setColumns(5);
		skillField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changed();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				changed();
			}

			private void changed() {
				try {
					int statValue = Integer.parseInt(skillField.getText());
					SkillField.this.skill.setValue(statValue);
				} catch (NumberFormatException e) {

				}

			}
		});

		add(skillField);
	}

}
