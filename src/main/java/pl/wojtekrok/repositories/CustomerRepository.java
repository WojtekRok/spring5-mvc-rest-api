package pl.wojtekrok.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtekrok.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
