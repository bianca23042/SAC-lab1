package lab;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.*;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.exceptions.ApiException;
import com.recombee.api_client.util.Region;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecombeeScript {

    public static void main(String[] args) {
        RecombeeClient client = new RecombeeClient("university-politehnica-of-bucharest-dev", "2r3eC87SsXNr7SMYLnx9beQWPyBjq7DV9sTGNq7hAmPpxHM2Sm9IbkLmuU3cRap7").setRegion(Region.EU_WEST);

        //client.send(new ResetDatabase());
//        addItemsToDatabase(client);
//        addUsers(client);
//        addInteractions(client);
        getUserRecommendation(client, "Bianca", 5);
        getUserRecommendation(client, "Antonio", 5);
        getUserRecommendation(client, "Marlena", 5);
        getUserRecommendation(client, "Costi", 5);
        getUserRecommendation(client, "Alex", 5);
    }

    public static void addUsers(RecombeeClient client) {
        try {
            client.send(new AddUser("Bianca"));
            client.send(new AddUser("Antonio"));
            client.send(new AddUser("Marlena"));
            client.send(new AddUser("Costi"));
            client.send(new AddUser("Alex"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void getUserRecommendation(RecombeeClient client, String userId, long number) {
        System.out.println("Recommendations for " + userId.toUpperCase() + ":");
        try {
            Recommendation[] recommendations = client.send(new RecommendItemsToUser(userId, number)
                    .setReturnProperties(true)).getRecomms();
            for (var r : recommendations) {
                System.out.println(r.getId() + " " + r.getValues().get("title"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void addInteractions(RecombeeClient client) {
        try {
            client.send(new AddDetailView("Bianca", "s1"));
            client.send(new AddDetailView("Bianca", "s5"));
            client.send(new AddDetailView("Bianca", "s10"));
            client.send(new AddDetailView("Bianca", "s15"));
            client.send(new AddBookmark("Bianca", "s20"));

            client.send(new AddDetailView("Antonio", "s25"));
            client.send(new AddDetailView("Antonio", "s30"));
            client.send(new AddDetailView("Antonio", "s35"));
            client.send(new AddDetailView("Antonio", "s40"));
            client.send(new AddBookmark("Antonio", "s45"));

            client.send(new AddDetailView("Marlena", "s50"));
            client.send(new AddDetailView("Marlena", "s52"));
            client.send(new AddDetailView("Marlena", "s53"));
            client.send(new AddDetailView("Marlena", "s55"));
            client.send(new AddBookmark("Marlena", "s57"));

            client.send(new AddDetailView("Costi", "s60"));
            client.send(new AddDetailView("Costi", "s62"));
            client.send(new AddDetailView("Costi", "s63"));
            client.send(new AddDetailView("Costi", "s65"));
            client.send(new AddBookmark("Costi", "s66"));

            client.send(new AddDetailView("Alex", "s70"));
            client.send(new AddDetailView("Alex", "s74"));
            client.send(new AddDetailView("Alex", "s77"));
            client.send(new AddDetailView("Alex", "s79"));
            client.send(new AddBookmark("Alex", "s99"));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public static void addItemsToDatabase(RecombeeClient client) {
        try {
            List<NetflixMovie> netflixMovies = NetflixMovieParser.parse();
            System.out.println(netflixMovies.get(0).showId);
            for (var movie : netflixMovies) {
                System.out.println(movie.toString());
            }

            addItemProperties(client);

            for (var movie : netflixMovies) {
                client.send(new SetItemValues(movie.showId,
                        new HashMap<String, Object>() {{
                            put("type", movie.getType());
                            put("title", movie.getTitle());
                            put("director", movie.getDirector());
                            put("country", movie.getCountry());
                            put("releaseYear", movie.getReleaseYear());
                            put("rating", movie.getRating());
                            put("listed_in", movie.getListed_in());
                            put("description", movie.getDescription());
                        }}
                ).setCascadeCreate(true));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void addItemProperties(RecombeeClient client) {
        try {
            client.send(new AddItemProperty("showId", "string"));
            client.send(new AddItemProperty("type", "string"));
            client.send(new AddItemProperty("title", "string"));
            client.send(new AddItemProperty("director", "string"));
            client.send(new AddItemProperty("country", "string"));
            client.send(new AddItemProperty("releaseYear", "string"));
            client.send(new AddItemProperty("rating", "string"));
            client.send(new AddItemProperty("listed_in", "string"));
            client.send(new AddItemProperty("description", "string"));

        } catch (Exception exception) {
            System.out.println(exception.toString());
        }

    }




}
