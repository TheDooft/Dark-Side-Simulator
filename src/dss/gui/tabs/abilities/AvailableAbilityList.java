package dss.gui.tabs.abilities;

import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.TransferHandler;

import dss.model.Ability;
import dss.model.DataModel;

class AvailableAbilityList extends JList<Ability> {

	private static final long serialVersionUID = 714064815787153574L;

	public AvailableAbilityList(DataModel model, TransferHandler transferHandler) {
		setCellRenderer(new AvailableAbilityRenderer());

		setModel(new AbilityListModel(model.getAvailableAbilities(), true));

		setDragEnabled(true);
		setDropMode(DropMode.INSERT);
		setTransferHandler(transferHandler);
	}
}