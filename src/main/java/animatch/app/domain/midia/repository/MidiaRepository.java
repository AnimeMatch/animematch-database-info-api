package animatch.app.domain.midia.repository;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midialista.MidiaLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    Midia findByIdApi(int idApi);
    Midia findById(int id);

    Boolean existsByIdApi(int idApi);

    @Query("""
            SELECT COUNT(al)
            FROM MidiaLista al
            JOIN al.midiaId a
            JOIN al.listaId l
            WHERE a.id = ?1 AND l.id = 2
                """)
    Integer qtdDeslikesMidia(Integer id);

    @Query("""
            SELECT SUM(a.likes)
            from Midia a
            where a.id = ?1
                    """)
    Integer qtdLikesMidia(Integer id);

    @Query("""
            SELECT COUNT(al)
            FROM MidiaLista al
            JOIN al.midiaId a
            JOIN al.listaId l
            WHERE a.id = ?1 AND l.id = 3
                """)
    Integer qtdAssistido(Integer id);

    List<Midia> findAllByOrderByLikesDesc();

    List<Midia> findAllByOrderByNotaMediaDesc();

    boolean existsByIdApi(Integer integer);
}
