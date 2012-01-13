package dss.gui.tabs.abilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import dss.model.Ability;

class AvailableAbilityRenderer implements ListCellRenderer<Ability> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Ability> list, Ability ability, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel label = new JLabel(ability.getName());
		label.setOpaque(true);

		if (isSelected) {
			label.setBackground(list.getSelectionBackground());
			label.setForeground(list.getSelectionForeground());
		} else {
			label.setBackground(Color.WHITE);
			label.setForeground(list.getForeground());
		}

		return label;
	}

}