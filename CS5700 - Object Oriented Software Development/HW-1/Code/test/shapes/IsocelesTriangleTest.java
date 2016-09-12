package shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import shapes.IsocelesTriangle;
import shapes.Shape;

public class IsocelesTriangleTest {

	@Test
	public void test() {
		Shape isoTriangle = new IsocelesTriangle(10, 5);
		int areaIso = isoTriangle.area();
		
		// Heron's formula for triangle's area calculation
		double halfPerimeter;
		int heronArea;
		halfPerimeter = (10 + 10 + 5) / 2.0;
		heronArea =  (int) (Math.sqrt(halfPerimeter * (halfPerimeter - 10) * (halfPerimeter - 10) * (halfPerimeter - 5)));
		
		System.out.println(areaIso);
		System.out.println(heronArea);
		
		assertTrue(areaIso == heronArea);
		
		
	}

}
