package animatch.app.service.topico;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.TopicoAdicionalListaComentariosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity<Topico> criarTopico(Topico topico) {
        topicoRepository.save(topico);
        return ResponseEntity.status(201).build();
    }


    public ResponseEntity<List<Topico>> getListaTopicos() {
        var topicos = topicoRepository.findAll();

        return topicos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicos);
    }


    public ResponseEntity<TopicoAdicionalListaComentariosDto> getTopico(int idTopico) {
        TopicoAdicionalListaComentariosDto topico = new TopicoAdicionalListaComentariosDto();
        if (topicoRepository.existsById(idTopico)) {
            var topicoSimples = topicoRepository.findTopicoById(idTopico);
            var comentariosDtos = comentarioRepository.findAllComentariosByTopico(idTopico);
            var comentarios = comentarioRepository.findByTopicoId(idTopico);
            var cont = 0;
            for (Comentario comentario : comentarios
            ) {
                var userComentario = usuarioRepository.findUserByEmailDtoSimples(comentario.getEmailUsuario());
                comentariosDtos.get(cont++).setUsuarioSimplesDto(userComentario);
            }
            var usuario = usuarioRepository.findUserByIdDtoSimples(topicoSimples.getUsuario().getId());
            topico.setId(topicoSimples.getId());
            topico.setTitulo(topicoSimples.getTitulo());
            topico.setComentarios(comentariosDtos);
            topico.setUsuarioSimplesDto(usuario);
        } else {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(topico);
    }
}
