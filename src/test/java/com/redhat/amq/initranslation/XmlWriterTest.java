package com.redhat.amq.initranslation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;

import com.dbove.testing.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class XmlWriterTest {

	@Test
	public void basicTest() throws ParserConfigurationException, TransformerException {
		Map<String, Map<String, String>> entireMap = new HashMap<String, Map<String, String>>();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("hey", "there");
		map.put("hello", "world");
		entireMap.put("firstsection", map);
		
		map = new HashMap<String, String>();
		map.put("hey", "whats");
		map.put("up", "hello");
		entireMap.put("secondSection", map);
		
		XmlWriter writer = new XmlWriter(entireMap);
		
		String s = writer.getXmlString();
		
		System.out.println(s);
	}
	
	@Test
	public void tyingTogether() throws IOException, ParserConfigurationException, TransformerException {
		File f = Testing.getFile("mqs.ini");
		
		IniParser p = new IniParser(f);
		
		XmlWriter writer = new XmlWriter(p.getMap());
		
		String s = writer.getXmlString();
		System.out.println(s);
		
	}
	
}
