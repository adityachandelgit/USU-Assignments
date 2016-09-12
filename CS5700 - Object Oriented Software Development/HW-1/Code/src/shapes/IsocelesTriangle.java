package shapes;

public class IsocelesTriangle extends Triangle {
	
	private final static int category = 7;
	private final static int parent = 5;
	private final String name = "Isoceles Triangle";

	public IsocelesTriangle(int twoSameSides, int otherSide) {
		super(twoSameSides, twoSameSides, otherSide);
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
