package shapes;

public class Square extends Rectangle {
	
	private final static int category = 10;
	private final static int parent = 9;
	private final String name = "Square";

	public Square(int side) {
		super(side, side);
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
