package desafio.keeggo.lucelia.avaliacao.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import desafio.keeggo.lucelia.avaliacao.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenService {

	@Value("${avaliacao.jwt.expiration}")
	private String expiration;
	
	@Value("${avaliacao.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication auth) {

		Usuario logado = (Usuario) auth.getPrincipal();
		Date date = new Date();
		Date dtExpiration = new Date(date.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API Rest alura teste")
				.setSubject(logado.getId().toString())
				.setIssuedAt(date)
				.setExpiration(dtExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
