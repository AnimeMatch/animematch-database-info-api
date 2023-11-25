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
        UsuarioCadastrarDTO usuarioCadastrarDTO = new UsuarioCadastrarDTO();
        usuarioCadastrarDTO.setEmail("test@example.com");
        usuarioCadastrarDTO.setPassword("password");

        when(repository.existsByEmail(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");

        ResponseEntity<Usuario> responseEntity = service.criar(usuarioCadastrarDTO);

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

        when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        ResponseEntity<Usuario> responseEntity = service.criar(usuarioCadastrarDTO);

        assertEquals(409, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

    @Test
    void autentincar(){

    }

}