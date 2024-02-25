package animatch.app.domain.lista.repository;

import animatch.app.domain.usuario.Usuario;
import animatch.app.service.lista.dto.ListaInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import animatch.app.domain.lista.Lista;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista,Integer> {

    List<Lista> findAllByUserId(Usuario userId);

    @Query("""
            select new animatch.app.service.lista.dto.ListaInfoDTO(l.id, l.name)
            from Lista l
            where l.userId.id = ?1
            """)
    List<ListaInfoDTO> findAllListaInfoByUserId(int userId);

    @Query("""
            select new animatch.app.service.lista.dto.ListaInfoDTO(l.id, l.name)
            from Lista l
            """)
    List<ListaInfoDTO> findAllInfo();

    Lista findListaById(int id);

    Lista findById(int listaId);

    @Query("""
            select new animatch.app.service.lista.dto.ListaInfoDTO(l.id, l.name, l.type)
            from Lista l
            where l.userId.email = ?1
            """)
    List<ListaInfoDTO> findAllListaInfoByEmail(String email);

    @Query("""
            select new animatch.app.service.lista.dto.ListaInfoDTO(l.id, l.name, l.type)
            from Lista l
            where l.userId.email = ?1 and l.name = 'Favoritos' and l.type = ?2
            """)
    ListaInfoDTO findListaFavoritoByEmail(String email, int type);
}
