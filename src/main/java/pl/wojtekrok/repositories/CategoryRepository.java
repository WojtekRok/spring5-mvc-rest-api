package pl.wojtekrok.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojtekrok.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
