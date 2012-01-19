package dss.model;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import dss.tools.LanguageTools;

public class DataModelFactory {

	
	public DataModelFactory() {
		
	}

	public DataModel load(String className) {
		DataModel dataModel = new DataModel();

		readStats("data/classes/" + className + "/stats.xml", dataModel);
		readSkills("data/classes/" + className + "/skills.xml", dataModel);
		readAbilities("data/classes/" + className + "/abilities.xml", dataModel);
		readAlterations("data/classes/" + className + "/alteration.xml", dataModel);

		return dataModel;
	}

	private void readStats(String path, DataModel dataModel) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfStats = doc.getElementsByTagName("stat");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element statElement = (Element) statNode;

					Stat stat = new Stat(getTagValue("tag", statElement), LanguageTools.translate(getTagValue("name",
							statElement)));
					stat.setValue(Integer.parseInt(getTagValue("value", statElement)));

					dataModel.getStats().add(stat.getTag(), stat);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	private void readSkills(String path, DataModel dataModel) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfSkillTrees = doc.getElementsByTagName("skillTree");
			for (int s = 0; s < listOfSkillTrees.getLength(); s++) {
				Node skillTreeNode = listOfSkillTrees.item(s);
				if (skillTreeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element skillTreeElement = (Element) skillTreeNode;

					SkillTree tree = new SkillTree(skillTreeElement.getAttribute("name"));
					dataModel.getSkillTrees().add(tree);

					NodeList listOfSkill = skillTreeElement.getElementsByTagName("skill");
					for (int s2 = 0; s2 < listOfSkill.getLength(); s2++) {
						Node skillNode = listOfSkill.item(s2);
						if (skillNode.getNodeType() == Node.ELEMENT_NODE) {

							Element skillElement = (Element) skillNode;

							Skill skill = new Skill(getTagValue("tag", skillElement), LanguageTools.translate(getTagValue(
									"name", skillElement)));
							skill.setValue(Integer.parseInt(getTagValue("value", skillElement)));
							skill.setMaxValue(Integer.parseInt(getTagValue("maxValue", skillElement)));
							skill.setRank(Integer.parseInt(getTagValue("rank", skillElement)));
							skill.setPosition(Integer.parseInt(getTagValue("position", skillElement)));
							skill.setIconName(getTagValue("icon", skillElement));

							String dependencyTag = getTagValue("dependency", skillElement);

							if (dependencyTag != null) {
								skill.setDependency(dataModel.getSkill(dependencyTag));
							}

							dataModel.getSkills().add(skill.getTag(), skill);
							tree.addSkill(skill);
							skill.setParentTree(tree);
						}
					}
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	private void readAbilities(String path, DataModel dataModel) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfStats = doc.getElementsByTagName("ability");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element abilityElement = (Element) statNode;

					String className = getTagValue("class", abilityElement);

					Ability ability = null;
					String tagValue = getTagValue("tag", abilityElement);
					if (className == null) {
						ability = new Ability(tagValue, LanguageTools.translate(getTagValue("name", abilityElement)));
					} else {
						String classPath = "dss.abilities." + className;
						@SuppressWarnings("unchecked")
						Class<Ability> abilityClass = (Class<Ability>) Class.forName(classPath);
						Constructor<Ability> constructor = abilityClass.getConstructor(String.class, String.class);
						ability = constructor.newInstance(tagValue,
								LanguageTools.translate(getTagValue("name", abilityElement)));
					}

					ability.setIconName(getTagValue("icon", abilityElement));

					ability.setCooldown((int) (1000 * Double.parseDouble(getTagValue("cooldown", abilityElement))));

					ability.setCasttime(Integer.parseInt(getTagValue("cast", abilityElement)));

					ability.setCost(Integer.parseInt(getTagValue("cost", abilityElement)));

					dataModel.getAvailableAbilities().add(tagValue, ability);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	private void readAlterations(String path, DataModel dataModel) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfAlterations = doc.getElementsByTagName("alteration");
			for (int s = 0; s < listOfAlterations.getLength(); s++) {

				Node alterationNode = listOfAlterations.item(s);
				if (alterationNode.getNodeType() == Node.ELEMENT_NODE) {

					Element alterationElement = (Element) alterationNode;

					String className = getTagValue("class", alterationElement);

					Alteration alteration = null;
					String tagValue = getTagValue("tag", alterationElement);
					String name = LanguageTools.translate(getTagValue("name", alterationElement));
					AlterationType type = AlterationType.valueOf(getTagValue("type", alterationElement));
					int maxDuration = Integer.parseInt(getTagValue("maxduration", alterationElement));
					int maxStack = Integer.parseInt(getTagValue("maxstack", alterationElement));
					if (className == null) {
						alteration = new Alteration(name, type, maxDuration, maxStack);
					} else {
						String classPath = "dss.alterations." + className;
						@SuppressWarnings("unchecked")
						Class<Alteration> alterationClass = (Class<Alteration>) Class.forName(classPath);
						Constructor<Alteration> constructor = alterationClass
								.getConstructor(String.class, String.class);
						alteration = constructor.newInstance(name, type, maxDuration, maxStack);
					}

					dataModel.getAlterations().add(tagValue, alteration);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList elementsByTagName = eElement.getElementsByTagName(sTag);
		if (elementsByTagName.getLength() == 0) {
			return null;
		}
		NodeList nlList = elementsByTagName.item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
