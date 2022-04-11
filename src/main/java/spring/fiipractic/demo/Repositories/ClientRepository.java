package spring.fiipractic.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.fiipractic.demo.models.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
