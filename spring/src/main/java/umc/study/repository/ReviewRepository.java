package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //메소드 이름만으로 SQL을 만들어주는 기능을 활용함
    Page<Review> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
}
