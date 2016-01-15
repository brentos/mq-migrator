package com.redhat.amq.initranslation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniParser {

	//private Pattern _section = Pattern.compile( "\\s*\\[([^]]*)\\]\\s*" );
	private Pattern _section = Pattern.compile("\\w*:");

	private Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
	private Map<String, Map<String, String>> _entries = new HashMap<>();

	public IniParser(File file) throws IOException {
		load(file);
	}
	
	//TODO Key-Values without Sections?
	
	//TODO Repeat Sections-- ie 2 QueueManager sections

	public void load(File file) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String section = null;
			while ((line = br.readLine()) != null) {
				Matcher m = _section.matcher(line);
				if (m.matches()) {
					//section = m.group(1).trim();
					section = line.substring(0, line.length()-1);	
					//Remove the : at the end of the section
				} else if (section != null) {
					m = _keyValue.matcher(line);
					if (m.matches()) {
						String key = m.group(1).trim();
						String value = m.group(2).trim();
						Map<String, String> kv = _entries.get(section);
						if (kv == null) {
							_entries.put(section, kv = new HashMap<>());
						}
						kv.put(key, value);
					}
				}
			}
		}
	}

	public String getString(String section, String key) {
		Map<String, String> kv = _entries.get(section);
		if (kv == null) {
			throw new NullPointerException("Section " + section + " does not exist");
		}
		String value = kv.get(key);
		if (value == null) {
			throw new NullPointerException("Key " + key + " does not exist");
		}
		return value;
	}

	public int getInt(String section, String key){
		String value = getString(section, key);
		return Integer.parseInt(value);
	}

	public float getFloat(String section, String key) {
		String value = getString(section, key);
		return Float.parseFloat(value);
	}

	public double getDouble(String section, String key) {
		String value = getString(section, key);
		return Double.parseDouble(value);
	}
}
