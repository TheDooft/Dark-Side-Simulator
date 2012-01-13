package dss.gui.tabs.abilities;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import dss.model.Ability;

class AbilityTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 7935227922205060684L;
	private int[] indices = null;
	private int addIndex = -1; // Location where items were added
	private int addCount = 0; // Number of items added.

	JList<Ability> source = null;

	public boolean canImport(TransferHandler.TransferSupport info) {
		// Check for String flavor
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	protected Transferable createTransferable(JComponent c) {
		// return new StringSelection(exportString(c));
		source = (JList<Ability>) c;
		indices = source.getSelectedIndices();
		return new AbilityDataTransferable(source.getSelectedValuesList());
	}

	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY_OR_MOVE;
	}

	@SuppressWarnings("unchecked")
	public boolean importData(TransferHandler.TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}

		JList<Ability> target = (JList<Ability>) info.getComponent();
		AbilityListModel listModel = (AbilityListModel) target.getModel();
		JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
		int index = dl.getIndex();

		// Get the string that is being dropped.
		Transferable t = info.getTransferable();
		List<Ability> data;
		try {
			data = (List<Ability>) t.getTransferData(DataFlavor.imageFlavor);
		} catch (Exception e) {
			return false;
		}

		if (source.equals(target)) {
			if (indices != null && index == indices[0]) {
				indices = null;
				return true;
			}
		}

		int max = listModel.getSize();
		if (index < 0) {
			index = max;
		} else {
			if (index > max) {
				index = max;
			}
		}
		addIndex = index;
		addCount = data.size();

		if (listModel.isReadOnly()) {
			return true;
		}

		for (Ability ability : data) {
			listModel.add(index++, ability);
		}
		return true;
	}

	protected void exportDone(JComponent c, Transferable data, int action) {
		AbilityListModel model = (AbilityListModel) source.getModel();

		if (!model.isReadOnly() && (action == MOVE) && (indices != null)) {

			// If we are moving items around in the same list, we
			// need to adjust the indices accordingly since those
			// after the insertion point have moved.
			if (addCount > 0) {
				for (int i = 0; i < indices.length; i++) {
					if (indices[i] > addIndex) {
						indices[i] += addCount;
					}
				}
			}
			for (int i = indices.length - 1; i >= 0; i--)
				model.remove(indices[i]);
		}
		indices = null;
		addIndex = -1;
		addCount = 0;

	}
}