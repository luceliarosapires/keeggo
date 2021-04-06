package desafio.keeggo.lucelia.avaliacao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Cliente {


	private String nome;

	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private String cpf;
	
	@ManyToOne
	private Endereco endereco;
	
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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	private Cliente() {}
	
	public Cliente(String nome, String cpf, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
}
