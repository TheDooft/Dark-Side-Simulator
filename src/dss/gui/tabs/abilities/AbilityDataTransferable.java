package dss.gui.tabs.abilities;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import dss.model.Ability;

public class AbilityDataTransferable implements Transferable {

	private final List<Ability> abilities;

	public AbilityDataTransferable(List<Ability> list) {
		this.abilities = list;
	}

	public List<Ability> getAbilities() {
		return abilities;
	}


	@SuppressWarnings("deprecation")
	private static final DataFlavor[] flavors = { DataFlavor.stringFlavor,
			DataFlavor.plainTextFlavor // deprecated
	};

	/**
	 * Returns an array of flavors in which this <code>Transferable</code> can
	 * provide the data. <code>DataFlavor.stringFlavor</code> is properly
	 * supported. Support for <code>DataFlavor.plainTextFlavor</code> is
	 * <b>deprecated</b>.
	 * 
	 * @return an array of length two, whose elements are <code>DataFlavor.
	 *         stringFlavor</code> and <code>DataFlavor.plainTextFlavor</code>
	 */
	public DataFlavor[] getTransferDataFlavors() {
		// returning flavors itself would allow client code to modify
		// our internal behavior
		return (DataFlavor[]) flavors.clone();
	}

	/**
	 * Returns whether the requested flavor is supported by this
	 * <code>Transferable</code>.
	 * 
	 * @param flavor
	 *            the requested flavor for the data
	 * @return true if <code>flavor</code> is equal to
	 *         <code>DataFlavor.stringFlavor</code> or
	 *         <code>DataFlavor.plainTextFlavor</code>; false if
	 *         <code>flavor</code> is not one of the above flavors
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 */
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// JCK Test StringSelection0003: if 'flavor' is null, throw NPE
		for (int i = 0; i < flavors.length; i++) {
			if (flavor.equals(flavors[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the <code>Transferable</code>'s data in the requested
	 * <code>DataFlavor</code> if possible. If the desired flavor is
	 * <code>DataFlavor.stringFlavor</code>, or an equivalent flavor, the
	 * <code>String</code> representing the selection is returned. If the
	 * desired flavor is <code>DataFlavor.plainTextFlavor</code>, or an
	 * equivalent flavor, a <code>Reader</code> is returned. <b>Note:</b> The
	 * behavior of this method for <code>DataFlavor.plainTextFlavor</code> and
	 * equivalent <code>DataFlavor</code>s is inconsistent with the definition
	 * of <code>DataFlavor.plainTextFlavor</code>.
	 * 
	 * @param flavor
	 *            the requested flavor for the data
	 * @return the data in the requested flavor, as outlined above
	 * @throws UnsupportedFlavorException
	 *             if the requested data flavor is not equivalent to either
	 *             <code>DataFlavor.stringFlavor</code> or
	 *             <code>DataFlavor.plainTextFlavor</code>
	 * @throws IOException
	 *             if an IOException occurs while retrieving the data. By
	 *             default, StringSelection never throws this exception, but a
	 *             subclass may.
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 * @see java.io.Reader
	 */
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return abilities;
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

}
