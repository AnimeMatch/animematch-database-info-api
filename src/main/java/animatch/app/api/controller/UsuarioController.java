package animatch.app.api.controller;

import animatch.app.api.controller.ListaController;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.utils.GerenciadorDeArquivo;
import animatch.app.utils.ListaObj;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Autowired
    private UsuarioService usuarioService;

    public Usuario getUserById(int userId){
        return repository.findUserById(userId);
    }

    public List<Usuario> getAllUsers(){
        List<Usuario> users = repository.findAll();
        return users;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll()
    {
        List<Usuario> users = repository.findAll();
//        System.out.println(users);
        return users.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(users);
    }

    @GetMapping("/info")
    public ResponseEntity enviarCsv(@RequestParam int usuarioId){
        List<Usuario> users = this.getAllUsers();
        ListaObj<Usuario> listaObj= new ListaObj<>(users.size());

        for (int i = 0; i < users.size(); i++) {
            listaObj.adiciona(users.get(i));
        }
        GerenciadorDeArquivo.gravaArquivoCsv(listaObj, "arquivoDeUsuarios");
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/")
    @SecurityRequirement(name= "Bearer")
    public ResponseEntity registrarUsuario(@RequestBody @Valid UsuarioCadastrarDTO usuarioCadastrarDTO){
        try {
            ResponseEntity resposta = this.usuarioService.criar(usuarioCadastrarDTO);
            return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

//    @PostMapping("/")
//    public ResponseEntity<Usuario> register(@RequestBody @Valid Usuario u)
//    {
//        if(repository.existsByEmail(u.getEmail())){
//            return ResponseEntity.status(409).build();
//        }
//        repository.save(u);
//        listController.defaultList(u.getId());
//        return ResponseEntity.status(201).body(u);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<UserTesteDTO> login(@RequestBody @Valid UsuarioDTO u){
//        Usuario user = repository.findUserByEmailPasword(u.getEmail(), u.getPassword());
//        if (user != null){
//            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Imx1Y2FzQGdtYWlsLmNvbSIsInNlbmhhIjoicGFzc3dvcmQifQ.BRG2BLSKPdBpAHjkCvxQVALZlNcjOmGdbj9m-gd5kH8";
//            UserTesteDTO usuario = new UserTesteDTO(token, user.getName());
//            return ResponseEntity.status(200).header("custom-header", "Access-Control-Allow-Origin").body(usuario);
//        }
//        return ResponseEntity.status(403).build();
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO u){
//        Usuario user = repository.findUserByEmailPasword(u.getEmail(), u.getPassword());
//        if (user != null){
//            return ResponseEntity.status(200).body(user);
//        }
//        return ResponseEntity.status(403).build();
//    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLoginDTO u){
        ResponseEntity resposta = usuarioService.autenticar(u);
        return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
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
