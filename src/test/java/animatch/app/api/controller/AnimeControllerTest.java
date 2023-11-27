package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.service.Anime.AnimeService;
import animatch.app.service.Anime.dto.AnimeDadosComplementaresDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AnimeControllerTest {
    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private AnimeService animeService;

    @Mock
    private TopicoRepository topicoRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AnimeController animeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstance_ReturnsEmptyList_Returns204() {
        when(animeRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Anime>> responseEntity = animeController.getInstance();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }


    @Test
    void getInstance() {
        List<Anime> animeList = Arrays.asList(new Anime(), new Anime());
        when(animeRepository.findAll()).thenReturn(animeList);

        ResponseEntity<List<Anime>> responseEntity = animeController.getInstance();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(animeList, responseEntity.getBody());
    }

    @Test
    void getOrdenadosPelaNota_ReturnsEmptyList_Returns204() {
        when(animeService.ordenarPelaNota()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Anime>> responseEntity = animeController.getOrdenadosPelaNota();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getOrdenadosPelaNota_ReturnsNonEmptyList_Returns200() {
        List<Anime> animeList = Arrays.asList(new Anime(), new Anime());
        when(animeService.ordenarPelaNota()).thenReturn(animeList);

        ResponseEntity<List<Anime>> responseEntity = animeController.getOrdenadosPelaNota();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(animeList, responseEntity.getBody());
    }

    @Test
    void getMaisLikes_ReturnsEmptyList_Returns204() {
        when(animeRepository.findAllByOrderByLikesDesc()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Anime>> responseEntity = animeController.getMaisLikes();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getMaisLikes_ReturnsNonEmptyList_Returns200() {
        List<Anime> animeList = Arrays.asList(new Anime(), new Anime());
        when(animeRepository.findAllByOrderByLikesDesc()).thenReturn(animeList);

        ResponseEntity<List<Anime>> responseEntity = animeController.getMaisLikes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(animeList, responseEntity.getBody());
    }

    @Test
    void dadosComplementares_ReturnsDto_Returns200() {
        int animeId = 1;
        AnimeDadosComplementaresDto dto = new AnimeDadosComplementaresDto();
        when(animeService.dadosComplementares(animeId)).thenReturn(dto);

        ResponseEntity<AnimeDadosComplementaresDto> responseEntity = animeController.dadosComplementares(animeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    void postAnimes_Success_Returns200() {
        int idApi = 1;

        ResponseEntity responseEntity = animeController.postAnimes(idApi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(animeService, times(1)).salvarAnime(idApi);
    }

    @Test
    void darLike_Success_Returns200() {
        int idApi = 1;
        when(animeService.darLike(idApi)).thenReturn(HttpStatus.OK);

        ResponseEntity responseEntity = animeController.darLike(idApi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(animeService, times(1)).darLike(idApi);
    }

    @Test
    void deleteAnime_Exists_Returns200() {
        int animeId = 1;
        when(animeRepository.existsById(animeId)).thenReturn(true);

        ResponseEntity responseEntity = animeController.deleteAnime(animeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(animeService, times(1)).deleteAnime(animeId);
    }

    @Test
    void deleteAnime_NotExists_Returns404() {
        int animeId = 1;
        when(animeRepository.existsById(animeId)).thenReturn(false);

        ResponseEntity responseEntity = animeController.deleteAnime(animeId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(animeService, never()).deleteAnime(animeId);
    }

    @Test
    void getComentarios_Exists_Returns200() {
        int animeId = 1;
        when(animeRepository.existsByIdApi(animeId)).thenReturn(true);

        List<ComentarioSimplesDTO> comentariosDtos = Arrays.asList(new ComentarioSimplesDTO(), new ComentarioSimplesDTO());
        when(comentarioRepository.findAllComentariosByIdAnimeApi(animeId)).thenReturn(comentariosDtos);

        ResponseEntity<List<ComentarioSimplesDTO>> responseEntity = animeController.getComentarios(animeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comentariosDtos, responseEntity.getBody());
    }

    @Test
    void getComentarios_NotExists_Returns404() {
        int animeId = 1;
        when(animeRepository.existsByIdApi(animeId)).thenReturn(false);

        ResponseEntity<List<ComentarioSimplesDTO>> responseEntity = animeController.getComentarios(animeId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(comentarioRepository, never()).findAllComentariosByIdAnimeApi(animeId);
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
    void postAnimes() {
    }

    @Test
    void darLike() {
    }

    @Test
    void deleteAnime() {
    }

    @Test
    void getComentarios() {
    }
}