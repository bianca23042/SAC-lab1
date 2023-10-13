package lab;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddItemProperty;
import com.recombee.api_client.api_requests.ResetDatabase;
import com.recombee.api_client.api_requests.SetItemValues;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecombeeScript {

    public static void main(String[] args) {
        RecombeeClient client = new RecombeeClient("university-politehnica-of-bucharest-dev", "2r3eC87SsXNr7SMYLnx9beQWPyBjq7DV9sTGNq7hAmPpxHM2Sm9IbkLmuU3cRap7").setRegion(Region.EU_WEST);

        //client.send(new ResetDatabase());
        addItemsToDatabase(client);
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
            e.printStackTrace();
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
            exception.printStackTrace();
        }

    }




}
