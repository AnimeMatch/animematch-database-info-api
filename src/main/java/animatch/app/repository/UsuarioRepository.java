package animatch.app.repository;

import animatch.app.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    public boolean existsByEmail(String email);
    public boolean existsByPassword(String password);

    @Query("""
            select u
            from Usuario u
            where u.email = :email and u.password = :password and u.status = true
            """)
    Usuario findUserByEmailPasword(String email, String password);

    Usuario findUserById(int id);
}
