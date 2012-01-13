package dss.gui.tabs.abilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import dss.model.Ability;

class SelectedAbilityRenderer implements ListCellRenderer<Ability> {

	private static final Color backgroundColor = new Color(230,230,230);
	private static final Color panelBackgroundColor = new Color(255,255,255);
	private static final Color selectedColor = new Color(91,203,23);

	
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Ability> list, Ability ability, int index,
			boolean isSelected, boolean cellHasFocus) {

		JPanel cellPanel = new JPanel();
		cellPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		cellPanel.setLayout(new BorderLayout());
		cellPanel.setOpaque(true);
		cellPanel.setBackground(panelBackgroundColor);
		
		Icon icon = new ImageIcon("data/img/large/"+ability.getIconName()+".jpg");
		
		
		
		
		JLabel label = new JLabel(ability.getName(), icon, JLabel.LEFT );
		label.setIcon(icon);
		
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		
		label.setFont(label.getFont().deriveFont(14f));

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BorderLayout());
		bodyPanel.setOpaque(true);
		bodyPanel.setBackground(backgroundColor);
		
		
		JLabel priorityLabel = new JLabel(Integer.toString(index+1));
		priorityLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));
		priorityLabel.setFont(priorityLabel.getFont().deriveFont(18f));
		priorityLabel.setFont(priorityLabel.getFont().deriveFont(priorityLabel.getFont().getStyle() ^ Font.BOLD));
		bodyPanel.add(priorityLabel, BorderLayout.WEST);
		
		
		bodyPanel.add(label, BorderLayout.CENTER);
		
		
		cellPanel.add(bodyPanel, BorderLayout.CENTER);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(5, 1));
		selectionPanel.setOpaque(true);
		if(isSelected) {
			selectionPanel.setBackground(selectedColor);
		} else {
			selectionPanel.setBackground(backgroundColor);
		}
		
		
		cellPanel.add(selectionPanel, BorderLayout.WEST);
		
		return cellPanel;
	}

}