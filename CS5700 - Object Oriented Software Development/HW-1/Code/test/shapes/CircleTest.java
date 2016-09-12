package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.Circle;
import shapes.Shape;

public class CircleTest {

	@Test
	public void test() {
		Shape circle = new Circle(7);
		int area = circle.area();
		assertTrue(area == (int)(Math.PI * Math.pow(7, 2)));
	}

}
