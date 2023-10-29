package lab;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;
import java.util.Date;

public class NetflixMovie {
    @CsvBindByPosition(position = 0)
    String showId;
    @CsvBindByPosition(position = 1)
    String type;
    @CsvBindByPosition(position = 2)
    String title;
    @CsvBindByPosition(position = 3)
    String director;
    @CsvBindByPosition(position = 4)
    String country;
    @CsvBindByPosition(position = 5)
    String releaseYear;
    @CsvBindByPosition(position = 6)
    String rating;
    @CsvBindByPosition(position = 7)
    String listed_in;
    @CsvBindByPosition(position = 8)
    String description;

    public NetflixMovie() {
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getListed_in() {
        return listed_in;
    }

    public void setListed_in(String listed_in) {
        this.listed_in = listed_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NetflixMovie{" +
                "showId='" + showId + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", rating='" + rating + '\'' +
                ", listed_in='" + listed_in + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
