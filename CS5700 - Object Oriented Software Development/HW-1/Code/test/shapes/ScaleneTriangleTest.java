package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.ScaleneTriangle;
import shapes.Shape;

public class ScaleneTriangleTest {

	@Test
	public void test() {
		
		Shape scaTriangle = new ScaleneTriangle(3, 5, 7);
		int areaSca = scaTriangle.area();
		
		// Heron's formula for triangle's area calculation
		double halfPerimeter;
		int heronArea;
		halfPerimeter = (3 + 5 + 7) / 2.0;
		heronArea =  (int) (Math.sqrt(halfPerimeter * (halfPerimeter - 3) * (halfPerimeter - 5) * (halfPerimeter - 7)));
		
		System.out.println(areaSca);
		System.out.println(heronArea);
		
		assertTrue(areaSca == heronArea);
	}

}
