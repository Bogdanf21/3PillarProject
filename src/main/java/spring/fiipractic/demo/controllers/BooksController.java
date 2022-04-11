package spring.fiipractic.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.fiipractic.demo.Services.BookService;
import spring.fiipractic.demo.models.Book;

import java.util.*;
@RestController
public class BooksController {

    @Autowired
    BookService bookService;

    @GetMapping("/books/getAllBooks")
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }

    @GetMapping("books/getBook/{ID}")
    public Book getBook(@PathVariable Integer ID)
    {
            return bookService.getBook(ID);
    }

    @GetMapping("books/getBook/title={partialTitle}")
    public List<Book> getBooksWithTitleContaining(@PathVariable String partialTitle)
    {
        return bookService.getBooksWithTitleContaining(partialTitle);
    }

    @GetMapping("books/getBook/author={partialName}")
    public List<Book> getBooksWithAuthorNameContaining(@PathVariable String partialName)
    {
        return bookService.getBooksWithAuthorNameContaining(partialName);
    }

    @PostMapping("/books/generate") // Nu e ok ce am facut aici ca daca face cineva 5 call-uri din astea a picat baza de date. Am lasat totusi functiile de generate ca poate e mai usor si pentru tine sa te uiti peste proiect :)
    public void generateBooks()
    {
     bookService.generateBooks();
    }

    @PostMapping("/books/addBook")
    public void addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
    }


    @PutMapping("books/updateBook")
    public void updateBook(@RequestBody Book book)
    {
        bookService.updateBook(book);
    }

    @DeleteMapping("books/deleteBook/{ID}")
    public void deleteBook(@PathVariable Integer ID)
    {
            bookService.deleteBook(ID);
    }




}
