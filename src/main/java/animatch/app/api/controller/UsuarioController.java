package animatch.app.api.controller;

import animatch.app.api.controller.ListaController;
import animatch.app.dto.UsuarioCsvDTO;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.service.usuario.dto.UsuarioAtualizarDto;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.utils.GerenciadorDeArquivo;
import animatch.app.utils.ListaObj;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Operation(summary = "Recebe todos os usuários cadastrados")
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> users = repository.findAll();
        return users.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(users);
    }

    @Operation(summary = "Busca um usuário pelo id")
    @GetMapping("/user")
    public ResponseEntity<Usuario> fingUserById(@RequestParam int id){
        Usuario user = repository.findUserById(id);
        return user != null ? ResponseEntity.status(200).body(user) : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Informações em arquivo .csv")
    @GetMapping("/info")
    public ResponseEntity enviarCsv(){
        List<Usuario> users = repository.findAll();
        List<Integer> qtds = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Integer quantidade = repository.countQuantiadeListas(users.get(i).getId());
            qtds.add(quantidade);
        }
        ListaObj<Usuario> listaObj= new ListaObj<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            listaObj.adiciona(users.get(i));
        }
        GerenciadorDeArquivo.gravaArquivoCsv(listaObj, "arquivoDeUsuarios", qtds);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Leitura de arquivos .csv")
    @GetMapping("/lerArquivoCsv")
    public ResponseEntity lerCsv(){
        GerenciadorDeArquivo.leArquivoCsv("arquivoDeUsuarios");

        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Cadastro de novos usuários")
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLoginDTO u){
        ResponseEntity resposta = usuarioService.autenticar(u);
        return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
    }

    @PutMapping("/")
    public ResponseEntity updateUsuario(@RequestBody UsuarioAtualizarDto user){
//        if (repository.existsById(user.getId())) {
//            repository.save(user);
//            return ResponseEntity.status(200).build();
//        }
        ResponseEntity response = usuarioService.atualizar(user);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
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
