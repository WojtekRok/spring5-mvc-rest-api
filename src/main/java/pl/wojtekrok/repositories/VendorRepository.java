package pl.wojtekrok.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtekrok.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
