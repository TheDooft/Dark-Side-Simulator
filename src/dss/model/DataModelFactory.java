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

public class DataModelFactory {

	private ResourceBundle bundle;

	public DataModelFactory() {
		bundle = ResourceBundle.getBundle("translation");
	}

	public DataModel load(String classeName) {
		DataModel dataModel = new DataModel();

		readStats("data/classes/" + classeName + "/stats.xml", dataModel);
		readSkills("data/classes/" + classeName + "/skills.xml", dataModel);
		readAbilities("data/classes/" + classeName + "/abilities.xml", dataModel);

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

					Stat stat = new Stat(getTagValue("tag", statElement), bundle.getString(getTagValue("name",
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

			NodeList listOfStats = doc.getElementsByTagName("skill");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element statElement = (Element) statNode;

					Skill skill = new Skill(getTagValue("tag", statElement), bundle.getString(getTagValue("name",
							statElement)));
					skill.setValue(Integer.parseInt(getTagValue("value", statElement)));

					dataModel.getSkills().add(skill.getTag(), skill);
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
					if (className == null) {
						ability = new Ability(getTagValue("tag", abilityElement), bundle.getString(getTagValue("name",
								abilityElement)));
					} else {
						String classPath = "dss.abilities." + className;
						@SuppressWarnings("unchecked")
						Class<Ability> abilityClass = (Class<Ability>) Class.forName(classPath);
						Constructor<Ability> constructor = abilityClass.getConstructor(String.class, String.class);
						ability = constructor.newInstance(getTagValue("tag", abilityElement),
								bundle.getString(getTagValue("name", abilityElement)));
					}

					ability.setIconName(getTagValue("icon", abilityElement));

					ability.setCooldown((int) (1000 * Double.parseDouble(getTagValue("cooldown", abilityElement))));

					ability.setCasttime(Integer.parseInt(getTagValue("cast", abilityElement)));

					ability.setCost(Integer.parseInt(getTagValue("cost", abilityElement)));

					dataModel.getAvailableAbilities().add(ability);
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
