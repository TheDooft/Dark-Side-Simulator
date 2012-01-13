package dss.gui.tabs.abilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.TransferHandler;

import dss.model.Ability;
import dss.model.DataModel;

public class AbilitiesTab extends JPanel {

	private static final long serialVersionUID = 4910906206006583803L;
	final LinkedList<Ability> availableAbilities = new LinkedList<Ability>();
	final LinkedList<Ability> selectedAbilities = new LinkedList<Ability>();
	public TransferHandler transferHandler = new AbilityTransferHandler();

	public AbilitiesTab(DataModel model) {
		setLayout(new BorderLayout());

		for (Ability ablility : model.getAbilities()) {
			availableAbilities.add(ablility);
		}

		
		add(generateLeftPanel(), BorderLayout.WEST);
		add(generateRightPanel(), BorderLayout.CENTER);
		
		

	}
	
	private JPanel generateLeftPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
		
		
		JLabel title = new JLabel("Available abilities");
		title.setFont(title.getFont().deriveFont(16f));
		title.setFont(title.getFont().deriveFont(title.getFont().getStyle() ^ Font.BOLD));
		panel.add(title, BorderLayout.NORTH);
		
		
		
		AvailableAbilityList availableAbilityList = new AvailableAbilityList(this);
		
		panel.add(new JScrollPane(availableAbilityList), BorderLayout.CENTER);

		return panel;
	}
	
	private JPanel generateRightPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
		
		JLabel title = new JLabel("Selected abilities");
		title.setFont(title.getFont().deriveFont(16f));
		title.setFont(title.getFont().deriveFont(title.getFont().getStyle() ^ Font.BOLD));
		panel.add(title, BorderLayout.NORTH);
		
		SelectedAbilityList selectedAbilityList = new SelectedAbilityList(this);
		panel.add(new JScrollPane(selectedAbilityList), BorderLayout.CENTER);
		
		panel.add(generateDetailPanel(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel generateDetailPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		panel.setMinimumSize(new Dimension(0, 100));
		
		return panel;
	}
}
