package spring.fiipractic.demo.models;


import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "ComicBooks",schema = "bookify")
public class ComicBook implements Comparable<ComicBook> {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String series;
    private Integer number;

    public boolean seriesContains(String partialSeries)
    {
        return this.series.toLowerCase().contains(partialSeries.toLowerCase());
    }


    public boolean seriesNumberIs(Integer number) {
        return this.number == number;
    }


    public static ComicBook generateRandom()
    {
        Faker faker = new Faker();
        return new ComicBook(faker.book().title(),faker.number().numberBetween(1,42));
    }


    public ComicBook(String series, Integer number)
    {
        this.series = series;
        this.number = number;
    }

    @Override
    public int compareTo(ComicBook other) {
        if(this.series.compareToIgnoreCase(other.series) != 0) return this.series.compareToIgnoreCase(other.series); //books have already an order from title (this.title != o.title)


        return this.number.compareTo(other.getNumber());
    }
}
