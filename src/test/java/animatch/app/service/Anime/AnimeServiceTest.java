//package animatch.app.service.Anime;
//
//import animatch.app.domain.anime.Anime;
//import animatch.app.domain.anime.repository.AnimeRepository;
//import animatch.app.domain.animelista.repository.AnimeListaRepository;
//import animatch.app.service.Anime.dto.AnimeParaSalvarDto;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class AnimeServiceTest {
//    @InjectMocks
//    private AnimeService animeService;
//
//    @Mock
//    private AnimeRepository animeRepository;
//    @Mock
//    private AnimeListaRepository animeListaRepository;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Test
//    void darLike() {
//
//    }
//
//    @Test
//    void buscarAnime() {
//    }
//
//    @Test
//    void construirAnime() {
//    }
//
//    //    @Test
////    void salvarAnime() {
////
//////        Anime animeToSave = new Anime();
//////        when(animeRepository.save(animeToSave)).thenReturn(animeToSave);
//////        animeService.salvarAnime(1);
////    }
//    @Test
//    void salvarAnimeTest() {
//        int idApi = 123;
//        Anime animeToSave = new Anime();
//        AnimeParaSalvarDto animeParaSalvarDto = new AnimeParaSalvarDto();
//        animeParaSalvarDto.setIdApi(idApi);
//        animeToSave.setIdApi(idApi);
//
//
//        // Mockando o comportamento do RestTemplate para simular a resposta do serviço externo
//
//
//        // Mockando o comportamento do construirAnime
////        when(animeService.construirAnime(idApi)).thenReturn(animeToSave);
//        when(animeService.buscarAnime(idApi)).thenReturn(animeParaSalvarDto);
//        when(animeRepository.save(animeToSave)).thenReturn(animeToSave);
//        // Testando
//        animeService.salvarAnime(idApi);
//
//        // Verificando se o anime foi salvo
//        Mockito.verify(animeRepository, Mockito.times(1)).save(animeToSave);
//    }
//
//
//    @Test
//    void dadosComplementares() {
//    }
//
//    @Test
//    void ordenarPelaNota() {
//    }
//
//    @Test
//    void deleteAnime() {
//    }
//}