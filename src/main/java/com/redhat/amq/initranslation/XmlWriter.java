package com.redhat.amq.initranslation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
 * Basic XML writer using DOM XML Parser
 * Will be useful when we know what to make the activemq.xml file should looke like
 * 
 */
public class XmlWriter {
	
	private Document doc;
	
	public XmlWriter(Map<String, Map<String, String>> map) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.newDocument();
		
		write(map, doc);
	}

	/*
	 * Writes xml from the output of the IniParser class
	 */
	public void write(Map<String, Map<String, String>> map, Document doc) throws ParserConfigurationException, TransformerException {
		
		//TODO Needs a root element here
		Element rootElement = doc.createElement("configuration");
		doc.appendChild(rootElement);
		
		for(String section: map.keySet()){
			Element sectionElement = doc.createElement(section);
			rootElement.appendChild(sectionElement);

			Map<String, String> keyVals = map.get(section);
			for (Map.Entry<String, String> entry : keyVals.entrySet()) {
				Element key = doc.createElement(entry.getKey());
				key.appendChild(doc.createTextNode(entry.getValue()));
			    sectionElement.appendChild(key);

			}
		}
	}
	
	public String getXmlString() throws TransformerException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(baos);
		writeXml(doc, result);
		return baos.toString();
	}
	
	public void writeXmlDoc(File file) throws TransformerException{
		StreamResult result = new StreamResult(file);
		writeXml(doc, result);
	}
	
	public static void writeXml(Document doc, StreamResult streamResult) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		transformer.transform(source, streamResult);
	}
}
