package animatch.app.api.controller;

import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico){
        topicoRepository.save(topico);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<Topico> getTopico(@PathVariable int idTopico){
        Topico topico = new Topico();
        if (topicoRepository.existsById(idTopico)) {
            topico = topicoRepository.findTopicoById(idTopico);
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(topico);
    }
}
