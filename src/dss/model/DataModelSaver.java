package dss.model;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.management.modelmbean.ModelMBean;
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
			for(Stat stat: dataModel.getStats()) {
				Element statElement = d.createElement("stat");
				Element statTagElement = d.createElement("tag");
				statTagElement.setTextContent(stat.getTag());
				Element statValueElement = d.createElement("value");
				statValueElement.setTextContent(Integer.toString(stat.getValue()));
				
				statElement.appendChild(statTagElement);
				statElement.appendChild(statValueElement);
				statsElement.appendChild(statElement);
			}
			
			Element selectedAbilitiesElement = d.createElement("selectedAbilities");
			
			
			for(Ability ability: dataModel.getSelectedAbilities()) {
				Element abilityElement = d.createElement("ability");
				Element abilityTagElement = d.createElement("tag");
				abilityTagElement.setTextContent(ability.getTag());
				
				abilityElement.appendChild(abilityTagElement);
				selectedAbilitiesElement.appendChild(abilityElement);
			}

			rootElement.appendChild(statsElement);
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

	}

}
