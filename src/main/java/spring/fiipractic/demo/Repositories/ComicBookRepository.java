package spring.fiipractic.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.fiipractic.demo.models.ComicBook;

public interface ComicBookRepository extends JpaRepository<ComicBook,Integer> {

}
