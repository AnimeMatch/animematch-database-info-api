package animatch.app.api.controller;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.lista.dto.ListaInfoDTO;
import animatch.app.service.lista.ListaService;
import animatch.app.service.lista.dto.UserViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lists")
public class ListaController {

    @Autowired
    private ListaRepository listRepository;
    @Autowired
    private ListaService service;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnimeListaRepository animeListaRepository;

    @GetMapping("/")
    public ResponseEntity<List<ListaInfoDTO>> getLists(){
        List<ListaInfoDTO> listas = listRepository.findAllInfo();
        if (listas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listas);
    }

    @GetMapping("/listas-usuario")
    public ResponseEntity<List<ListaInfoDTO>> getListsByUserId(@RequestParam String email){
        List<ListaInfoDTO> listas = service.listasPorUsuario(email);
        return listas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(listas);
    }

    @GetMapping("/favorito")
    public ResponseEntity<ListaInfoDTO> getListFavoritos(@RequestParam String email){
        ListaInfoDTO listas = service.listaFavorito(email);
        return ResponseEntity.status(200).body(listas);
    }

    @PostMapping("/new")
    public  ResponseEntity newList(@RequestBody @Valid Lista listReceived){
        service.adicionarObjLista(listReceived);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/nome")
    public ResponseEntity updateNomeLista(@RequestParam int listaId, @RequestParam String nomeLista){
        service.updateNomeLista(listaId, nomeLista);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/descricao")
    public ResponseEntity updateDescricaoLista(@RequestParam int listaId, @RequestParam String descricaoLista){
        service.updateDescricaoLista(listaId, descricaoLista);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteList(@RequestParam int listaId){
        if (listRepository.existsById(listaId)){
            animeListaRepository.deleteAllByListaId(listaId);
            listRepository.deleteById(listaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}