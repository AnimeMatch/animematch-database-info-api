package animatch.app.domain.midialista.repository;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.midia.Midia;
import animatch.app.domain.midialista.MidiaLista;
import animatch.app.service.MidiaLista.dto.MidiaListaInfoDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MidiaListaRepository extends JpaRepository<MidiaLista,Integer> {
//    @Query("""
//            select new jakarta.persistence.Id(l.*)
//            from Lista l
//            """)
//    List<Lista> findAllById(int midiaId, int listaId);

    List<MidiaLista> findByListaId(Lista listaId);

    List<MidiaLista> findAll();
    @Query("""
        select a.midiaId
        from MidiaLista a
        """)
    List<Midia> findAllInfo();
    @Query("""
        select a.midiaId
        from MidiaLista a
        where a.listaId.id = ?1
    """)
    List<Midia> findAllMidiaInfoByListaId(int listaId);

    @Query("""
        select a.midiaId
        from MidiaLista a
        where a.listaId.id = ?1
    """)
    List<Midia> findAllMidiaPaginadoInfoByListaId(int listaId, Pageable paginacao);

    @Query("""
        select new animatch.app.service.MidiaLista.dto.MidiaListaInfoDTO(a.midiaId, a.listaId)
        from MidiaLista a
        join a.listaId Lista
        where Lista.userId.id = :userId
    """)
    List<MidiaListaInfoDTO> findAllMidiaListaInfoByUserId(int userId);

//    @Query("""
//        select new animatch.app.dto.MidiaListaInfoDTO(a.midiaId, a.listaId)
//        from MidiaLista a
//        join a.listaId Lista
//        where Lista.userId = ?1
//    """)
//    List<MidiaListaInfoDTO> findAllMidiaListaInfoByUserId(int userId);

//    @Query("""
//        select new animatch.app.service.MidiaLista.dto.MidiaListaInfoDTO(a.midiaId, a.listaId)
//        from MidiaLista a
//        where a.listaId.userId.id = ?1
//    """)
//    List<MidiaListaInfoDTO> findAllMidiaListaInfoByUserId(int userId);
//
    @Query("""
        select new animatch.app.service.MidiaLista.dto.MidiaListaInfoDTO(a.midiaId, a.listaId)
        from MidiaLista a
        where a.listaId.userId.id = ?1
    """)
    List<MidiaListaInfoDTO> findAllMidiaListaInfoByUserIdPaginacao(int userId, Pageable paginacao);

    @Modifying
    @Transactional
    @Query("DELETE FROM MidiaLista a WHERE a.listaId.id = ?1")
    void deleteAllByListaId(int listaId);

    @Modifying
    @Transactional
    @Query("""
            DELETE FROM MidiaLista a WHERE a.midiaId.id = ?1
            """)
    void deleteAllByMidiaId(int midiaId);

    @Query("""
        select new animatch.app.domain.midialista.MidiaLista(a.midiaListaId, a.midiaId, a.listaId)
        from MidiaLista a
        join a.listaId Lista
        where Lista.userId.email = ?1
            """)
    List<MidiaLista> findAllMidiaListaInfoByEmail(String email);

    @Query("""
        select new animatch.app.domain.midialista.MidiaLista(a.midiaListaId, a.midiaId, a.listaId)
        from MidiaLista a
        where a.listaId.userId.email = ?1
    """)
    List<MidiaLista> findAllMidiaListaInfoByEmailPaginacao(String email, Pageable paginacao);
}
