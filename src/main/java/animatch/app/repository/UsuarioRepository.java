package animatch.app.repository;

import animatch.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    public boolean existsByEmail(String email);
    public boolean existsByPassword(String password);

    Usuario findUserById(int id);
}
