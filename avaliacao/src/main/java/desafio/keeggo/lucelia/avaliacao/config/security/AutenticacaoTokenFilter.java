package desafio.keeggo.lucelia.avaliacao.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import desafio.keeggo.lucelia.avaliacao.modelo.Usuario;
import desafio.keeggo.lucelia.avaliacao.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		 this.tokenService = tokenService;
		 this.usuarioRepository = usuarioRepository;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperaToken(request);
		boolean valido = tokenService.isTokenValid(token);
		if (valido) {
			autenticar(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticar(String token) {
		Long idUser = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken autenticador = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(autenticador);
	}

	private String recuperaToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return  token.substring(7, token.length());
	}
}
