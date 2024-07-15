package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Restaurant> existsById(Long value);

    Optional<Restaurant> findById(Long regionId);
}
