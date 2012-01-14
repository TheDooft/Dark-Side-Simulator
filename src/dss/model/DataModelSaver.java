package dss.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DataModelSaver {

	private final DataModel dataModel;

	public DataModelSaver(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void save() {
		try {

			Document d;

			d = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

			Transformer t = TransformerFactory.newInstance().newTransformer();

			Element rootElement = d.createElement("dssSave");

			Element statsElement = d.createElement("stats");
			for (Stat stat : dataModel.getStats()) {
				Element statElement = d.createElement("stat");
				Element statTagElement = d.createElement("tag");
				statTagElement.setTextContent(stat.getTag());
				Element statValueElement = d.createElement("value");
				statValueElement.setTextContent(Integer.toString(stat.getValue()));

				statElement.appendChild(statTagElement);
				statElement.appendChild(statValueElement);
				statsElement.appendChild(statElement);
			}
			
			Element skillsElement = d.createElement("skills");
			for (Skill skill: dataModel.getSkills()) {
				Element skillElement = d.createElement("skill");
				Element skillTagElement = d.createElement("tag");
				skillTagElement.setTextContent(skill.getTag());
				Element skillValueElement = d.createElement("value");
				skillValueElement.setTextContent(Integer.toString(skill.getValue()));

				skillElement.appendChild(skillTagElement);
				skillElement.appendChild(skillValueElement);
				skillsElement.appendChild(skillElement);
			}

			Element selectedAbilitiesElement = d.createElement("selectedAbilities");
			for (Ability ability : dataModel.getSelectedAbilities()) {
				Element abilityElement = d.createElement("ability");
				Element abilityTagElement = d.createElement("tag");
				abilityTagElement.setTextContent(ability.getTag());

				abilityElement.appendChild(abilityTagElement);
				selectedAbilitiesElement.appendChild(abilityElement);
			}

			rootElement.appendChild(statsElement);
			rootElement.appendChild(skillsElement);
			rootElement.appendChild(selectedAbilitiesElement);

			d.appendChild(rootElement);

			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			FileOutputStream s = new FileOutputStream("save.xml");

			t.transform(new DOMSource(d), new StreamResult(s));

			s.close();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load() {

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("save.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();

			// Clean selected abilities
			dataModel.getSelectedAbilities().clear();

			NodeList listOfAbilities = doc.getElementsByTagName("ability");
			for (int s = 0; s < listOfAbilities.getLength(); s++) {

				Node statNode = listOfAbilities.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element abilityElement = (Element) statNode;

					String tagName = getTagValue("tag", abilityElement);

					Ability ability = dataModel.getAbility(tagName);
					dataModel.getSelectedAbilities().add(ability);
				}
			}

			NodeList listOfStats = doc.getElementsByTagName("stat");
			for (int s = 0; s < listOfStats.getLength(); s++) {

				Node statNode = listOfStats.item(s);
				if (statNode.getNodeType() == Node.ELEMENT_NODE) {

					Element statElement = (Element) statNode;

					String tagName = getTagValue("tag", statElement);
					
					Stat stat = dataModel.getStat(tagName);
					stat.setValue(Integer.parseInt(getTagValue("value", statElement)));
					
				}
			}
			
			NodeList listOfSkills = doc.getElementsByTagName("skill");
			for (int s = 0; s < listOfSkills.getLength(); s++) {

				Node skillNode = listOfSkills.item(s);
				if (skillNode.getNodeType() == Node.ELEMENT_NODE) {

					Element skillElement = (Element) skillNode;

					String tagName = getTagValue("tag", skillElement);
					
					Skill skill = dataModel.getSkill(tagName);
					skill.setValue(Integer.parseInt(getTagValue("value", skillElement)));
					
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
