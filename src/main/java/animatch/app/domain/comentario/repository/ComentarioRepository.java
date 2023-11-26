package animatch.app.domain.comentario.repository;

import animatch.app.domain.comentario.Comentario;
import animatch.app.dto.ComentarioCompletoDTO;
import animatch.app.dto.ComentarioSimplesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Comentario findComentarioById(int id);

    List<Comentario> findByComentarioPaiId(int idComentario);

    @Query("""
        select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto)
        from Comentario c
        where c.comentarioPai.id = :idComentario
    """)
    List<ComentarioSimplesDTO> findBySimplesComentarioPaiId(int idComentario);

    @Query("""
        select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto,c.dataComentario)
        from Comentario c
        where c.topico.id = :idTopico and c.comentarioPai = null
    """)
    List<ComentarioSimplesDTO> findAllComentariosByTopico(int idTopico);

    @Query("""
        select c
        from Comentario c
        where c.topico.id = :idTopico and c.comentarioPai = null
    """)
    List<Comentario> findByTopicoIdAComentarioPai(int idTopico);
    List<Comentario> findByTopicoId(int idComentario);
    List<Comentario> findByIdAnimeApiAndComentarioPai(int idComentario,Comentario comentario);
    @Query("""
        select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto,c.dataComentario)
        from Comentario c
        where c.idAnimeApi = :idTopico and c.comentarioPai = null
    """)
    List<ComentarioSimplesDTO> findAllComentariosByIdAnimeApi(int idTopico);

    @Query("""
        select new animatch.app.dto.ComentarioCompletoDTO(c.id,c.texto,c.topico,c.comentarioPai)
        from Comentario c
        where c.id = :idComentario
    """)
    ComentarioCompletoDTO findByComentarioCompletoId(int idComentario);
}
