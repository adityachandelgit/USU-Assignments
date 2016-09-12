import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 1/29/2016.
 */
public class JsonToCsv {

    static void test() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("D:\\file65.txt"));

        String csv = "D:\\output2.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        List<String[]> data = new ArrayList<>();

        for (Object o : a)
        {
            JSONObject person = (JSONObject) o;
            JSONObject user = (JSONObject) person.get("user");

            String createdAt = (String) person.get("createdAt");
            String tweetId = String.valueOf(person.get("id"));
            String text = (String) person.get("text");

            String userId = String.valueOf(user.get("id"));
            String userName = (String) user.get("name");
            String userLocation = (String) user.get("location");
            String friendsCount = String.valueOf(user.get("friendsCount"));
            String followersCount = String.valueOf(user.get("followersCount"));
            String language = (String) user.get("lang");

            data.add(new String[] {createdAt, tweetId, text, userId, userName, userLocation, friendsCount, followersCount, language});

        }

        writer.writeAll(data);
        writer.close();
    }

}
