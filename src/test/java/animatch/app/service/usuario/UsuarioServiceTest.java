package animatch.app.service.usuario;

import animatch.app.api.configuration.security.jwt.GerenciadorTokenJwt;
import animatch.app.domain.usuario.Usuario;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.usuario.dto.UsuarioCadastrarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UsuarioService.class)
class UsuarioServiceTest {

    @Autowired
    UsuarioService service;

    @MockBean
    UsuarioRepository repository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    UsuarioCadastrarDTO usuarioCadastrarDTO;

    @MockBean
    GerenciadorTokenJwt gerenciadorTokenJwt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarNovoUsuario() {
        // Mock dados de entrada
        UsuarioCadastrarDTO usuarioCadastrarDTO = new UsuarioCadastrarDTO();
        usuarioCadastrarDTO.setEmail("test@example.com");
        usuarioCadastrarDTO.setPassword("password");

        // Configurar o comportamento do mock
        when(repository.existsByEmail(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");

        // Executar o método a ser testado
        ResponseEntity<Usuario> responseEntity = service.criar(usuarioCadastrarDTO);

        // Verificar se o usuário foi salvo corretamente
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals("test@example.com", responseEntity.getBody().getEmail());
        assertEquals("hashedPassword", responseEntity.getBody().getPassword());
    }

    @Test
    void testCriarUsuarioExistente() {
        // Mock dados de entrada
        UsuarioCadastrarDTO usuarioCadastrarDTO = new UsuarioCadastrarDTO();
        usuarioCadastrarDTO.setEmail("existingUser@example.com");
        usuarioCadastrarDTO.setPassword("password");

        // Configurar o comportamento do mock para indicar que o usuário já existe
        when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        // Executar o método a ser testado
        ResponseEntity<Usuario> responseEntity = service.criar(usuarioCadastrarDTO);

        // Verificar se o código de status 409 foi retornado, indicando conflito (usuário já existe)
        assertEquals(409, responseEntity.getStatusCodeValue());
        // Verificar se o corpo da resposta está vazio
        assertNull(responseEntity.getBody());
    }

    @Test
    void autentincar(){

    }

}