package spring.fiipractic.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.fiipractic.demo.Services.ComicBookService;
import spring.fiipractic.demo.models.ComicBook;

import java.util.*;


@RestController
public class ComicBooksController {

    @Autowired
    ComicBookService comicBookService;

    @GetMapping("/comicBooks/getAllComicBooks")
    public List<ComicBook> getAllComicBooks()
    {
        return comicBookService.getAllComicBooks();
    }

    @GetMapping("comicBooks/getComicBook/{ID}")
    public ComicBook getComicBook(@PathVariable Integer ID)
    {

            return comicBookService.getComicBook(ID);

    }

    @GetMapping("comicBooks/getComicBook/title={partialSeries}")
    public List<ComicBook> getComicBooksWithTitleContaining(@PathVariable String partialSeries)
    {
        return comicBookService.getComicBooksWithSeriesContaining(partialSeries);
    }

    @GetMapping("comicBooks/getComicBook/number={number}")
    public List<ComicBook> getComicBooksWithNumber(@PathVariable Integer number)
    {
        return comicBookService.getComicBooksWithNumber(number);
    }

    @PostMapping("/comicBooks/generate")
    public void generateComicBooks()
    {
        comicBookService.generateComicBooks();
    }

    @PostMapping("/comicBooks/addComicBook")
    public void addComicBook(@RequestBody ComicBook comicBook)
    {
        comicBookService.addComicBook(comicBook);
    }

    @PutMapping("comicBooks/updateComicBook")
    public void updateComicBook(@RequestBody ComicBook comicBook)
    {
        comicBookService.updateComicBook(comicBook);
    }

    @DeleteMapping("comicBooks/deleteComicBook/{ID}")
    public void deleteComicBook(@PathVariable Integer ID)
    {
        comicBookService.deleteComicBook(ID);
    }


}
