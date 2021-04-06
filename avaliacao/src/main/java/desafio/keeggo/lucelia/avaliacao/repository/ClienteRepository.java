package desafio.keeggo.lucelia.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	Optional<Cliente> findByCpf(String cpf);
	

}
