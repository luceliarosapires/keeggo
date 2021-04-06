package desafio.keeggo.lucelia.avaliacao.controller.form;

import java.util.Optional;

import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;
import desafio.keeggo.lucelia.avaliacao.repository.ClienteRepository;

public class ClienteForm {

	private String nome;
	private String cpf;	
	private String endereco;
	
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
	
	public Cliente converter(ClienteRepository clienteRepository) {
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		return new Cliente(nome, cpf, cliente.get().getEndereco());
	}
	
}
