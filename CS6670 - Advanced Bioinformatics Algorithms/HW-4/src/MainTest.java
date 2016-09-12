import static org.junit.Assert.*;

/**
 * Created by Aditya on 7/16/2016.
 */
public class MainTest {

    @org.junit.Test
    public void testMinimumEditDistance() throws Exception {

        String sequence01 = "PLASMA";
        String sequence02 = "ALTRUISM";

        assertEquals(Main.minimumEditDistance(sequence01.toCharArray(), sequence02.toCharArray()), 6);

        String sequence11 = "SPARTAN";
        String sequence12 = "PART";

        assertEquals(Main.minimumEditDistance(sequence11.toCharArray(), sequence12.toCharArray()), 3);

        String sequence21 = "TGCATAT";
        String sequence22 = "ATCCGAT";

        assertEquals(Main.minimumEditDistance(sequence21.toCharArray(), sequence22.toCharArray()), 4);

        String sequence31 = "TCGAATTACG";
        String sequence32 = "TCGTTTACGT";

        assertEquals(Main.minimumEditDistance(sequence31.toCharArray(), sequence32.toCharArray()), 3);

    }


}