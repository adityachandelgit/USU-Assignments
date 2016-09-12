package shapes;

public class Circle extends Ellipse {
	
	private final static int category = 3;
	private final static int parent = 2;
	private final String name = "Circle";

	public Circle(int radius) {
		super(radius, radius);
	}

	public static int getCategory() {
		return category;
	}

	public static int getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}
	
}
