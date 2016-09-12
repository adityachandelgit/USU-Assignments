package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import shapes.Circle;
import shapes.ConvexPolygon;
import shapes.Ellipse;
import shapes.EquilateralTriangle;
import shapes.IsocelesTriangle;
import shapes.Rectangle;
import shapes.ScaleneTriangle;
import shapes.Shape;
import shapes.Square;
import shapes.Triangle;

public class Parsers {

	// Takes XML file as input, reads it, calculates the areas and outputs the
	// areas to the console
	public static void xmlToConsoleWriter(String inputFilePath) {
		ArrayList<Shape> listOfShapes = readXmlImporter(inputFilePath);
		if (listOfShapes != null) {
			calculateAreas(listOfShapes, 1);
		}
	}

	// Takes JSON file as input, reads it, calculates the areas and outputs the
	// areas to the console
	public static void jsonToConsoleWriter(String inputFilePath) {
		ArrayList<Shape> listOfShapes = readJsonImporter(inputFilePath);
		if (listOfShapes != null) {
			calculateAreas(listOfShapes, 1);
		}
	}

	public static void xmlToCsvExporter(String inputXmlFilePath, String outputCsvFilePath) {
		ArrayList<Shape> listOfShapes = readXmlImporter(inputXmlFilePath);
		ArrayList<TotalAreaInfo> totalAreaInfos = calculateAreas(listOfShapes, 0);
		if (totalAreaInfos == null) {
			//System.out.println("\nInvalid options! Exiting...\n");
			return;
		}
		if (totalAreaInfos.size() == 0) {
			System.out.println("\nNo valid shapes in the XML file! Exiting...\n");
			return;
		}

		csvExporter(totalAreaInfos, outputCsvFilePath);

	}

	// Takes JSON file as input, reads it, calculates the areas and outputs CSV 
	// file to the specified path
	public static void jsonToCsvExporter(String inputJsonFilePath, String outputCsvFilePath) {
		ArrayList<Shape> listOfShapes = readJsonImporter(inputJsonFilePath);
		ArrayList<TotalAreaInfo> totalAreaInfos = calculateAreas(listOfShapes, 0);
		if (totalAreaInfos == null) {
			System.out.println("\nInvalid options! Exiting...\n");
			return;
		}
		if (totalAreaInfos.size() == 0) {
			System.out.println("\nNo valid shapes in the JSON file! Exiting...\n");
			return;
		}

		csvExporter(totalAreaInfos, outputCsvFilePath);

	}

