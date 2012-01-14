package dss.gui.tabs.abilities;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
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
	private JList<Ability> target;
	private List<Integer> addedIndicies;

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

		target = (JList<Ability>) info.getComponent();
		AbilityListModel targetModel = (AbilityListModel) target.getModel();
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
			if (indices != null && (index == indices[0] + 1 || index == indices[0])) {
				indices = null;
				return true;
			}
		}

		int max = targetModel.getSize();
		if (index < 0) {
			index = max;
		} else {
			if (index > max) {
				index = max;
			}
		}
		addIndex = index;
		addCount = data.size();

		if (!targetModel.isReadOnly()) {

			addedIndicies = new ArrayList<Integer>();

			for (Ability ability : data) {
				addedIndicies.add(index);
				targetModel.add(index++, ability);
			}

		}

		AbilityListModel sourceModel = (AbilityListModel) source.getModel();

		if (!sourceModel.isReadOnly() && (indices != null)) {

			// If we are moving items around in the same list, we
			// need to adjust the indices accordingly since those
			// after the insertion point have moved.
			if (addCount > 0) {
				for (int i = 0; i < indices.length; i++) {
					if (indices[i] > addIndex) {
						indices[i] += addCount;
					}
				}

				for (int i = 0; i < addedIndicies.size(); i++) {

				}
			}
			for (int i = indices.length - 1; i >= 0; i--) {
				sourceModel.remove(indices[i]);
				for (int j = 0; j < addedIndicies.size(); j++) {
					if (addedIndicies.get(j) > indices[i]) {
						addedIndicies.set(j, addedIndicies.get(j) - 1);
					}
				}
			}
		}
		indices = null;
		addIndex = -1;
		addCount = 0;

		source.setSelectedIndices(new int[0]);
		if (targetModel.isReadOnly()) {
			target.setSelectedIndices(new int[0]);
		} else {
			target.setSelectedIndices(toIntArray(addedIndicies));
		}

		return true;
	}

	static int[] toIntArray(List<Integer> integerList) {
		int[] intArray = new int[integerList.size()];
		for (int i = 0; i < integerList.size(); i++) {
			intArray[i] = integerList.get(i);
		}
		return intArray;
	}

	protected void exportDone(JComponent c, Transferable data, int action) {
		return;
		/*
		 * AbilityListModel model = (AbilityListModel) source.getModel();
		 * 
		 * if (!model.isReadOnly() && (action == MOVE) && (indices != null)) {
		 * 
		 * // If we are moving items around in the same list, we // need to
		 * adjust the indices accordingly since those // after the insertion
		 * point have moved. if (addCount > 0) { for (int i = 0; i <
		 * indices.length; i++) { if (indices[i] > addIndex) { indices[i] +=
		 * addCount; } } } for (int i = indices.length - 1; i >= 0; i--)
		 * model.remove(indices[i]); } indices = null; addIndex = -1; addCount =
		 * 0;
		 * 
		 * source.setSelectedIndices(new int[0]);
		 * target.setSelectedIndices(toIntArray(addedIndicies));
		 */
	}
}