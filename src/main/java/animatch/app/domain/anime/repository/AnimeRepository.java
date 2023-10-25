package animatch.app.domain.anime.repository;

import animatch.app.domain.anime.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {
}
