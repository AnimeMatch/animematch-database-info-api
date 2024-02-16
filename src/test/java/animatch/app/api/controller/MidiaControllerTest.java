package animatch.app.api.controller;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midia.repository.MidiaRepository;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.service.Midia.MidiaService;
import animatch.app.service.Midia.dto.MidiaDadosComplementaresDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MidiaControllerTest {
    @Mock
    private MidiaRepository midiaRepository;

    @Mock
    private MidiaService midiaService;

    @Mock
    private TopicoRepository topicoRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private MidiaController midiaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstance_ReturnsEmptyList_Returns204() {
        when(midiaRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Midia>> responseEntity = midiaController.getInstance();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }


    @Test
    void getInstance() {
        List<Midia> midiaList = Arrays.asList(new Midia(), new Midia());
        when(midiaRepository.findAll()).thenReturn(midiaList);

        ResponseEntity<List<Midia>> responseEntity = midiaController.getInstance();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(midiaList, responseEntity.getBody());
    }

    @Test
    void getOrdenadosPelaNota_ReturnsEmptyList_Returns204() {
        when(midiaService.ordenarPelaNota()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Midia>> responseEntity = midiaController.getOrdenadosPelaNota();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getOrdenadosPelaNota_ReturnsNonEmptyList_Returns200() {
        List<Midia> midiaList = Arrays.asList(new Midia(), new Midia());
        when(midiaService.ordenarPelaNota()).thenReturn(midiaList);

        ResponseEntity<List<Midia>> responseEntity = midiaController.getOrdenadosPelaNota();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(midiaList, responseEntity.getBody());
    }

    @Test
    void getMaisLikes_ReturnsEmptyList_Returns204() {
        when(midiaRepository.findAllByOrderByLikesDesc()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Midia>> responseEntity = midiaController.getMaisLikes();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getMaisLikes_ReturnsNonEmptyList_Returns200() {
        List<Midia> midiaList = Arrays.asList(new Midia(), new Midia());
        when(midiaRepository.findAllByOrderByLikesDesc()).thenReturn(midiaList);

        ResponseEntity<List<Midia>> responseEntity = midiaController.getMaisLikes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(midiaList, responseEntity.getBody());
    }

    @Test
    void dadosComplementares_ReturnsDto_Returns200() {
        int midiaId = 1;
        MidiaDadosComplementaresDto dto = new MidiaDadosComplementaresDto();
        when(midiaService.dadosComplementares(midiaId)).thenReturn(dto);

        ResponseEntity<MidiaDadosComplementaresDto> responseEntity = midiaController.dadosComplementares(midiaId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    void postMidias_Success_Returns200() {
        int idApi = 1;

        ResponseEntity responseEntity = midiaController.postMidias(idApi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(midiaService, times(1)).salvarMidia(idApi);
    }

    @Test
    void darLike_Success_Returns200() {
        int idApi = 1;
        when(midiaService.darLike(idApi)).thenReturn(HttpStatus.OK);

        ResponseEntity responseEntity = midiaController.darLike(idApi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(midiaService, times(1)).darLike(idApi);
    }

    @Test
    void deleteMidia_Exists_Returns200() {
        int midiaId = 1;
        when(midiaRepository.existsById(midiaId)).thenReturn(true);

        ResponseEntity responseEntity = midiaController.deleteMidia(midiaId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(midiaService, times(1)).deleteMidia(midiaId);
    }

    @Test
    void deleteMidia_NotExists_Returns404() {
        int midiaId = 1;
        when(midiaRepository.existsById(midiaId)).thenReturn(false);

        ResponseEntity responseEntity = midiaController.deleteMidia(midiaId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(midiaService, never()).deleteMidia(midiaId);
    }

    @Test
    void getComentarios_Exists_Returns200() {
        int midiaId = 1;
        when(midiaRepository.existsByIdApi(midiaId)).thenReturn(true);

        List<ComentarioSimplesDTO> comentariosDtos = Arrays.asList(new ComentarioSimplesDTO(), new ComentarioSimplesDTO());
        when(comentarioRepository.findAllComentariosByIdMidiaApi(midiaId)).thenReturn(comentariosDtos);

        ResponseEntity<List<ComentarioSimplesDTO>> responseEntity = midiaController.getComentarios(midiaId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comentariosDtos, responseEntity.getBody());
    }

    @Test
    void getComentarios_NotExists_Returns404() {
        int midiaId = 1;
        when(midiaRepository.existsByIdApi(midiaId)).thenReturn(false);

        ResponseEntity<List<ComentarioSimplesDTO>> responseEntity = midiaController.getComentarios(midiaId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(comentarioRepository, never()).findAllComentariosByIdMidiaApi(midiaId);
    }

    @Test
    void getOrdenadosPelaNota() {
    }

    @Test
    void getMaisLikes() {
    }

    @Test
    void dadosComplementares() {
    }

    @Test
    void postMidias() {
    }

    @Test
    void darLike() {
    }

    @Test
    void deleteMidia() {
    }

    @Test
    void getComentarios() {
    }
}