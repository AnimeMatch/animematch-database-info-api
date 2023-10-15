package animatch.app.controller;

import animatch.app.domain.Usuario;
import animatch.app.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    ListaController listController;

    public Usuario getUserById(int userId){
        return repository.findUserById(userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll()
    {
        List<Usuario> users = repository.findAll();
//        System.out.println(users);
        return users.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(users);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> register(@RequestBody @Valid Usuario u)
    {
        if(repository.existsByEmail(u.getEmail())){
            return ResponseEntity.status(409).build();
        }
        repository.save(u);
        listController.defaultList(u.getId());
        return ResponseEntity.status(201).body(u);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody @Valid Usuario u )
    {
        if (repository.existsByEmail(u.getEmail()) && repository.existsByPassword(u.getPassword())){
            return ResponseEntity.status(200).body(u);
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("/")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario user){
        if (repository.existsById(user.getId())) {
            repository.save(user);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUsuario(@PathVariable int userId){
        if (repository.existsById(userId)){
            Usuario user = this.getUserById(userId);
            user.setStatus(false);
            repository.save(user);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
