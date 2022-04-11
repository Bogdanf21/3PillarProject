package spring.fiipractic.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import spring.fiipractic.demo.Services.RentalService;
import spring.fiipractic.demo.models.Rental;

import java.util.List;

@RestController
public class RentalController {
    @Autowired
    RentalService rentalService;

    @GetMapping("rentals/getAllRentals")
    public List<Rental> getAllRentals()
    {
        return rentalService.getAllRentals();
    }

    @PostMapping("rentals/startRental") // pentru start date primit prin post, end date null (neprimit prin post) (n-am stiut cum sa il oblig sa faca asta :( )
    public String startRental(@RequestBody Rental rental)
    {
        try {
            rentalService.startRental(rental);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    return null;
    }

    @PostMapping("rentals/addOldRental") // pentru start date si end date primit prin post (un rental trecut)  (n-am stiut cum sa il oblig la body sa intre aici :( )
    public String addOldRental(@RequestBody Rental rental)
    {
        try
        {
            rentalService.addOldRental(rental);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
        return null;

    }

    @PutMapping("rentals/endRental/{id}") // pentru un rental primit prin startRental, schimba end date-ul cu date-u curent
    public String endRental(@PathVariable Integer id)
    {
        try {
            rentalService.endRental(id);
        }catch (Exception e)
        {
            return e.getMessage();
        }
        return null;
    }

    @PutMapping("rentals/endRentalForItemWithId/{id}")
    public String endRentalForItemWithId(@PathVariable Integer id)
    {
        try {
            rentalService.endRentalForItemWithId(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    @PutMapping("rentals/updateRental")
    public void updateRental(@RequestBody Rental rental)
    {
        rentalService.updateRental(rental);
    }

    @DeleteMapping("rentals/deleteRental/{id}")
    public void deleteRental(@PathVariable Integer id)
    {
        rentalService.deleteRental(id);
    }

}
