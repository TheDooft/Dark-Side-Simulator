package dss.gui.tabs.abilities;

import javax.swing.DropMode;
import javax.swing.JList;

import dss.model.Ability;

class SelectedAbilityList extends JList<Ability> {

	final AbilitiesTab abilitiesTab;
	private static final long serialVersionUID = 714064815787153574L;

	public SelectedAbilityList(AbilitiesTab abilitiesTab) {
		this.abilitiesTab = abilitiesTab;
		setCellRenderer(new SelectedAbilityRenderer());

		setModel(new AbilityListModel(abilitiesTab.selectedAbilities, false));

		setDropMode(DropMode.INSERT);
		setDragEnabled(true);
		setTransferHandler(abilitiesTab.transferHandler);
	}
}