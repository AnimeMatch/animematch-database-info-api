package animatch.app.service.Anime;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.service.Anime.dto.AnimeDadosComplementaresDto;
import animatch.app.service.Anime.dto.AnimeParaSalvarDto;
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
public class AnimeService {
    @Autowired
    private AnimeRepository repository;
    @Autowired
    private AnimeListaRepository animeListaRepository;
    public HttpStatus darLike(int idApi) {
        Anime animeToChange = repository.findByIdApi(idApi);
        if (animeToChange == null) {
            Anime anime = construirAnime(idApi);
            anime.somarLikes();
            return HttpStatus.OK;
        }
        animeToChange.somarLikes();
        repository.save(animeToChange);
        return HttpStatus.OK;
    }

    public AnimeParaSalvarDto buscarAnime(int idApi) {
        try {
//            System.setProperty("javax.net.debug", "ssl");
            String encodedIdApi = UriUtils.encodePath(String.valueOf(idApi), StandardCharsets.UTF_8);
            String url = "http://localhost:8081/animes/anime-para-salvar/{idApi}";
            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<AnimeParaSalvarDto> responseEntity = restTemplate.getForEntity(url, AnimeParaSalvarDto.class);
//            System.out.println("Resp %s".formatted(responseEntity));
//            return responseEntity.getBody();
            return restTemplate.getForObject(
                    url,
                    AnimeParaSalvarDto.class,
                    encodedIdApi
            );
        } catch (HttpClientErrorException e) {
            System.out.println("Erro na chamada HTTP: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public Anime construirAnime(int idApi) {
        try {
            AnimeParaSalvarDto animeRequested = buscarAnime(idApi);
            Anime anime = new Anime(
                    animeRequested.getIdApi(),
                    animeRequested.getNome(),
                    animeRequested.getNotaMedia(),
                    animeRequested.getImagem()
            );
            return anime;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao contruir anime recebido pela API\n[Erro]: %s".formatted(e));
        }
    }

    public void salvarAnime(int idApi) {
        Anime animeToSave = construirAnime(idApi);
        repository.save(animeToSave);
    }

    public AnimeDadosComplementaresDto dadosComplementares(int id) {
        if (!repository.existsById(id)) {
            return new AnimeDadosComplementaresDto(0, 0, 0);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime de id %d não encontrado".formatted(id));
        }
        try {
            Integer like = repository.qtdLikesAnime(id);
            Integer deslike = repository.qtdDeslikesAnime(id);
            Integer assistido = repository.qtdAssistido(id);
            return new AnimeDadosComplementaresDto(
                    like,
                    deslike,
                    assistido);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ("houve um erro ao carregar dados de likes," +
                    " deslike e vezes assistido para o anime de id %d").formatted(id));
        }
    }

    public List<Anime> ordenarPelaNota() {
        List<Anime> animes = repository.findAllByOrderByNotaMediaDesc();
//        for (int i = 0; i < animes.size() - 1; i++) {
//            for (int j = 0; j < animes.size() - 1; j++) {
//                if (animes.get(j).getNotaMedia() > animes.get(j+1).getNotaMedia()) {
//                    var aux = animes.get(j);
//                    animes.set(j,animes.get(j+1));
//                    animes.set(j+1,aux);
//                }
//            }
//        }
        return animes;
    }

    public void deleteAnime(int animeId) {
        if (repository.existsById(animeId)) {
            try {
                repository.deleteById(animeId);
            } catch (DataIntegrityViolationException e) {
                try {
                    animeListaRepository.deleteAllByAnimeId(animeId);
                    repository.deleteById(animeId);
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
