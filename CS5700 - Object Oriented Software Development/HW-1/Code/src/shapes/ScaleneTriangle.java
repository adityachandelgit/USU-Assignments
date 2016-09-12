package shapes;

public class ScaleneTriangle extends Triangle {
	
	private final static int category = 8;
	private final static int parent = 5;
	private final String name = "Scalene Triangle";
	
	public ScaleneTriangle(int sideA, int sideB, int sideC) {
		super(sideA, sideB, sideC);
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
