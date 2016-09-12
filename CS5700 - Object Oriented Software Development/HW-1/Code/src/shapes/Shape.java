package shapes;

public abstract class Shape {
	
	private final static int category = 1;
	private final static int parent = 0;
	private final String name = "Shape";

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
