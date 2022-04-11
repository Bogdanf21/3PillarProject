package spring.fiipractic.demo.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Rentals",schema = "bookify")
public class Rental implements Comparable<Rental> {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;
    private Integer itemId;
    private Integer clientId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date rentedDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date returnedDate = null;


    public Rental(Integer itemId, Integer clientId, Date rentedDate) {
        this.itemId = itemId;
        this.clientId = clientId;
        this.rentedDate = rentedDate;
    }

    @Override
    public int compareTo(Rental o) {
        if(this.clientId.compareTo(o.clientId) != 0) return this.clientId.compareTo(o.clientId);
        if(itemId.compareTo(o.itemId) != 0) return itemId.compareTo(o.itemId);
        return this.rentedDate.compareTo(o.rentedDate);
    }


}


