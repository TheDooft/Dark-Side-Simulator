package dss.model;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {

	private final String name;
	
	private final List<Skill> skills = new ArrayList<Skill>();
	
	SkillTree(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
	}

	public List<Skill> getSkills() {
		return skills;
	}
}
