package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.Triangle;

public class TriangleTest {

	@Test
	public void test() {
		
		Triangle triangle = new Triangle(5, 10, 20);
		int triangleArea = triangle.area();
		
		// Heron's formula for calculating triangle's area
		double halfPerimeter;
		int area;
		int side1 = 5, side2 = 10, side3 = 20;
		halfPerimeter = ((side1 + side2 + side3) / 2.0);
		area =  (int) Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
	
		assertTrue(triangleArea == area);
	}

}
