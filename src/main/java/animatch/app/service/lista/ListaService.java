package animatch.app.service.lista;

import animatch.app.domain.lista.Lista;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.lista.dto.ListaInfoDTO;
import animatch.app.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ListaService {
    @Autowired
    ListaRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public Lista addList(int aniUserId, String name){
        Lista newList = new Lista(name);
        newList.setUserId(usuarioRepository.findUserById(aniUserId));
        repository.save(newList);
        return newList;
    }

    public void adicionarListasDefault(int idUsuario){
        addList(idUsuario, "favorites");
        addList(idUsuario, "dropped");
        addList(idUsuario, "watched");
        addList(idUsuario, "on going");        
    }
    public List<ListaInfoDTO> listasPorUsuario(int usuarioId){
        this.verificarUsuarioExiste(usuarioId);
        return repository.findAllListaInfoByUserId(usuarioId);
    }

    public void verificarUsuarioExiste(int userId){
        if (!usuarioRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }
}
