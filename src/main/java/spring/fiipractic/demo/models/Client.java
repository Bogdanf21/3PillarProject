package spring.fiipractic.demo.models;


import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Clients",schema = "bookify")
public class Client implements Comparable<Client> {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String name;

    Client(String name){this.name = name;}


    public boolean nameContains(String partialName)
    {
        return this.name.toLowerCase().contains(partialName.toLowerCase());
    }

    public static Client generateRandom()
    {
        Faker faker = new Faker();
        return new Client(faker.name().fullName());
    }
    @Override
    public int compareTo(Client o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
