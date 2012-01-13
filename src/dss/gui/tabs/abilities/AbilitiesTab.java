package dss.gui.tabs.abilities;

import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.TransferHandler;

import dss.model.Ability;
import dss.model.DataModel;

public class AbilitiesTab extends JPanel {

	private static final long serialVersionUID = 4910906206006583803L;
	final LinkedList<Ability> availableAbilities = new LinkedList<Ability>();
	final LinkedList<Ability> selectedAbilities = new LinkedList<Ability>();
	public TransferHandler transferHandler = new AbilityTransferHandler();

	public AbilitiesTab(DataModel model) {
		setLayout(new GridLayout(1, 2));

		for (Ability ablility : model.getAbilities()) {
			availableAbilities.add(ablility);
		}

		add(new AvailableAbilityList(this));
		add(new SelectedAbilityList(this));

	}
}
