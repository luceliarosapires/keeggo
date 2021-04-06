package desafio.keeggo.lucelia.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.keeggo.lucelia.avaliacao.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
}
