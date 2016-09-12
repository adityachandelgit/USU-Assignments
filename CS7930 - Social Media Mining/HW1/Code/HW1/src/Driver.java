import org.json.simple.parser.ParseException;
import twitter4j.TwitterException;

import java.io.IOException;

/**
 * Created by Aditya on 1/24/2016.
 */
public class Driver {

    public static void main(String args[]) throws TwitterException, IOException, ParseException {

        Worker w = new Worker();
        w.setup();

        //JsonToCsv.test();

    }
}
