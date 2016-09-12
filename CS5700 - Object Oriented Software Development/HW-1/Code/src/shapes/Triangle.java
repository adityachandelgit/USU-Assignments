package shapes;

public class Triangle extends ConvexPolygon {
	
	private final static int category = 5;
	private final static int parent = 4;
	private final String name = "Triangle";

	private int side1;
	private int side2;
	private int side3;

	public Triangle(int sideA, int sideB, int sideC) {
		super();
		this.side1 = sideA;
		this.side2 = sideB;
		this.side3 = sideC;
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

	// Heron's formula
	@Override
	public int area() {
		double halfPerimeter;
		int area;
		halfPerimeter = ((side1 + side2 + side3) / 2.0);
		area =  (int) Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
		return Math.round(area * 100) / 100;
	}

}
