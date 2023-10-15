package animatch.app.repository;

import animatch.app.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    public boolean existsByEmail(String email);
<<<<<<< HEAD
//
=======
>>>>>>> ec04d49070d6cc0e72b1c8716d4ef3b961c8a88f
    public boolean existsByPassword(String password);

    @Query("""
            select u
            from Usuario u
            where u.email = :email and u.password = :password and u.status = true
            """)
    Usuario findUserByEmailPasword(String email, String password);

    Usuario findUserById(int id);
}
