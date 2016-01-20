package com.redhat.amq.initranslation;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ActivemqXmlWriterTest {

	@Test
	public void createBase() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		
		ActivemqXmlWriter writer = new ActivemqXmlWriter();
		
		String s = writer.getXmlString();
		System.out.println(s);
	}
}
