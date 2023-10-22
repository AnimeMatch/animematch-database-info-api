package animatch.app.repository;

import animatch.app.model.Comentario;
import animatch.app.dto.ComentarioCompletoDTO;
import animatch.app.dto.ComentarioSimplesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Comentario findComentarioById(int id);

    @Query("""
        select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto)
        from Comentario c
        where c.comentarioPai.id = :idComentario
    """)
    List<ComentarioSimplesDTO> findByComentarioPaiId(int idComentario);

    @Query("""
        select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto)
        from Comentario c
        where c.topico.id = :idTopico
    """)
    List<ComentarioSimplesDTO> findAllComentariosByTopico(int idTopico);

    @Query("""
        select new animatch.app.dto.ComentarioCompletoDTO(c.id,c.texto,c.topico,c.comentarioPai)
        from Comentario c
        where c.id = :idComentario
    """)
    ComentarioCompletoDTO findByComentarioCompletoId(int idComentario);
}
