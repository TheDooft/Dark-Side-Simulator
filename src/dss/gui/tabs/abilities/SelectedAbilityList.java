package dss.gui.tabs.abilities;

import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.TransferHandler;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import dss.model.Ability;
import dss.model.DataModel;

class SelectedAbilityList extends JList<Ability> {

	private static final long serialVersionUID = 714064815787153574L;

	public SelectedAbilityList(final AbilitiesTab abilitiesTab) {
		
	}

	public SelectedAbilityList(DataModel model, TransferHandler transferHandler) {
		setCellRenderer(new SelectedAbilityRenderer());

		AbilityListModel listModel = new AbilityListModel(model.getSelectedAbilities(), false);
		setModel(listModel);
	
		setDropMode(DropMode.INSERT);
		setDragEnabled(true);
		setTransferHandler(transferHandler);
	}
}