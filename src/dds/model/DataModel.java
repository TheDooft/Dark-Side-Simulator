package dds.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

	protected List<Stat> stats = new ArrayList<Stat>();
	protected List<Skill> skills = new ArrayList<Skill>();

	public DataModel() {

	}

	public List<Stat> getStats() {
		return stats;
	}
}
