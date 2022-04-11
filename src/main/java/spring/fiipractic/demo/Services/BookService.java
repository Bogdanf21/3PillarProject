package spring.fiipractic.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.fiipractic.demo.Repositories.BookRepository;
import spring.fiipractic.demo.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Book getBook(Integer id) {
      try {
          return bookRepository.findById(id).get();
      }
      catch (Exception e)
      {
          e.printStackTrace();
          return null;
      }

    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Integer id){
        try {
            bookRepository.deleteById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Book> getBooksWithTitleContaining(String partialTitle) {
        return bookRepository.findAll().stream()
                .filter(book -> book.titleContains(partialTitle))
                .sorted()
                .collect(Collectors.toList());
    }


    public List<Book> getBooksWithAuthorNameContaining(String partialName) {
        return  bookRepository.findAll().stream()
                .filter(book -> book.AuthorNameContains(partialName))
                .sorted()
                .collect(Collectors.toList());
    }

    public void generateBooks() {
        for(int i=0;i<50;i++)
            bookRepository.save(Book.generateRandom());
    }
}
