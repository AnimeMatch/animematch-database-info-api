package animatch.app.api.controller;

import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.usuario.UsuarioService;
import animatch.app.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import animatch.app.service.usuario.dto.UsuarioAtualizarDto;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import animatch.app.service.usuario.dto.UsuarioLoginDTO;
import animatch.app.utils.GerenciadorDeArquivo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Recebe todos os usuários cadastrados")
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> users = repository.findAll();
        return users.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(users);
    }

    @Operation(summary = "Busca um usuário pelo email")
    @GetMapping("/user")
    public ResponseEntity<Usuario> findUserById(@RequestParam String email) {
        Usuario user = service.findUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @Operation(summary = "Informações em arquivo .csv")
    @GetMapping("/info")
    public ResponseEntity gravarCsv() {
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
    public ResponseEntity lerCsv() {
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
    public ResponseEntity registrarUsuario(@RequestBody @Valid UsuarioCadastrarDTO usuarioCadastrarDTO) {
        try {
            ResponseEntity resposta = service.criar(usuarioCadastrarDTO);
            return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
        } catch (Exception e) {
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
    public ResponseEntity login(@RequestBody UsuarioLoginDTO usuarioLogin) {
        ResponseEntity resposta = service.autenticar(usuarioLogin);
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
    public ResponseEntity updateUsuario(@RequestBody UsuarioAtualizarDto user) {
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
    @DeleteMapping("/")
    public ResponseEntity deleteUsuario(@RequestParam String email) {
        if (repository.existsByEmail(email)) {
            Usuario user = repository.findUserByEmail(email);
            user.setStatus(false);
            repository.save(user);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @Operation(summary = "Informações em arquivo .txt")
    @GetMapping("/info-txt")
    public ResponseEntity gravarTxt() {
        service.gravarTxt();
        return ResponseEntity.status(200).build();
    }
}
