import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aditya on 1/24/2016.
 */
public class Worker {

        void setup() throws IOException {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setOAuthConsumerKey("paHY2eshhxrBAwY6laiZCSeVy");
            cb.setOAuthConsumerSecret("7lH0BTCPwFtJvcavIRjXWkCeq2NgHLSC7aQ8phTIxDKxbTQH7S");
            cb.setOAuthAccessToken("4493024472-Ip6tGzAID8uq9JznDYwM5ecdiN92obmhqV6yi5c");
            cb.setOAuthAccessTokenSecret("dC1UeiZOXgcchMtd3cg2obuNJtqOLwtvSS3PYjdiUph33");

            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            Query query = new Query("(Trump) OR (#trump) OR (#DonaldTrump)");
            int numberOfTweets = 2500;
            long lastID = Long.MAX_VALUE;
            ArrayList<Status> tweets = new ArrayList<>();
            while (tweets.size () < numberOfTweets) {
                if (numberOfTweets - tweets.size() > 100)
                    query.setCount(100);
                else
                    query.setCount(numberOfTweets - tweets.size());
                try {
                    QueryResult result = twitter.search(query);
                    tweets.addAll(result.getTweets());
                    System.out.println("Gathered " + tweets.size() + " tweets");
                    for (Status t: tweets)
                        if(t.getId() < lastID) lastID = t.getId();

                }

                catch (TwitterException te) {
                    System.out.println("Couldn't connect: " + te);
                }
                query.setMaxId(lastID-1);
            }

            for (int i = 0; i < tweets.size(); i++) {
                Status t = (Status) tweets.get(i);

                GeoLocation loc = t.getGeoLocation();

                String user = t.getUser().getScreenName();
                String msg = t.getText();
                String time = "";
                if (loc!=null) {
                    Double lat = t.getGeoLocation().getLatitude();
                    Double lon = t.getGeoLocation().getLongitude();
                    System.out.println(i + " USER: " + user + " wrote: " + msg + " located at " + lat + ", " + lon);
                }
                else
                    System.out.println(i + " USER: " + user + " wrote: " + msg);
            }

            Gson gson = new GsonBuilder().create();
            JsonArray myCustomArray = gson.toJsonTree(tweets).getAsJsonArray();

            org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
            obj.put("tweets", myCustomArray);

            try (FileWriter file = new FileWriter("D:/file1.txt")) {
                file.write(obj.toJSONString());
            }

        }

}
