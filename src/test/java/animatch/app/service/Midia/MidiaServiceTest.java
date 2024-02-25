//package animatch.app.service.Midia;
//
//import animatch.app.domain.midia.Midia;
//import animatch.app.domain.midia.repository.MidiaRepository;
//import animatch.app.domain.midialista.repository.MidiaListaRepository;
//import animatch.app.service.Midia.dto.MidiaParaSalvarDto;
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
//class MidiaServiceTest {
//    @InjectMocks
//    private MidiaService midiaService;
//
//    @Mock
//    private MidiaRepository midiaRepository;
//    @Mock
//    private MidiaListaRepository midiaListaRepository;
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
//    void buscarMidia() {
//    }
//
//    @Test
//    void construirMidia() {
//    }
//
//    //    @Test
////    void salvarMidia() {
////
//////        Midia midiaToSave = new Midia();
//////        when(midiaRepository.save(midiaToSave)).thenReturn(midiaToSave);
//////        midiaService.salvarMidia(1);
////    }
//    @Test
//    void salvarMidiaTest() {
//        int idApi = 123;
//        Midia midiaToSave = new Midia();
//        MidiaParaSalvarDto midiaParaSalvarDto = new MidiaParaSalvarDto();
//        midiaParaSalvarDto.setIdApi(idApi);
//        midiaToSave.setIdApi(idApi);
//
//
//        // Mockando o comportamento do RestTemplate para simular a resposta do serviço externo
//
//
//        // Mockando o comportamento do construirMidia
////        when(midiaService.construirMidia(idApi)).thenReturn(midiaToSave);
//        when(midiaService.buscarMidia(idApi)).thenReturn(midiaParaSalvarDto);
//        when(midiaRepository.save(midiaToSave)).thenReturn(midiaToSave);
//        // Testando
//        midiaService.salvarMidia(idApi);
//
//        // Verificando se o midia foi salvo
//        Mockito.verify(midiaRepository, Mockito.times(1)).save(midiaToSave);
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
//    void deleteMidia() {
//    }
//}