package animatch.app.api.controller;

import animatch.app.domain.topico.Topico;
import animatch.app.dto.TopicoAdicionalListaComentariosDto;
import animatch.app.service.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        return topicoService.criarTopico(topico);
    }

    @GetMapping()
    public ResponseEntity<List<Topico>> getListaTopicos() {
        return topicoService.getListaTopicos();
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<TopicoAdicionalListaComentariosDto> getTopico(@PathVariable int idTopico) {
        return topicoService.getTopico(idTopico);
    }
}
