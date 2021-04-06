package desafio.keeggo.lucelia.avaliacao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.keeggo.lucelia.avaliacao.controller.dto.ClienteDto;
import desafio.keeggo.lucelia.avaliacao.controller.dto.DetalhesClienteDto;
import desafio.keeggo.lucelia.avaliacao.controller.form.AtualizarClienteForm;
import desafio.keeggo.lucelia.avaliacao.controller.form.ClienteForm;
import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;
import desafio.keeggo.lucelia.avaliacao.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	public ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ClienteDto> lista(@RequestParam String cpf) {	
		System.out.println("Lista de Clientes");
		return ((cpf != null && !cpf.isEmpty()) ? ClienteDto.converter(clienteRepository.findByCpf(cpf)) : null );
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter(clienteRepository);
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getCpf()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@GetMapping("/{cpf}")
	@Transactional
	public ResponseEntity<DetalhesClienteDto> detalhar(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new DetalhesClienteDto(cliente.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{cpf}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable String cpf, @RequestBody @Valid AtualizarClienteForm form) {
		
		Optional<Cliente> optional = clienteRepository.findByCpf(cpf);
		
		if (optional.isPresent()) {
			Cliente cliente = form.atualizar(cpf, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{cpf}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable String cpf) {
		
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente.isPresent()) {
			clienteRepository.deleteById(cpf);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
