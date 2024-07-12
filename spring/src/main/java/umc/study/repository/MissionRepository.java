package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;

public class MissionRepository extends JpaRepository<Mission, Long> {
}
