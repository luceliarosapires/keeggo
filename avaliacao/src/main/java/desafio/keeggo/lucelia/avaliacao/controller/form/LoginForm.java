package desafio.keeggo.lucelia.avaliacao.controller.form;

import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class LoginForm {

	private String email;
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken converter(@Valid LoginForm loginFrm) {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
	
}
