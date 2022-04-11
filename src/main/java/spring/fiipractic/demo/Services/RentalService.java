package spring.fiipractic.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.fiipractic.demo.Repositories.BookRepository;
import spring.fiipractic.demo.Repositories.ClientRepository;
import spring.fiipractic.demo.Repositories.ComicBookRepository;
import spring.fiipractic.demo.Repositories.RentalRepository;
import spring.fiipractic.demo.models.Rental;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ComicBookRepository comicBookRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void startRental(Rental rental) throws Exception {
        ////////////////////// IF ITEM DOESN'T EXIST ////////////
        if (!itemWithIdExists(rental.getItemId()))
            throw new Exception("Item with id " + rental.getItemId() + " not found in either Book or ComicBook section");
        ////////////////////// END IF ITEM DOESN'T EXIST ////////////


        ////////////////// IF USER DOES NOT EXIST /////////////////////
        if (!clientWithIdExists(rental.getClientId()))
            throw new Exception("Client with id " + rental.getClientId() + " not found in database");
        ////////////////// END IF USER DOES NOT EXIST ////////////////////


        ////////// IF RENTED ALREADY ///////////////////
        List<Rental> possibleRentals = rentalRepository.findAll()
                .stream()
                .filter(rental1 -> rental1.getItemId() == rental.getItemId()).collect(Collectors.toList());
        for (Rental rental1 : possibleRentals)
            if (rental1.getReturnedDate() == null || rental.getRentedDate().before(rental1.getReturnedDate()))
                throw new Exception("Item is already rented:\n" + rental1.toString());
        ////////// END IF RENTED ALREADY ///////////////////

        rentalRepository.save(rental);
    }


    public void endRental(Integer id) throws Exception {
        try {
            Rental rental = rentalRepository.findById(id).get();
            rental.setReturnedDate(new Date());
            rentalRepository.save(rental);
        } catch (Exception e) {
            throw new Exception("No rental with id " + id + " found");
        }

    }

    public void endRentalForItemWithId(Integer id) throws Exception {
        /////// ITEM NOT FOUND /////////////////
        if (!itemWithIdExists(id))
            throw new Exception("Item with id " + id + " not found in either Book or ComicBook section. Cannot end Rental");
        //////// END ITEM NOT FOUND ////////////////////////


        /////////// CHECK IF RENTAL EXISTS + END RENTAL ////////////////////////////
        try {
            List<Rental> list = rentalRepository.findAll().stream()
                    .filter(rental -> rental.getItemId() == id && rental.getReturnedDate() == null).collect(Collectors.toList());

            if (list.size() == 0) throw new Exception();

            list.forEach(
                    rental -> {
                        rental.setReturnedDate(new Date());
                        rentalRepository.save(rental);
                    });
        } catch (Exception e) {
            throw new Exception("No rental found for item with id " + id);
        }
        /////////// END CHECK IF RENTAL EXISTS + END RENTAL ////////////////////////////

    }

    public void addOldRental(Rental rental) throws Exception {
        if (!clientWithIdExists(rental.getClientId()))
            throw new Exception("User with id " + rental.getClientId() + " not found");
        if (!itemWithIdExists(rental.getItemId()))
            throw new Exception("Item with id " + rental.getItemId() + " not found");
        ///////////// IF ALREADY RENTED //////////////////

        List<Rental> possibleRentals = rentalRepository.findAll()
                .stream()
                .filter(rental1 -> rental1.getItemId() == rental.getItemId()).collect(Collectors.toList());
        for (Rental rental1 : possibleRentals) {

            if (rental1.getReturnedDate() == null)
                throw new Exception("Item was already rented:\n" + rental1.toString());

            if (rental.getRentedDate().before(rental1.getReturnedDate()) && rental1.getRentedDate().before(rental.getReturnedDate()))
                throw new Exception("Item was already rented:\n" + rental1.toString());
        }

        ///////////////////////////////////////////////////////


        rentalRepository.save(rental);

    }


    private boolean itemWithIdExists(Integer id) {
        if (bookRepository.findAll().stream()
                .filter(book -> book.getId() == id)
                .collect(Collectors.toList()).size() == 0
                && comicBookRepository.findAll().stream()
                .filter(comicBook -> comicBook.getId() == id)
                .collect(Collectors.toList()).size() == 0)
            return false;
        return true;
    }

    private boolean clientWithIdExists(Integer clientId) {
        if (
                clientRepository.findAll()
                        .stream().filter(client -> client.getId().equals(clientId))
                        .collect(Collectors.toList())
                        .size() == 0
        )
            return false;
        return true;
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void deleteRental(Integer id) {
        try {
            rentalRepository.deleteById(id);
        } catch (Exception e) {
            e.getMessage();

        }
    }
}
