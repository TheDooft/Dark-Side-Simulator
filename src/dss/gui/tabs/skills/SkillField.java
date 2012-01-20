package dss.gui.tabs.skills;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dss.model.ChangeListener;
import dss.model.DataModel;
import dss.model.Skill;
import dss.tools.ImageTools;

public class SkillField extends JPanel {

	private static final Color LIGHT_GRAY = new Color(230,230, 230);
	private static final Color RED = new Color(233,83, 80);
	private static final Color DARK_GRAY = new Color(200,200, 200);
	private static final Color BLUE = new Color(80,191, 233);
	private static final Color GREEN = new Color(80,233,86);
	private static final long serialVersionUID = -4455285130313141604L;
	private final Skill skill;
	private JLabel label;
	private JButton button;
	private final DataModel model;

	public SkillField(final Skill skill, DataModel model) {
		this.skill = skill;
		this.model = model;

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		
		button = new JButton() ;
		button.setIcon(ImageTools.getLargeIcon(skill.getIconPath()));
		add(button, BorderLayout.CENTER);
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.SOUTH);

		updateSkill();

		button.addMouseListener(new MouseAdapter() {
			boolean pressed;

			@Override
			public void mousePressed(MouseEvent e) {
				button.getModel().setArmed(true);
				button.getModel().setPressed(true);
				pressed = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// if(isRightButtonPressed)
				// {underlyingButton.getModel().setPressed(true));
				button.getModel().setArmed(false);
				button.getModel().setPressed(false);

				if (pressed) {
					if (SwingUtilities.isRightMouseButton(e)) {
						decrementSkill();
					} else {
						incrementSkill();
					}
				}
				pressed = false;

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pressed = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pressed = true;
			}
		});

		model.addChangeListener(new ChangeListener() {
			
			@Override
			public void change() {
				updateSkill();
			}
		});
		
		// add(new JLabel(skill.getName() + ":"));
		/*
		 * skillField = new JTextField(Integer.toString(skill.getValue()));
		 * skillField.setColumns(5);
		 * skillField.getDocument().addDocumentListener(new DocumentListener() {
		 * 
		 * @Override public void removeUpdate(DocumentEvent arg0) { changed(); }
		 * 
		 * @Override public void insertUpdate(DocumentEvent arg0) { changed(); }
		 * 
		 * @Override public void changedUpdate(DocumentEvent arg0) { changed();
		 * }
		 * 
		 * private void changed() { try { int statValue =
		 * Integer.parseInt(skillField.getText());
		 * SkillField.this.skill.setValue(statValue); } catch
		 * (NumberFormatException e) {
		 * 
		 * }
		 * 
		 * } });
		 * 
		 * add(skillField);
		 */
	}

	private void updateSkill() {
		label.setText(skill.getValue() + "/" + skill.getMaxValue());
		label.setOpaque(true);
		
		if(skill.isValid()) {
			button.setEnabled(true);
			button.setToolTipText(skill.getName());
			if(skill.getValue() > 0) {
				if(skill.getValue() >= skill.getMaxValue()) {
					label.setBackground(GREEN);
				} else {
					label.setBackground(BLUE);
				}
			} else {
				label.setBackground(LIGHT_GRAY);
			}
			
		} else {
			button.setEnabled(false);
			button.setToolTipText(skill.getName());
			if(skill.getValue() > 0) {
				label.setBackground(RED);
			} else {
				label.setBackground(DARK_GRAY);
			}
		}
	}

	private void decrementSkill() {
		if(skill.canDecrement()) {
			skill.setValue(skill.getValue() - 1);
			model.notifyChange();
		}
	}

	private void incrementSkill() {
		if(skill.canIncrement()) {
			skill.setValue(skill.getValue() + 1);
			model.notifyChange();
		}
	}

	
	
}
