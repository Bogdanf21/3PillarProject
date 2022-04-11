package spring.fiipractic.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.fiipractic.demo.models.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
