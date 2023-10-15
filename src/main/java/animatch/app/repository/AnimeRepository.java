package animatch.app.repository;

import animatch.app.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {
}
