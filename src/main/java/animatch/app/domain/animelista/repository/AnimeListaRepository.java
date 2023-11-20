package animatch.app.domain.animelista.repository;

import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.lista.Lista;
import animatch.app.dto.AnimeInfoDTO;
import animatch.app.dto.AnimeListaInfoDTO;
import animatch.app.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
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

    List<AnimeLista> findByListaId(Lista listaId);
    @Query("""
        select new animatch.app.dto.AnimeInfoDTO(a.animeId)
        from AnimeLista a
        """)
    List<AnimeInfoDTO> findAllInfo();

//    @Query("""
//        select new animatch.app.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
//        from AnimeLista a
//        join a.listaId Lista
//        where Lista.userId = :userId
//    """)
//    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserId(int userId);

    @Query("""
        select new animatch.app.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
        from AnimeLista a
        join a.listaId Lista
        where Lista.userId = :userId
    """)
    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserId(Usuario userId);

    @Query("""
        select new animatch.app.dto.AnimeListaInfoDTO(a.animeId, a.listaId)
        from AnimeLista a
        join a.listaId Lista
        where Lista.userId = :userId
    """)
    List<AnimeListaInfoDTO> findAllAnimeListaInfoByUserIdPaginacao(Usuario userId, Pageable paginacao);

    @Query("""
        select new animatch.app.dto.AnimeInfoDTO(a.animeId)
        from AnimeLista a
        join a.listaId Lista
        where Lista.id = :listaId
    """)
    List<AnimeInfoDTO> findAllAnimePaginadoInfoByListaId(int listaId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM AnimeLista a WHERE a.listaId = :listaId")
    void deleteAllByListaId(int listaId);
}
