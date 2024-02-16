package animatch.app.service.Midia;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midia.repository.MidiaRepository;
import animatch.app.domain.midialista.repository.MidiaListaRepository;
import animatch.app.service.Midia.dto.MidiaDadosComplementaresDto;
import animatch.app.service.Midia.dto.MidiaParaSalvarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class MidiaService {
    @Autowired
    private MidiaRepository repository;
    @Autowired
    private MidiaListaRepository midiaListaRepository;
    public HttpStatus darLike(int idApi) {
        Midia midiaToChange = repository.findByIdApi(idApi);
        if (midiaToChange == null) {
            Midia midia = construirMidia(idApi);
            midia.somarLikes();
            return HttpStatus.OK;
        }
        midiaToChange.somarLikes();
        repository.save(midiaToChange);
        return HttpStatus.OK;
    }

    public MidiaParaSalvarDto buscarMidia(int idApi) {
        try {
//            System.setProperty("javax.net.debug", "ssl");
            String encodedIdApi = UriUtils.encodePath(String.valueOf(idApi), StandardCharsets.UTF_8);
            String url = "http://localhost:8081/midias/midia-para-salvar/{idApi}";
            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<MidiaParaSalvarDto> responseEntity = restTemplate.getForEntity(url, MidiaParaSalvarDto.class);
//            System.out.println("Resp %s".formatted(responseEntity));
//            return responseEntity.getBody();
            return restTemplate.getForObject(
                    url,
                    MidiaParaSalvarDto.class,
                    encodedIdApi
            );
        } catch (HttpClientErrorException e) {
            System.out.println("Erro na chamada HTTP: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public Midia construirMidia(int idApi) {
        try {
            MidiaParaSalvarDto midiaRequested = buscarMidia(idApi);
            Midia midia = new Midia(
                    midiaRequested.getIdApi(),
                    midiaRequested.getNome(),
                    midiaRequested.getNotaMedia(),
                    midiaRequested.getImagem()
            );
            return midia;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao contruir midia recebido pela API\n[Erro]: %s".formatted(e));
        }
    }

    public void salvarMidia(int idApi) {
        Midia midiaToSave = construirMidia(idApi);
        repository.save(midiaToSave);
    }

    public MidiaDadosComplementaresDto dadosComplementares(int id) {
        if (!repository.existsById(id)) {
            return new MidiaDadosComplementaresDto(0, 0, 0);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Midia de id %d não encontrado".formatted(id));
        }
        try {
            Integer like = repository.qtdLikesMidia(id);
            Integer deslike = repository.qtdDeslikesMidia(id);
            Integer assistido = repository.qtdAssistido(id);
            return new MidiaDadosComplementaresDto(
                    like,
                    deslike,
                    assistido);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ("houve um erro ao carregar dados de likes," +
                    " deslike e vezes assistido para o midia de id %d").formatted(id));
        }
    }

    public List<Midia> ordenarPelaNota() {
        List<Midia> midias = repository.findAllByOrderByNotaMediaDesc();
//        for (int i = 0; i < midias.size() - 1; i++) {
//            for (int j = 0; j < midias.size() - 1; j++) {
//                if (midias.get(j).getNotaMedia() > midias.get(j+1).getNotaMedia()) {
//                    var aux = midias.get(j);
//                    midias.set(j,midias.get(j+1));
//                    midias.set(j+1,aux);
//                }
//            }
//        }
        return midias;
    }

    public void deleteMidia(int midiaId) {
        if (repository.existsById(midiaId)) {
            try {
                repository.deleteById(midiaId);
            } catch (DataIntegrityViolationException e) {
                try {
                    midiaListaRepository.deleteAllByMidiaId(midiaId);
                    repository.deleteById(midiaId);
                } catch (Exception exception) {
                    throw exception;
                }
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "%s".formatted(e));
            } catch (Exception e) {
                throw e;
            }
//            return ResponseEntity.status(200).build();
        }
    }
}
