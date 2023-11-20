package animatch.app.domain.anime.repository;

import animatch.app.domain.anime.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {
    Anime findByIdApi(int idApi);

    Boolean existsByIdApi(int idApi);

    @Query("""
        SELECT COUNT(al)
        FROM AnimeLista al
        JOIN al.animeId a
        JOIN al.listaId l
        WHERE a.id = ?1 AND l.id = 2
            """)
    Integer qtdDeslikesAnime(Integer id);

    @Query("""
    select animatch.app.dto.AnimeLikesDto(a.likes)
    from Anime a
    where a.id = ?1
            """)
    Integer qtdLikesAnime(Integer id);

    @Query("""
        SELECT COUNT(al)
        FROM AnimeLista al
        JOIN al.animeId a
        JOIN al.listaId l
        WHERE a.id = ?1 AND l.id = 3
            """)
    Integer qtdAssistido(Integer id);

    List<Anime> findAllOrderByLikes();
}
