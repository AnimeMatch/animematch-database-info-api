package animatch.app.api.controller;

import animatch.app.api.controller.ListaController;
import animatch.app.dto.UsuarioCsvDTO;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;
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


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;
    @Autowired
    private ListaController listController;

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
    public ResponseEntity gravarCsv(){
        service.gravarCsv();
        return ResponseEntity.status(200).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Leitura de arquivos .csv")
    @GetMapping("/lerArquivoCsv")
    public ResponseEntity lerCsv(){
        GerenciadorDeArquivo.leArquivoCsv("arquivoDeUsuarios");
        return ResponseEntity.status(200).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criação de usuário bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Cadastro de novos usuários")
    @PostMapping("/")
//    @SecurityRequirement(name= "Bearer")
    public ResponseEntity registrarUsuario(@RequestBody @Valid UsuarioCadastrarDTO usuarioCadastrarDTO){
        try {
            ResponseEntity resposta = service.criar(usuarioCadastrarDTO);
            return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioTokenDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLoginDTO u){
        ResponseEntity resposta = service.autenticar(u);
        return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
    }

    @PutMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização bem-sucedida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity updateUsuario(@RequestBody UsuarioAtualizarDto user){
        ResponseEntity response = service.atualizar(user);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleção bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
