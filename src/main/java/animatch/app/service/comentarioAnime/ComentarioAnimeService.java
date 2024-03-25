package animatch.app.service.comentarioAnime;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.comentarioAnime.ComentarioAnime;
import animatch.app.domain.comentarioAnime.repository.ComentarioAnimeRepository;
import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioAnimeService {
    @Autowired
    private ComentarioAnimeRepository comentarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity<ComentarioAnime> criarComentario(int idMidiaApi, ComentarioAnime comentario) {
        comentario.setIdMidiaApi(idMidiaApi);
        comentarioRepository.save(comentario);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<ComentarioAnime> criarComentarioFilho(int idComentarioPai, ComentarioAnime comentario) {
        ComentarioAnime comentarioPai = new ComentarioAnime();
        if (comentarioRepository.existsById(idComentarioPai)) {
            comentarioPai = comentarioRepository.findComentarioAnimeById(idComentarioPai);
        } else {
            return ResponseEntity.status(404).build();
        }
        comentario.setComentarioPai(comentarioPai);
        comentario.setIdMidiaApi(comentarioPai.getIdMidiaApi());
        comentarioRepository.save(comentario);

        return ResponseEntity.status(201).build();
    }


    public ResponseEntity darLike(int idComentario) {
        if (comentarioRepository.existsById(idComentario)) {
            var comentario = comentarioRepository.findComentarioAnimeById(idComentario);
            var like = comentario.getQtdLikes();
            like++;
            comentario.setQtdLikes(like);
            comentarioRepository.save(comentario);
        } else {
            ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }


    public ResponseEntity darDeslike(int idComentario) {
        if (comentarioRepository.existsById(idComentario)) {
            var comentario = comentarioRepository.findComentarioAnimeById(idComentario);
            var deslike = comentario.getQtdDeslikes();
            deslike++;
            comentario.setQtdDeslikes(deslike);
            comentarioRepository.save(comentario);
        } else {
            ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }


    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosAnime(int idMidiaApi) {
        List<ComentarioSimplesDTO> comentariosDtos;

        comentariosDtos = comentarioRepository.findAllComentariosByIdMidiaApi(idMidiaApi);
        var comentarios = comentarioRepository.findComentarioAnimeByIdMidiaApi(idMidiaApi);
        var cont = 0;
        for (ComentarioAnime comentario : comentarios
        ) {
            var userComentario = usuarioRepository.findUserByEmailDtoSimples(comentario.getEmailUsuario());
            var qtdComentariosFilhos = comentarioRepository.countByComentarioAnimePaiId(comentario.getId());
            comentariosDtos.get(cont).setUsuarioSimplesDto(userComentario);
            comentariosDtos.get(cont).setQtdComentariosFilhos(qtdComentariosFilhos);
            cont++;
        }

        return comentariosDtos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(comentariosDtos);
    }

    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosFilhos(int idComentario) {
        List<ComentarioSimplesDTO> comentariosDtos;

        comentariosDtos = comentarioRepository.findBySimplesComentarioPaiId(idComentario);
        var comentarios = comentarioRepository.findByComentarioAnimePaiId(idComentario);
        var cont = 0;
        for (ComentarioAnime comentario : comentarios
        ) {
            var userComentario = usuarioRepository.findUserByEmailDtoSimples(comentario.getEmailUsuario());
            var qtdComentariosFilhos = comentarioRepository.countByComentarioAnimePaiId(comentario.getId());
            comentariosDtos.get(cont).setUsuarioSimplesDto(userComentario);
            comentariosDtos.get(cont).setQtdComentariosFilhos(qtdComentariosFilhos);
            cont++;
        }

        return comentariosDtos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(comentariosDtos);
    }


    public ResponseEntity<ComentarioAnime> getComentario(int idComentario) {
        ComentarioAnime comentario;
        if (comentarioRepository.existsById(idComentario)) {
            comentario = comentarioRepository.findComentarioAnimeById(idComentario);
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(comentario);
    }


    public ResponseEntity<ComentarioAnime> atualizarTextoComentario(int idComentario, ComentarioAnime comentario) {
        ComentarioAnime comentarioExistente;
        if (comentarioRepository.existsById(idComentario)) {
            comentarioExistente = comentarioRepository.findComentarioAnimeById(idComentario);
        } else {
            return ResponseEntity.status(404).build();
        }
        comentarioExistente.setTexto(comentario.getTexto());
        comentarioRepository.save(comentarioExistente);
        return ResponseEntity.status(200).build();
    }


    public ResponseEntity<ComentarioAnime> deletarComentario(int idComentario) {
        List<ComentarioAnime> comentarios;
        if (comentarioRepository.existsById(idComentario)) {
            comentarios = comentarioRepository.findByComentarioAnimePaiId(idComentario);
            if (comentarios.isEmpty()) {
                comentarioRepository.deleteById(idComentario);
            } else {
                comentarioRepository.deleteAll(comentarios);
                comentarioRepository.deleteById(idComentario);
            }
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }
}
