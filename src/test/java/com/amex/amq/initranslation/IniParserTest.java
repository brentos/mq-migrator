package com.amex.amq.initranslation;

import java.io.File;

import org.junit.Test;

import com.dbove.testing.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class IniParserTest {

	@Test
	public void parserTest() throws Exception {

		File f = Testing.getFile("mqs.ini");

		IniParser p = new IniParser(f);
		assertEquals(p.getInt("LogDefaults", "LogPrimaryFiles"), 3);
		assertEquals(p.getInt("LogDefaults", "LogSecondaryFiles"), 2);
		assertEquals(p.getInt("LogDefaults", "LogFilePages"), 4096);
		assertEquals(p.getString("LogDefaults", "LogType"), "CIRCULAR");
		assertEquals(p.getInt("LogDefaults", "LogBufferPages"), 0);
		assertEquals(p.getString("LogDefaults", "LogDefaultPath"),
				"/var/mqm/log");
		
		//assertEquals(p.getString("QueueManager", "Directory"),
			//	"saturn!queue!manager");
		

		assertEquals(p.getString("DefaultQueueManager", "Name"),
				"saturn.queue.manager");

		
	}

	@Test
	public void failTest() throws Exception {
		File f = Testing.getFile("mqs.ini");

		IniParser p = new IniParser(f);
		
		try {
			p.getString("FakeSection", "some-key");
			fail();
		} catch (NullPointerException e) {
			assertEquals("Section FakeSection does not exist", e.getMessage());
		}

		try {
			p.getString("ApiExitCommon", "some-key");
			fail();
		} catch (NullPointerException e) {
			assertEquals("Key some-key does not exist", e.getMessage());
		}

		
		
	}

}
