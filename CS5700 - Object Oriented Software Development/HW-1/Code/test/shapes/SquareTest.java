package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.Shape;
import shapes.Square;

public class SquareTest {

	@Test
	public void test() {
		Shape square = new Square(10);
		int sqArea = square.area();
		
		assertTrue(sqArea == 10 * 10);
	}

}
