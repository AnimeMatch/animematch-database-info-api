package animatch.app.repository;

import animatch.app.domain.Anime;
import animatch.app.dto.AnimeInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {
}
