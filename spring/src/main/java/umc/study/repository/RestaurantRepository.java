package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
