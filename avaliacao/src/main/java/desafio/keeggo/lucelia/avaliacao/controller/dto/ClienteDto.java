package desafio.keeggo.lucelia.avaliacao.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;

public class ClienteDto {

	private String nome;
	private String cpf;	
	private String endereco;
	
	public ClienteDto(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco().getLogradouro();
	}
	
	
	public static List<ClienteDto> converter(Optional<Cliente> optional) {
		return optional.stream().map(ClienteDto::new).collect(Collectors.toList());
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
