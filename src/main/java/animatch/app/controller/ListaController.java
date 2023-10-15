package animatch.app.controller;

import animatch.app.domain.Lista;
import animatch.app.dto.ListaInfoDTO;
import animatch.app.repository.AnimeListaRepository;
import animatch.app.repository.ListaRepository;
import animatch.app.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListaController {

    @Autowired
    ListaRepository listRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AnimeListaRepository animeListaRepository;

    public Lista addList(int aniUserId, String name){
        Lista newList = new Lista(name);
        newList.setUserId(usuarioRepository.findUserById(aniUserId));
        listRepository.save(newList);
        return newList;
    }
    public Lista getListsById(Integer listaId){
        return listRepository.findListaById(listaId);
    }

    @GetMapping("/")
    public ResponseEntity<List<ListaInfoDTO>> getLists(){
        List<ListaInfoDTO> listas = listRepository.findAllInfo();
        if (listas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listas);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<ListaInfoDTO>> getListsByUserId(@PathVariable Integer userId){
        List<ListaInfoDTO> listasUser = listRepository.findAllListaInfoByUserId(usuarioRepository.findUserById(userId));
        if (listasUser.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(listasUser);
    }

    @PostMapping("/")
    public ResponseEntity<List<Lista>> defaultList(int aniUserId){
        addList(aniUserId, "favorites");
        addList(aniUserId, "dropped");
        addList(aniUserId, "watched");
        addList(aniUserId, "on going");
        return ResponseEntity.status(200).body(listRepository.findAllById(Collections.singleton(aniUserId)));
    }
    @PostMapping("/new")
    public  ResponseEntity newList(@RequestBody @Valid Lista listReceived){
        var response = addList(listReceived.getUserId().getId(),listReceived.getName());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{listaId}")
    public ResponseEntity deleteList(@PathVariable int listaId){
        if (listRepository.existsById(listaId)){
            animeListaRepository.deleteAllByListaId(listaId);
            listRepository.deleteById(listaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}