	// Read the TotalAreaInfo arrayList and creates a CSV file at the specified path
	private static void csvExporter(ArrayList<TotalAreaInfo> totalAreaInfos, String outputCsvFilePath) {
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		final String FILE_HEADER = "Category,ParentCategory,Shape,Area";

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(outputCsvFilePath);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

			for (TotalAreaInfo tai : totalAreaInfos) {
				fileWriter.append(String.valueOf(tai.getCategory()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(tai.getParentCateogry()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(tai.getShapeName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(tai.getArea()));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully at location: " + outputCsvFilePath);

		} catch (IOException e) {
			System.out.println("There's some problem in creating the output file.");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.err.println("There's some problem in closing the file writer.");
				e.printStackTrace();
			}
		}
	}

	// Read the input JSON file, processes it and returns arrayList of Shape objects
	public static ArrayList<Shape> readJsonImporter(String inputFilePath) {
		System.out.println("----------------------------");
		ArrayList<Shape> shapeList = new ArrayList<>();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(inputFilePath));
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonobject = (JSONObject) jsonArray.get(i);
				String name = (String) jsonobject.get("name");
				if (name.equals("ellipse")) {
					String axis1str = (String) jsonobject.get("axis1");
					String axis2str = (String) jsonobject.get("axis2");
					if(tryParseInt(axis1str) == null || tryParseInt(axis2str) == null) {
						continue;
					}
					int axis1 = Integer.parseInt(axis1str);
					int axis2 = Integer.parseInt(axis2str);
					if (axis1 <= 0 || axis2 <= 0) {
						System.err.println("Invalid shape: Axis of ellipse can't be zero or less!");
					} else {
						Shape ellipse = new Ellipse(axis1, axis2);
						shapeList.add(ellipse);
					}
				} else if (name.equals("circle")) {
					String radiusStr = (String) jsonobject.get("radius");
					if(tryParseInt(radiusStr) == null) {
						continue;
					}
					int radius = Integer.parseInt(radiusStr);
					if (radius <= 0) {
						System.err.println("Invalid shape: Radius of circle can't be zero or less!");
					} else {
						Shape circle = new Circle(radius);
						shapeList.add(circle);
					}
				} else if (name.equals("triangle")) {
					String sideAstr = (String) jsonobject.get("sidea");
					String sideBstr = (String) jsonobject.get("sideb");
					String sideCstr = (String) jsonobject.get("sidec");
					if(tryParseInt(sideAstr) == null || tryParseInt(sideBstr) == null || tryParseInt(sideCstr) == null) {
						continue;
					}
					int sideA = Integer.parseInt(sideAstr);
					int sideB = Integer.parseInt(sideBstr);
					int sideC = Integer.parseInt(sideCstr);
					if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
						System.err.println("Invalid shape: Sides of triangle cannot be zero or less!");
					} else if (sideA > sideB + sideC || sideB > sideA + sideC || sideC > sideA + sideB) {
						System.err.println("Invalid shape: Sum of two sides of a triangle cannot be less than third side!");
					} else if ((sideA == sideB) && (sideB == sideC)) {
						Shape eqTriangle = new EquilateralTriangle(sideA);
						shapeList.add(eqTriangle);
					} else if (sideA == sideB || sideB == sideC || sideC == sideA) {
						Shape isoTriangle = new IsocelesTriangle(sideA, sideB);
						shapeList.add(isoTriangle);
					} else {
						Shape scaTriangle = new ScaleneTriangle(sideA, sideB, sideC);
						shapeList.add(scaTriangle);
					}
				} else if (name.equals("rectangle")) {
					String sideAstr = (String) jsonobject.get("sidea");
					String sideBstr = (String) jsonobject.get("sideb");
					if(tryParseInt(sideAstr) == null || tryParseInt(sideBstr) == null) {
						continue;
					}
					int sideA = Integer.parseInt(sideAstr);
					int sideB = Integer.parseInt(sideBstr);
					if (sideA <= 0 || sideB <= 0) {
						System.err.println("Invalid shape: Sides of rectangle cannot be zero or less!");
					} else {
						Shape rectangle = new Rectangle(sideA, sideB);
						shapeList.add(rectangle);
					}
				} else if (name.equals("square")) {
					String sideStr = (String) jsonobject.get("side");
					if(tryParseInt(sideStr) == null) {
						continue;
					}
					int side = Integer.parseInt(sideStr);
					if (side <= 0) {
						System.err.println("Invalid shape: Side of square cannot be zero or less!");
					} else {
						Shape square = new Square(side);
						shapeList.add(square);
					}
				} else {
					System.err.println(name + ": is an unrecognisable shape! Skipping!");
				}
			}
		} catch (IOException e) {
			System.err.println("Problem reading the JSON file!");
			return null;
			// e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Problem reading the JSON file! Check its structure and try again!");
			return null;
			//e.printStackTrace();
		}
		return shapeList;
	}

	// Read the input XML file, processes it and returns arrayList of Shape objects
	public static ArrayList<Shape> readXmlImporter(String inputFilePath) {
		System.out.println("----------------------------");
		ArrayList<Shape> shapeList = new ArrayList<>();
		try {
			File fXmlFile = new File(inputFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			if (!doc.getDocumentElement().getNodeName().toString().equals("shapes")) {
				System.err.println("Invalid input XML format! Exiting...");
				return null;
			}
			NodeList nList = doc.getElementsByTagName("shape");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String shapeName = eElement.getElementsByTagName("name").item(0).getTextContent();
					if (shapeName.equals("ellipse")) {
						String axis1str = (String) eElement.getElementsByTagName("axis1").item(0).getTextContent();
						String axis2str = (String) eElement.getElementsByTagName("axis2").item(0).getTextContent();
						if(tryParseInt(axis1str) == null || tryParseInt(axis2str) == null) {
							continue;
						}
						int axis1 = Integer.parseInt(axis1str);
						int axis2 = Integer.parseInt(axis2str);
						if (axis1 <= 0 || axis2 <= 0) {
							System.err.println("Invalid shape: Axis of ellipse can't be zero or less!");
						} else {
							Shape ellipse = new Ellipse(axis1, axis2);
							shapeList.add(ellipse);
						}
					} else if (shapeName.equals("circle")) {
						String radiusStr = (String) eElement.getElementsByTagName("radius").item(0).getTextContent();
						if(tryParseInt(radiusStr) == null) {
							continue;
						}
						int radius = Integer.parseInt(radiusStr);
						if (radius <= 0) {
							System.err.println("Invalid shape: Radius of circle can't be zero or less!");
						} else {
							Shape circle = new Circle(radius);
							shapeList.add(circle);
						}
					} else if (shapeName.equals("triangle")) {
						String sideAstr = (String) eElement.getElementsByTagName("sidea").item(0).getTextContent();
						String sideBstr = (String) eElement.getElementsByTagName("sideb").item(0).getTextContent();
						String sideCstr = (String) eElement.getElementsByTagName("sidec").item(0).getTextContent();
						if(tryParseInt(sideAstr) == null || tryParseInt(sideBstr) == null || tryParseInt(sideCstr) == null) {
							continue;
						}
						int sideA = Integer.parseInt(sideAstr);
						int sideB = Integer.parseInt(sideBstr);
						int sideC = Integer.parseInt(sideCstr);
						if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
							System.err.println("Invalid shape: Side of a triangle cannot be zero or less!");
						} else if (sideA > sideB + sideC || sideB > sideA + sideC || sideC > sideA + sideB) {
							System.err.println("Invalid shape: Sum of two sides of a triangle cannot be less than third side!");
						} else if ((sideA == sideB) && (sideB == sideC)) {
							Shape eqTriangle = new EquilateralTriangle(sideA);
							shapeList.add(eqTriangle);
						} else if (sideA == sideB || sideB == sideC || sideC == sideA) {
							Shape isoTriangle = new IsocelesTriangle(sideA, sideB);
							shapeList.add(isoTriangle);
						} else {
							Shape scaTriangle = new ScaleneTriangle(sideA, sideB, sideC);
							shapeList.add(scaTriangle);
						}
					} else if (shapeName.equals("rectangle")) {
						String sideAstr = (String) eElement.getElementsByTagName("sidea").item(0).getTextContent();
						String sideBstr = (String) eElement.getElementsByTagName("sideb").item(0).getTextContent();
						if(tryParseInt(sideAstr) == null || tryParseInt(sideBstr) == null) {
							continue;
						}
						int sideA = Integer.parseInt(sideAstr);
						int sideB = Integer.parseInt(sideBstr);
						if (sideA <= 0 || sideB <= 0) {
							System.err.println("Invalid shape: Sides of rectangle cannot be zero or less!");
						} else {
							Shape rectangle = new Rectangle(sideA, sideB);
							shapeList.add(rectangle);
						}
					} else if (shapeName.equals("square")) {
						String sideStr = (String) eElement.getElementsByTagName("side").item(0).getTextContent();
						if(tryParseInt(sideStr) == null) {
							continue;
						}
						int side = Integer.parseInt(sideStr);
						if (side <= 0) {
							System.err.println("Invalid shape: Side of square cannot be zero or less!");
						} else {
							Shape square = new Square(side);
							shapeList.add(square);
						}
					} else {
						System.err.println(shapeName + ": is unrecognisable shape! Skipping!");
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Problem reading the XML file!");
			return null;
			// e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.err.println("Problem parsing the XML file!");
			return null;
			//e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Problem parsing the XML file!");
			return null;
			//e.printStackTrace();
		}
		return shapeList;
	}

	// Takes arrayList of shapes as input and an integer variable as option
	// to decide whether to print the calculated areas on console or not.
	private static ArrayList<TotalAreaInfo> calculateAreas(ArrayList<Shape> shapeList, int options) {
		if(shapeList == null) {
			return null;
		}
		int totalArea = 0;
		int ellipseArea = 0;
		int circleArea = 0;
		int converPolygonArea = 0;
		int triangleArea = 0;
		int equilateralTriangleArea = 0;
		int isoscelesTriangleArea = 0;
		int scaleneTriangleArea = 0;
		int rectangleArea = 0;
		int squareArea = 0;

		// Cycles through the list shapes, and add their area to appropriate int variables
		for (Shape s : shapeList) {
			String shapeName = s.getName();
			if (shapeName.equals("Ellipse")) {
				ellipseArea += s.area();
			} else if (shapeName.equals("Circle")) {
				circleArea += s.area();
				ellipseArea += s.area();
			} else if (shapeName.equals("Equilateral Triangle")) {
				converPolygonArea += s.area();
				triangleArea += s.area();
				equilateralTriangleArea += s.area();
			} else if (shapeName.equals("Isoceles Triangle")) {
				converPolygonArea += s.area();
				triangleArea += s.area();
				isoscelesTriangleArea += s.area();
			} else if (shapeName.equals("Scalene Triangle")) {
				converPolygonArea += s.area();
				triangleArea += s.area();
				scaleneTriangleArea += s.area();
			} else if (shapeName.equals("Rectangle")) {
				converPolygonArea += s.area();
				rectangleArea += s.area();
			} else if (shapeName.equals("Square")) {
				converPolygonArea += s.area();
				rectangleArea += s.area();
				squareArea += s.area();
			}
			totalArea += s.area();
		}

		// If the option is set as 0, to arrayList of TotalAreaInfo, add TotalAreaInfo objects 
		// containing the details of Shape objects like, shape name, total area, category and parent.
		ArrayList<TotalAreaInfo> listAreasTotal = null;
		if (options == 0) {
			listAreasTotal = new ArrayList<>();
			if (totalArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Total area of all shapes", totalArea, Shape.getCategory(), Shape.getParent()));
			}
			if (ellipseArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Ellipse", ellipseArea, Ellipse.getCategory(), Ellipse.getParent()));
			}
			if (circleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Circle", circleArea, Circle.getCategory(), Circle.getParent()));
			}
			if (converPolygonArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Convex Polygon", converPolygonArea, ConvexPolygon.getCategory(), ConvexPolygon.getParent()));
			}
			if (triangleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Triangle", triangleArea, Triangle.getCategory(), Triangle.getParent()));
			}
			if (equilateralTriangleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Equilateral Triangle", equilateralTriangleArea, EquilateralTriangle.getCategory(), EquilateralTriangle.getParent()));
			}
			if (isoscelesTriangleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Isosceles Triangle", isoscelesTriangleArea, IsocelesTriangle.getCategory(), IsocelesTriangle.getParent()));
			}
			if (scaleneTriangleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Scalene Triangle", scaleneTriangleArea, ScaleneTriangle.getCategory(), ScaleneTriangle.getParent()));
			}
			if (rectangleArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Rectangle", rectangleArea, Rectangle.getCategory(), Rectangle.getParent()));
			}
			if (squareArea != 0) {
				listAreasTotal.add(new TotalAreaInfo("Square", squareArea, Square.getCategory(), Square.getParent()));
			}
		}

		// If option passed is 1 the,, Output the formatted areas to the console
		if (options == 1) {
			System.out.println("-----------------------------");
			System.out .println(StringUtils.fixedLengthString("Total area of all Shapes: ", 26) + "\t\t\t\t" + totalArea);
			System.out.println(StringUtils.fixedLengthString("Ellipses: ", 26) + "\t\t\t" + ellipseArea);
			System.out.println(StringUtils.fixedLengthString("Circles: ", 26) + "\t\t" + circleArea);
			System.out.println(StringUtils.fixedLengthString("Non-Circle Ellipses: ", 26) + "\t\t" + (ellipseArea - circleArea));
			System.out.println(StringUtils.fixedLengthString("Convex Polygon: ", 26) + "\t\t\t" + converPolygonArea);
			System.out.println(StringUtils.fixedLengthString("All triangles: ", 26) + "\t\t" + triangleArea);
			System.out.println(StringUtils.fixedLengthString("Equilateral Triangle: ", 26) + "\t" + equilateralTriangleArea);
			System.out.println(StringUtils.fixedLengthString("Isoceles Triangle: ", 26) + "\t" + isoscelesTriangleArea);
			System.out.println(StringUtils.fixedLengthString("Scalene Triangle: ", 26) + "\t" + scaleneTriangleArea);
			System.out.println(StringUtils.fixedLengthString("Rectangles: ", 26) + "\t\t" + rectangleArea);
			System.out.println(StringUtils.fixedLengthString("Square: ", 26) + "\t" + squareArea);
		}

		return listAreasTotal;
	}
	
	// Check if the value read from json/xml is integer or not
	public static Integer tryParseInt(String text) {
		  try {
		    return Integer.parseInt(text);
		  } catch (NumberFormatException e) {
		    System.err.println(text + ": is an invalid integer value.");
		    return null;
		  }
		}

}
