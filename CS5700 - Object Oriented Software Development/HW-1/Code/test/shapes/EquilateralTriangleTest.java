package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.EquilateralTriangle;
import shapes.Shape;

public class EquilateralTriangleTest {

	@Test
	public void test() {
		Shape eqTriangle = new EquilateralTriangle(6);
		int area = eqTriangle.area();
		assertTrue((int) ((Math.sqrt(3) / 4) * Math.pow(6, 2)) == area); 
	}

}
