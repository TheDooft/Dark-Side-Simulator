package dss.gui.tabs.abilities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import dss.model.Ability;

final class AbilityListModel implements ListModel<Ability> {
	private final List<Ability> abilities;
	List<ListDataListener> listeners = new ArrayList<ListDataListener>();
	private boolean readOnly;

	/**
	 * @param abilityList
	 */
	AbilityListModel(List<Ability> abilities, boolean readOnly) {
		this.abilities = abilities;
		this.readOnly = readOnly;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public Ability getElementAt(int index) {
		return abilities.get(index);
	}

	@Override
	public int getSize() {
		return abilities.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	public void add(int index, Ability data) {
		abilities.add(index, data);
		fireContentsChanged(this, index, index);
	}

	public void set(int index, Ability data) {
		abilities.set(index, data);
		fireContentsChanged(this, index, index);
	}

	protected void fireContentsChanged(Object source, int index0, int index1) {
		ListDataEvent e = null;

		for (ListDataListener listener : listeners) {
			if (e == null) {
				e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED,
						index0, index1);
			}
			listener.contentsChanged(e);
		}
	}

	public void remove(int index) {
		abilities.remove(index);
		fireContentsChanged(this, index, index);
	}

	public boolean isReadOnly() {
		return readOnly;
	}

}