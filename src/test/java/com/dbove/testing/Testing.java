package com.dbove.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Static tools, used for testing.
 *
 * @author Domenic Bove <dbove@redhat.com>
 * @since 1/14/16
 */
public class Testing {
	
    /**
     * getFile written to work in JBoss Developer Studio
     *
     */
    public static File getFile(String fileName){
    	String basedir = System.getProperty("user.dir");
       	String dir = basedir+"/src/test/resources/" + fileName;
       	return new File(dir);
    }
    
    /**
     * getText written to work in JBoss Developer Studio
     *
     */
    public static String getText(String name) throws Exception{
    	File file = getFile(name);
    	return getTextFromFile(file);
    }
    
    public static String getTextFromFile(File file) throws Exception {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s = "";
            String line;
            while((line = br.readLine()) != null) {
                s += line + "\n";
            }
            return s;
        }
    }
}
