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
@Table(name = "Books",schema = "bookify")
public class Book implements Comparable<Book> {

 @Id
 @GeneratedValue
 @Setter(AccessLevel.NONE)
 private Integer id;

 private String author;
 private String title;

 public boolean titleContains(String partialTitle)
 {
  return this.title.toLowerCase().contains(partialTitle.toLowerCase());
 }


 public boolean AuthorNameContains(String partialName) {
  return this.author.toLowerCase().contains(partialName.toLowerCase());
 }


 public static Book generateRandom()
 {
  Faker faker = new Faker();
  Book book = new Book();
  return new Book(faker.book().author(),faker.book().title());
 }


 public Book(String author, String title) {
  this.author = author;
  this.title = title;
 }

 @Override
 public int compareTo(Book o) {
  if(this.title.compareToIgnoreCase(o.title) != 0) return this.title.compareToIgnoreCase(o.title); //books have already an order from title (this.title != o.title)


  return this.author.compareToIgnoreCase(o.author);
 }
}
