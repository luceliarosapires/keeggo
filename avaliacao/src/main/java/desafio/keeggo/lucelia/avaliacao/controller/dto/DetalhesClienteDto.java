package desafio.keeggo.lucelia.avaliacao.controller.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;

public class DetalhesClienteDto {

	private String nome;

	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private String cpf;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	
	public DetalhesClienteDto(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.logradouro = cliente.getEndereco().getLogradouro();
		this.bairro = cliente.getEndereco().getBairro();
		this.cidade = cliente.getEndereco().getCidade();
		this.uf = cliente.getEndereco().getUf();
	}

	

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getLogradouro() {
		return logradouro;
	}



	public String getBairro() {
		return bairro;
	}



	public String getCidade() {
		return cidade;
	}



	public String getUf() {
		return uf;
	}

}
