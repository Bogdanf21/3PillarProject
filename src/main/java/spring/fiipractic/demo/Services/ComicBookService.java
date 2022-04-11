package spring.fiipractic.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.fiipractic.demo.Repositories.ComicBookRepository;
import spring.fiipractic.demo.Repositories.RentalRepository;
import spring.fiipractic.demo.models.ComicBook;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicBookService {
    @Autowired
    ComicBookRepository comicBookRepository;
    @Autowired
    RentalRepository rentalRepository;


    public List<ComicBook> getAllComicBooks() {
        return comicBookRepository.findAll()
                .stream().sorted()
                .collect(Collectors.toList());
    }

    public ComicBook getComicBook(Integer id){

        try {
            return comicBookRepository.findById(id).get();
        }
        catch(Exception e)
        {
           e.printStackTrace();
            return null;
        }
    }

    public List<ComicBook> getComicBooksWithSeriesContaining(String partialSeries) {
        return comicBookRepository.findAll().stream()
                .filter(comicBook -> comicBook.seriesContains(partialSeries))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<ComicBook> getComicBooksWithNumber(Integer number) {
        return comicBookRepository.findAll().stream()
                .filter(comicBook -> comicBook.seriesNumberIs(number))
                .sorted()
                .collect(Collectors.toList());
    }

    public void generateComicBooks() {
        for(int i=0;i<50;i++)
            comicBookRepository.save(ComicBook.generateRandom());
    }

    public void addComicBook(ComicBook comicBook) {
        comicBookRepository.save(comicBook);
    }

    public void updateComicBook(ComicBook comicBook) {
        comicBookRepository.save(comicBook);
    }

    public void deleteComicBook(Integer id) {
        try{
            comicBookRepository.deleteById(id);
//            rentalRepository.findAll().stream()
//                    .filter(rental -> rental.getItemId() == id)
//                    .forEach(rental -> rentalRepository.delete(rental));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
