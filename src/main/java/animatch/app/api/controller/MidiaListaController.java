package animatch.app.api.controller;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midialista.MidiaLista;
import animatch.app.domain.midialista.repository.MidiaListaRepository;
import animatch.app.service.MidiaLista.MidiaListaService;
import animatch.app.utils.FilaObj;
import animatch.app.utils.PilhaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/midia-lista")
public class MidiaListaController {
    @Autowired
    private MidiaListaRepository repository;
    @Autowired
    private MidiaListaService service;
    FilaObj<MidiaLista> filaObj = new FilaObj<>(10);
    PilhaObj<MidiaLista> pilhaObj = new PilhaObj<>(10);

//    @GetMapping("/")
//    public ResponseEntity<Midia[]> getMidias() {
//        List<MidiaInfoDTO> midias = midiaListaRepository.findAllInfo();
//        ListaObj<Midia> lista = new ListaObj<Midia>(midias.size());
//        for (int i = 0; i < midias.size(); i++) {
//            lista.adiciona(midias.get(i).getMidia());
//        }
//        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
//    }
    @GetMapping("/")
    public ResponseEntity<List<MidiaLista>> getMidias(){
    //        ListaObj<Midia> lista = service.vetorDeMidias();
        List<MidiaLista> lista = service.receberMidias();
    //        return lista.getTamanho() == 0 ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/midias-e-listas-do-usuario")
    public ResponseEntity<List<MidiaLista>> getMidiasLista(@RequestParam String email){
        List<MidiaLista> midias = service.midiaListaPorUsuario(email);
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @GetMapping("/midias-e-listas-do-usuario-paginado")
    public ResponseEntity<List<MidiaLista>> getMidiasListaPaginacao(@RequestParam String email, @RequestParam int paginacao){
        List<MidiaLista> midias = service.midiaListaPorUsuarioPaginado(email, paginacao);
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @GetMapping("/midias-da-lista")
    public ResponseEntity<List<Midia>> getMidiaLista(@RequestParam int listaId) {
        List<Midia> midias = service.receberMidiasDeUmaLista(listaId);
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }
//    public ResponseEntity<Midia[]> getMidiaLista(@RequestParam int listaId) {
//        ListaObj<Midia> midias = service.receberMidiasDeUmaLista(listaId);
//        return midias.getTamanho() == 0 ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias.getLista());
//    }

    @GetMapping("/midias-da-lista-paginado")
    public ResponseEntity<List<Midia>> getMidiaListaPaginacao(@RequestParam int listaId, @RequestParam int paginacao) {
        List<Midia> midias = service.recebreMidiasDeUmaListaPaginado(listaId, paginacao);
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }


    @GetMapping("/midias-da-lista-id-associativo")
    public ResponseEntity<List<MidiaLista>> getMidiaListaWithAssociativeId(@RequestParam int listaId) {
        List<MidiaLista> midias = service.animesWithAssociativeId(listaId);
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @PostMapping("/")
    public ResponseEntity AdicionarMidiaLista(@RequestParam int idApi, @RequestParam int idLista) {
        service.salvarMidiaLista(idApi, idLista);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/adicionar-midia-fila-espera")
    public ResponseEntity adicionarMidiaFila(@RequestBody @Valid MidiaLista midiaLista) {
        if (filaObj.isFull()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fila cheia, salve os midias na lista escolhida");
        }
        filaObj.insert(midiaLista);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/esvaziar-fila-de-espera")
    public ResponseEntity esvaziarFila() {
        var fila = filaObj;
        while (!filaObj.isEmpty()){
           var obj = repository.save(filaObj.poll());
           pilhaObj.push(obj);
        }
        return ResponseEntity.status(201).body(fila);
    }

    @GetMapping("/exibir-midia-pilha")
    public ResponseEntity<MidiaLista> exibirMidiaPilha() {
        if (pilhaObj.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        var midiaPilhaTopo = pilhaObj.pop();
        return ResponseEntity.status(200).body(midiaPilhaTopo);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteMidiaLista(@RequestParam int midiaListaId){
        if (repository.existsById(midiaListaId)) {
            repository.deleteById(midiaListaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
