package com.redhat.amq.initranslation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;

import com.dbove.testing.Testing;

/*
 * Loads a basic activemq.xml file with DOM Parser for editing
 * and writing into new configuration file
 */
public class ActivemqXmlWriter {
	
	private static String BASE_CONFIG_FILE = "activemq.xml";
	
	private Document doc;
	private Element rootElement;
	
	public ActivemqXmlWriter() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		File template = Testing.getFile(BASE_CONFIG_FILE);
		doc = docBuilder.parse(template);
		
		rootElement = doc.getDocumentElement();
	}
	
	// TODO write methods to edit rootElement
	// Can use appendChild() removeChild(), setAttribute()...
	
	public String getXmlString() throws TransformerException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(baos);
		writeXml(doc, result);
		String s = baos.toString();
		
		return s.substring(s.indexOf(">")+1);		
		//Eliminate the <?xml ...?> tag at the beginning
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
