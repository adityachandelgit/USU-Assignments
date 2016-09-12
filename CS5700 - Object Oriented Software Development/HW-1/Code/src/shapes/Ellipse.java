package shapes;

public class Ellipse extends Shape {

	private final static int category = 2;
	private final static int parent = 1;
	private final String name = "Ellipse";

	private int axis1;
	private int axis2;

	public Ellipse(int axis1, int axis2) {
		this.axis1 = axis1;
		this.axis2 = axis2;
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
		return (int) (Math.PI * axis1 * axis2);
	}

}
