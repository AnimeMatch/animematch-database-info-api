package animatch.app.repository;

import animatch.app.domain.Anime;
import animatch.app.domain.AnimeLista;
import animatch.app.domain.Lista;
import animatch.app.dto.AnimeInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnimeListaRepository extends JpaRepository<AnimeLista,Integer> {
//    @Query("""
//            select new jakarta.persistence.Id(l.*)
//            from Lista l
//            """)
//    List<Lista> findAllById(int animeId, int listaId);

    @Query("""
        select new animatch.app.dto.AnimeInfoDTO(a.animeId)
        from AnimeLista a where a.listaId in :listaId
        """)
    List<AnimeInfoDTO> findAllAnimeInfoByListaId(Lista listaId);
    @Query("""
        select new animatch.app.dto.AnimeInfoDTO(a.animeId)
        from AnimeLista a
        """)
    List<AnimeInfoDTO> findAllInfo();

}
