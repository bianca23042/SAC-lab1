package lab;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NetflixMovieParser {
    public static List<NetflixMovie> parse() throws IOException {
        FileReader fileReader = new FileReader("C:\\work-related\\facultate-related\\Recombee-lab\\src\\main\\java\\lab\\netflix_titles.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        return new CsvToBeanBuilder(bufferedReader)
                .withType(NetflixMovie.class)
                .build()
                .parse();
    }
}
