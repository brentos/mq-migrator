package com.amex.amq;

import java.io.File;
import java.util.regex.Pattern;

import org.junit.Test;

import com.dbove.testing.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
	
	@Test
	public void getFileTest() throws Exception{

		File f = Testing.getFile("mqs.ini");
		
		Parser p = new Parser(f);
		
		
		//assertEquals(p.getInt("LogDefaults", "LogPrimaryFiles", 0), 3);


	}

}
