package dds.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

	protected MappedList<String, Stat> stats = new MappedList<String, Stat>();
	protected MappedList<String, Skill> skills = new MappedList<String, Skill>();
	protected MappedList<String, Ability> abilities = new MappedList<String, Ability>();
	private List<GenerationListener> generationListeners = new ArrayList<GenerationListener>();

	public DataModel() {

	}

	public MappedList<String, Stat> getStats() {
		return stats;
	}

	public MappedList<String, Skill> getSkills() {
		return skills;
	}

	public MappedList<String, Ability> getAbilities() {
		return abilities;
	}

	public Stat getStat(String string) {
		return stats.get(string);
	}

	public Skill getSkill(String string) {
		return skills.get(string);
	}

	public Ability getAbility(String string) {
		return abilities.get(string);
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
