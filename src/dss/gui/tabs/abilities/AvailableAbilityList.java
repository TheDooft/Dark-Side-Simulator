package dss.gui.tabs.abilities;

import javax.swing.DropMode;
import javax.swing.JList;

import dss.model.Ability;

class AvailableAbilityList extends JList<Ability> {

	private static final long serialVersionUID = 714064815787153574L;

	public AvailableAbilityList(AbilitiesTab abilitiesTab) {
		setCellRenderer(new AvailableAbilityRenderer());

		setModel(new AbilityListModel(abilitiesTab.availableAbilities, true));

		setDragEnabled(true);
		setDropMode(DropMode.INSERT);
		setTransferHandler(abilitiesTab.transferHandler);
	}
}