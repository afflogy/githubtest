package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Region;

public class RegionRepository extends JpaRepository<Region, Integer> {
    public boolean existsById(Long value) {
        return false;
    }

    public <T> ScopedValue<T> findById(Long regionId) {
        return null;
    }
}
