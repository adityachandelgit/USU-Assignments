package aditya.hw1;

import java.util.Scanner;

import utilities.Parsers;

public class Main {

	public static void main(String[] args) {

		System.out.println("Please select choice!\n" + "------------------------------------\n" + 
		"Press 1 for reading shapes from JSON file and showing output on Console.\n" + 
		"Press 2 for reading shapes from XML file and showing output on Console.\n" + 
		"Press 3 for reading shapes from JSON file and exporting to CSV file.\n" + 
		"Press 4 for reading shapes from XML file and exporting to CSV file.\n" +
		"Press 5 for reading inexistent JSON file.\n" +
		"Press 6 for reading inexistent XML file.\n");
		

		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();

		switch (input) {
		case 1:
			System.out.println("Press 1 for importing from JSON file without any errors.");
			System.out.println("Press 2 for importing from JSON file with invalid shapes.");
			System.out.println("Press 3 for importing from JSON file with invalid shape values.");
			System.out.println("Press 4 for importing from JSON file with invalid JSON structure.");
			input = scan.nextInt();
			System.out.println("Reading shapes from JSON file");
			switch (input) {
			case 1:
				Parsers.jsonToConsoleWriter(".\\TestJsonFiles\\shapes-json-no-errors.json");
				break;
			case 2:
				Parsers.jsonToConsoleWriter(".\\TestJsonFiles\\shapes-json-invalid-shapes.json");
				break;
			case 3:
				Parsers.jsonToConsoleWriter(".\\TestJsonFiles\\shapes-json-invalid-shapes-values.json");
				break;
			case 4:
				Parsers.jsonToConsoleWriter(".\\TestJsonFiles\\shapes-json-invalid-json-structure.json");
				break;
			default:
				System.out.println("Invalid selection");
			}
			break;
		case 2:
			System.out.println("Press 1 for importing from XML file without any errors.");
			System.out.println("Press 2 for importing from XML file with invalid shapes.");
			System.out.println("Press 3 for importing from XML file with invalid shape values.");
			System.out.println("Press 4 for importing from XML file with invalid JSON structure.");
			input = scan.nextInt();
			System.out.println("Reading shapes from XML file");
			
			switch (input) {
			case 1:
				Parsers.xmlToConsoleWriter(".\\TestXmlFiles\\shapes-xml-no-errors.xml");
				break;
			case 2:
				Parsers.xmlToConsoleWriter(".\\TestXmlFiles\\shapes-xml-invalid-shapes.xml");
				break;
			case 3:
				Parsers.xmlToConsoleWriter(".\\TestXmlFiles\\shapes-xml-invalid-shapes-values.xml");
				break;
			case 4:
				Parsers.xmlToConsoleWriter(".\\TestXmlFiles\\shapes-xml-invalid-json-structure.xml");
				break;
			default:
				System.out.println("Invalid selection");
			}
			break;
			
			//Parsers.xmlToConsoleWriter(".\\TestXmlFiles\\shapes-xml-no-errors.xml");
			//break;
		case 3:
			System.out.println("Press 1 for importing from JSON file without any errors.");
			System.out.println("Press 2 for importing from JSON file with invalid shapes.");
			System.out.println("Press 3 for importing from JSON file with invalid shape values.");
			System.out.println("Press 4 for importing from JSON file with invalid JSON structure.");
			input = scan.nextInt();
			System.out.println("Reading shapes from JSON file");
			switch (input) {
			case 1:
				Parsers.jsonToCsvExporter(".\\TestJsonFiles\\shapes-json-no-errors.json", ".\\OutputCSVs\\shapes-json-no_error.csv");
				break;
			case 2:
				Parsers.jsonToCsvExporter(".\\TestJsonFiles\\shapes-json-invalid-shapes.json", ".\\OutputCSVs\\shapes-json-invalid_shapes.csv");
				break;
			case 3:
				Parsers.jsonToCsvExporter(".\\TestJsonFiles\\shapes-json-invalid-shapes-values.json", ".\\OutputCSVs\\shapes-json-invalid_shape_values.csv");
				break;
			case 4:
				Parsers.jsonToCsvExporter(".\\TestJsonFiles\\shapes-json-invalid-json-structure.json", ".\\OutputCSVs\\shapes-json-invalid_json_structure.csv");
				break;
			default:
				System.out.println("Invalid selection");
			}
			break;
		case 4:
			System.out.println("Press 1 for importing from XML file without any errors.");
			System.out.println("Press 2 for importing from XML file with invalid shapes.");
			System.out.println("Press 3 for importing from XML file with invalid shape values.");
			System.out.println("Press 4 for importing from XML file with invalid JSON structure.");
			input = scan.nextInt();
			System.out.println("Reading shapes from XML file");
			switch (input) {
			case 1:
				Parsers.xmlToCsvExporter(".\\TestXmlFiles\\shapes-xml-no-errors.xml", ".\\OutputCSVs\\shapes-xml-no_error.csv");
				break;
			case 2:
				Parsers.xmlToCsvExporter(".\\TestXmlFiles\\shapes-xml-invalid-shapes.xml", ".\\OutputCSVs\\shapes-xml-invalid_shapes.csv");
				break;
			case 3:
				Parsers.xmlToCsvExporter(".\\TestXmlFiles\\shapes-xml-invalid-shapes-values.xml", ".\\OutputCSVs\\shapes-xml-invalid_shapes_values.csv");
				break;
			case 4:
				Parsers.xmlToCsvExporter(".\\TestXmlFiles\\shapes-xml-invalid-xml-structure.xml", ".\\OutputCSVs\\shapes-xml-invalid_json_structure.csv");
				break;
			default:
				System.out.println("Invalid selection");
			}
			break;
		case 5:
			Parsers.jsonToConsoleWriter(".\\TestJsonFiles\\doesnot_exist.json");
			break;
		case 6:
			Parsers.xmlToConsoleWriter(".\\TestJsonFiles\\doesnot_exist.xml");
			break;
		default:
			System.out.println("Invalid selection");
		}
		scan.close();

	}

}
