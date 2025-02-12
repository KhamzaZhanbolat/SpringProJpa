package kz.bitlab.JPASpringPro.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.JPASpringPro.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {

}
