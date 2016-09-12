package utilities;

public class TotalAreaInfo {
	
	private String shapeName;
	private int area;
	private int category;
	private int parentCateogry;
	
	public TotalAreaInfo(String shapeName, int area, int category, int parentCateogry) {
		super();
		this.shapeName = shapeName;
		this.area = area;
		this.category = category;
		this.parentCateogry = parentCateogry;
	}

	public String getShapeName() {
		return shapeName;
	}
	
	public int getArea() {
		return area;
	}
	
	public int getCategory() {
		return category;
	}
	
	public int getParentCateogry() {
		return parentCateogry;
	}

}
