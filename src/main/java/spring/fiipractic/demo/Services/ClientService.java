package spring.fiipractic.demo.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.fiipractic.demo.Repositories.ClientRepository;
import spring.fiipractic.demo.Repositories.RentalRepository;
import spring.fiipractic.demo.models.Client;
import spring.fiipractic.demo.models.ComicBook;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
@Autowired
    ClientRepository clientRepository;
@Autowired
    RentalRepository rentalRepository;

    public List<Client> getAllClients() {
    return   clientRepository.findAll()
            .stream()
            .sorted()
            .collect(Collectors.toList());
    }

    public Client getClient(Integer id) {
        try {
            return clientRepository.findById(id).get();
        }
        catch(Exception e)
        {return null;}
    }

    public List<Client> getClientWithNameContaining(String partialName) {
        return clientRepository.findAll()
                .stream()
                .filter(client -> client.nameContains(partialName)).collect(Collectors.toList());
    }

    public void generateClients() {
        for(int i=0;i<50;i++)
            clientRepository.save(Client.generateRandom());
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(Integer id) throws Exception {
        try{
            clientRepository.deleteById(id);
           /* rentalRepository.findAll().stream()
                    .filter(rental -> rental.getClientId() == id)
                    .forEach(rental -> rentalRepository.delete(rental));*/
        }
        catch(Exception e)
        {
            throw new Exception("Client with Id " + id + " not found");
        }

    }
}
