package spring.fiipractic.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.fiipractic.demo.Services.ClientService;
import spring.fiipractic.demo.Services.ComicBookService;
import spring.fiipractic.demo.models.Client;
import spring.fiipractic.demo.models.ComicBook;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients/getAllClients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/getClient/{ID}")
    public Client getClient(@PathVariable Integer ID) {
        return clientService.getClient(ID);
    }

    @GetMapping("/clients/getClient/name={partialName}")
    public List<Client> getClientWithNameContaining(@PathVariable String partialName) {
        return clientService.getClientWithNameContaining(partialName);
    }

    @PostMapping("/clients/generate")
    public void generateComicBooks() {
        clientService.generateClients();
    }

    @PostMapping("/clients/addClient")
    public void addComicBook(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @PutMapping("clients/updateClient")
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    @DeleteMapping("clients/deleteClient/{ID}")
    public String deleteClient(@PathVariable Integer ID) {
       try {
           clientService.deleteClient(ID);
       }
       catch (Exception e)
       {
           return e.getMessage();
       }
       return null;
    }
}
