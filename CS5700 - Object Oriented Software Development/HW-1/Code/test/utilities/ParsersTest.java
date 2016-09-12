package utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.Parsers;

public class ParsersTest {

	// Test is successful if the Parsers.readJsonImporter() does not return null,
	// i.e. the file has been successfully parsed without any error. Or in other 
	// words, the function does not enter the catch block if the parsing is successful.
	@Test
	public void testReadJsonImporter() {
		// Parsers.readJsonImporter reads valid path, so it does not return null, and the test passes.
		Object returnValue1 = Parsers.readJsonImporter(".\\TestJsonFiles\\shapes-json-no-errors.json");
		assertNotNull(returnValue1);
	}

	@Test
	public void testReadXmlImporter() {
		// Parsers.readXmlImporter reads valid path, so it does not return null, and the test passes.
		Object returnValue2 = Parsers.readXmlImporter(".\\TestXmlFiles\\shapes-xml-no-errors.xml");
		assertNotNull(returnValue2);
	}

}
