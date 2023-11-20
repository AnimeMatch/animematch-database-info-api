package animatch.app.domain.lista.repository;

import animatch.app.domain.usuario.Usuario;
import animatch.app.dto.ListaInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import animatch.app.domain.lista.Lista;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista,Integer> {

    List<Lista> findAllByUserId(Usuario userId);

    @Query("""
            select new animatch.app.dto.ListaInfoDTO(l.id, l.name)
            from Lista l where l.userId in :userId
            """)
    List<ListaInfoDTO> findAllListaInfoByUserId(Usuario userId);

    @Query("""
            select new animatch.app.dto.ListaInfoDTO(l.id, l.name)
            from Lista l
            """)
    List<ListaInfoDTO> findAllInfo();

    Lista findListaById(int id);
}