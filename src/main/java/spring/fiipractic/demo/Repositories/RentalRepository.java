package spring.fiipractic.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.fiipractic.demo.models.Rental;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
