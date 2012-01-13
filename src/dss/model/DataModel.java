package dss.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

	protected MappedList<String, Stat> stats = new MappedList<String, Stat>();
	protected MappedList<String, Skill> skills = new MappedList<String, Skill>();
	final List<Ability> availableAbilities = new ArrayList<Ability>();
	final List<Ability> selectedAbilities = new ArrayList<Ability>();
	
	private List<GenerationListener> generationListeners = new ArrayList<GenerationListener>();

	public DataModel() {

	}

	public MappedList<String, Stat> getStats() {
		return stats;
	}

	public MappedList<String, Skill> getSkills() {
		return skills;
	}

	public List<Ability> getAvailableAbilities() {
		return availableAbilities;
	}
	
	public List<Ability> getSelectedAbilities() {
		return selectedAbilities;
	}

	public Stat getStat(String string) {
		return stats.get(string);
	}

	public Skill getSkill(String string) {
		return skills.get(string);
	}


	public void addGenerationListener(GenerationListener generationListener) {
		generationListeners.add(generationListener);
	}

	public void generate() {
		for (GenerationListener listener : generationListeners) {
			listener.generate();
		}

	}
}
