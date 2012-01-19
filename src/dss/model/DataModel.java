package dss.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

	private final MappedList<String, Stat> stats = new MappedList<String, Stat>();
	private final MappedList<String, Skill> skills = new MappedList<String, Skill>();
	private final List<SkillTree> skillTrees = new ArrayList<SkillTree>();
	private final MappedList<String, Ability> availableAbilities = new MappedList<String, Ability>();
	private final MappedList<String, Alteration> alterations = new MappedList<String, Alteration>();
	private final List<Ability> selectedAbilities = new ArrayList<Ability>();

	private List<GenerationListener> generationListeners = new ArrayList<GenerationListener>();

	public DataModel() {

	}

	public MappedList<String, Stat> getStats() {
		return stats;
	}

	public MappedList<String, Skill> getSkills() {
		return skills;
	}

	public MappedList<String, Ability> getAvailableAbilities() {
		return availableAbilities;
	}
	
	public MappedList<String,Alteration> getAlterations() {
		return alterations;
	}

	public List<Ability> getSelectedAbilities() {
		return selectedAbilities;
	}

	public Stat getStat(String tag) {
		return stats.get(tag);
	}

	public Skill getSkill(String tag) {
		return skills.get(tag);
	}
	
	public Ability getAbility(String tag) {
		return availableAbilities.get(tag);
	}
	
	public Alteration getAlteration(String tag){
		return alterations.get(tag);
	}

	public void addGenerationListener(GenerationListener generationListener) {
		generationListeners.add(generationListener);
	}

	public void generate() {
		for (GenerationListener listener : generationListeners) {
			listener.generate();
		}

	}
	
	public void save() {
		DataModelSaver saver = new DataModelSaver(this);
		saver.save();
	}

	public void load() {
		DataModelSaver saver = new DataModelSaver(this);
		saver.load();
	}

	public List<SkillTree> getSkillTrees() {
		return skillTrees;
	}

	
}
