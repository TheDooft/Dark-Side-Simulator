package dss.gui.tabs.abilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import dss.model.Ability;
import dss.tools.ImageTools;

class AvailableAbilityRenderer implements ListCellRenderer<Ability> {

	private static final Color backgroundColor = new Color(230, 230, 230);
	private static final Color panelBackgroundColor = new Color(255, 255, 255);
	private static final Color selectedColor = new Color(91, 203, 23);

	@Override
	public Component getListCellRendererComponent(JList<? extends Ability> list, Ability ability, int index,
			boolean isSelected, boolean cellHasFocus) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		panel.setBackground(panelBackgroundColor);

		Icon icon = ImageTools.getSmallIcon(ability.getIconPath());

		JLabel label = new JLabel(ability.getName(), icon, JLabel.LEFT);
		label.setIcon(icon);

		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label.setOpaque(true);
		label.setBackground(backgroundColor);

		label.setFont(label.getFont().deriveFont(14f));
		// label.setFont(label.getFont().deriveFont(label.getFont().getStyle() ^
		// Font.BOLD));

		panel.add(label, BorderLayout.CENTER);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(5, 1));
		selectionPanel.setOpaque(true);
		if (isSelected) {
			selectionPanel.setBackground(selectedColor);
		} else {
			selectionPanel.setBackground(backgroundColor);
		}

		panel.add(selectionPanel, BorderLayout.WEST);

		return panel;
	}

}