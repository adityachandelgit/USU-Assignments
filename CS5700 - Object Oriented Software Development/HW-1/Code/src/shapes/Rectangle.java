package shapes;

public class Rectangle extends ConvexPolygon {
	
	private final static int category = 9;
	private final static int parent = 4;
	private final String name = "Rectangle";
	
	private double length;
	private double breadth;
	
	public Rectangle(int length, int breadth) {
		this.length = length;
		this.breadth = breadth;
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

	@Override
	public int area() {
		return (int) (length * breadth);
	}

}
