package shapes;

public class EquilateralTriangle extends Triangle {
	
	private final static int category = 6;
	private final static int parent = 5;
	private final String name = "Equilateral Triangle";

	public EquilateralTriangle(int sideA) {
		super(sideA, sideA, sideA);
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
