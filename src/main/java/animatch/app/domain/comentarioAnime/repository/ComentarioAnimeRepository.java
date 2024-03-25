package animatch.app.domain.comentarioAnime.repository;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentarioAnime.ComentarioAnime;
import animatch.app.dto.ComentarioCompletoDTO;
import animatch.app.dto.ComentarioSimplesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioAnimeRepository extends JpaRepository<ComentarioAnime, Integer> {
    ComentarioAnime findComentarioAnimeById(int id);

    List<ComentarioAnime> findByComentarioAnimePaiId(int idComentario);

    List<ComentarioAnime> findComentarioAnimeByIdMidiaApi(int idMidiaApi);

    int countByComentarioAnimePaiId(int idComentario);

    @Query("""
                select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto,c.dataComentarioAnime,c.qtdLikes,c.qtdDeslikes)
                from ComentarioAnime c
                where c.comentarioAnimePai.id = :idComentario
            """)
    List<ComentarioSimplesDTO> findBySimplesComentarioPaiId(int idComentario);

    List<ComentarioAnime> findByIdMidiaApiAndComentarioAnimePai(int idComentario, ComentarioAnime comentario);

    @Query("""
                select new animatch.app.dto.ComentarioSimplesDTO(c.id,c.texto,c.dataComentarioAnime,c.qtdLikes,c.qtdDeslikes)
                from ComentarioAnime c
                where c.idMidiaApi = :idMidiaApi and c.comentarioAnimePai = null
            """)
    List<ComentarioSimplesDTO> findAllComentariosByIdMidiaApi(int idMidiaApi);

//    @Query("""
//                select new animatch.app.dto.ComentarioCompletoDTO(c.id,c.texto,c.topico,c.comentarioPai)
//                from Comentario c
//                where c.id = :idComentario
//            """)
//    ComentarioCompletoDTO findByComentarioCompletoId(int idComentario);
}
