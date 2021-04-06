package desafio.keeggo.lucelia.avaliacao.controller;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.keeggo.lucelia.avaliacao.config.security.TokenService;
import desafio.keeggo.lucelia.avaliacao.controller.dto.TokenDto;
import desafio.keeggo.lucelia.avaliacao.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value= { "prod", "test" })
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm loginFrm) throws AuthenticationException{
		
		UsernamePasswordAuthenticationToken userName = loginFrm.converter(loginFrm);
		
		try {
			Authentication auth  = authManager.authenticate(userName);
			String token = tokenService.gerarToken(auth);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
