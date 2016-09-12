package shapes;

public abstract class ConvexPolygon extends Shape {
	
	private final static int category = 4;
	private final static int parent = 1;
	private final String name = "Convex Polygon";

	public abstract int area();

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
