package dss.model;

import java.io.File;
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
		readAbilities("data/classes/" + classeName + "/abilities.xml",
				dataModel);

		return dataModel;
	}

	private void readStats(String path, DataModel dataModel) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfStats = doc.getElementsByTagName("stat");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element statElement = (Element) statNode;

					Stat stat = new Stat(getTagValue("tag", statElement),
							bundle.getString(getTagValue("name", statElement)));
					stat.setValue(Integer.parseInt(getTagValue("value",
							statElement)));

					dataModel.getStats().add(stat.getTag(), stat);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
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
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfStats = doc.getElementsByTagName("skill");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element statElement = (Element) statNode;

					Skill skill = new Skill(getTagValue("tag", statElement),
							bundle.getString(getTagValue("name", statElement)));
					skill.setValue(Integer.parseInt(getTagValue("value",
							statElement)));

					dataModel.getSkills().add(skill.getTag(),skill);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
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
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();

			NodeList listOfStats = doc.getElementsByTagName("ability");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element abilityElement = (Element) statNode;

					Ability ability = new Ability(getTagValue("tag", abilityElement),
							bundle.getString(getTagValue("name", abilityElement)));

					ability.setIconName(getTagValue("icon",
							abilityElement));
					
					ability.setCooldown((int)(1000*Double.parseDouble(getTagValue("cooldown",
							abilityElement))));
					
					ability.setGcd(Integer.parseInt(getTagValue("gcd",
							abilityElement)));
					
					ability.setCasttime(Integer.parseInt(getTagValue("cast",
							abilityElement)));
					
					
					dataModel.getAvailableAbilities().add(ability);
				}
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
	

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
