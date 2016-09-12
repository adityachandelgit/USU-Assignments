package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.Ellipse;
import shapes.Shape;

public class EllipseTest {

	@Test
	public void testArea() {
		Shape ellipse = new Ellipse(4, 5);
		int areaEllipse = ellipse.area();
		assertTrue(areaEllipse == (int)( Math.PI * 4 * 5));
	}

}
