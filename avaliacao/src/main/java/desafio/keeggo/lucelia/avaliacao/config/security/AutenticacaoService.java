package desafio.keeggo.lucelia.avaliacao.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import desafio.keeggo.lucelia.avaliacao.modelo.Usuario;
import desafio.keeggo.lucelia.avaliacao.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> user = usuarioRepository.findByEmail(username);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}
	
	@Bean
	public TokenService tokenService() {
	    return new TokenService();
	}

}
