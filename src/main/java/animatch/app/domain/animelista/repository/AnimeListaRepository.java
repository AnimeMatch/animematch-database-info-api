package animatch.app.domain.animelista.repository;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.service.Anime.dto.AnimeInfoDTO;
import animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO;
import animatch.app.domain.usuario.Usuario;
import animatch.app.service.AnimeLista.dto.AnimeListaInfoDadosBrutosDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AnimeListaRepository extends JpaRepository<AnimeLista,Integer> {
//    @Query("""
//            select new jakarta.persistence.Id(l.*)
//            from Lista l
//            """)
//    List<Lista> findAllById(int animeId, int listaId);

    @Query("""
        select a.animeId
        from AnimeLista a
        """)
    List<Anime> findAllInfo();
    @Query("""
        select a.animeId
        from AnimeLista a
        where a.listaId.id = ?1
    """)
    List<Anime> findAllAnimeInfoByListaId(int listaId);

    @Query("""
        select a.animeId
        from AnimeLista a
        where a.listaId.id = ?1
    """)
    List<Anime> findAllAnimePaginadoInfoByListaId(int listaId, Pageable pageable);

    @Query("""
        select new animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
        from AnimeLista a
        join a.listaId Lista
        where Lista.userId.id = :userId
    """)
    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserId(int userId);

//    @Query("""
//        select new animatch.app.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
//        from AnimeLista a
//        join a.listaId Lista
//        where Lista.userId = ?1
//    """)
//    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserId(int userId);

//    @Query("""
//        select new animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
//        from AnimeLista a
//        where a.listaId.userId.id = ?1
//    """)
//    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserId(int userId);
//
    @Query("""
        select new animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
        from AnimeLista a
        where a.listaId.userId.id = ?1
    """)
    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserIdPaginacao(Usuario userId, Pageable paginacao);

    @Modifying
    @Transactional
    @Query("DELETE FROM AnimeLista a WHERE a.listaId = :listaId")
    void deleteAllByListaId(int listaId);
}
