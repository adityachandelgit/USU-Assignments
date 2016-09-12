package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.Rectangle;
import shapes.Shape;

public class RectangleTest {

	@Test
	public void test() {
		Shape rectangle = new Rectangle(12, 14);
		int areaRect = rectangle.area();
		assertTrue(areaRect == 12 * 14);
	}

}
