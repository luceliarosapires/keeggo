package desafio.keeggo.lucelia.avaliacao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import desafio.keeggo.lucelia.avaliacao.modelo.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
public  class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void findClientByCpf() {
		String cpf = "11111111111";
		Cliente cliente = clienteRepository.findByCpf(cpf).get();
		Assert.assertNotNull(cliente);
		Assert.assertEquals(cpf, cliente.getCpf());
	}

	@Test
	public void notFoundClientByCpf() {
		String cpf = "33333333333";
		Cliente cliente = clienteRepository.findByCpf(cpf).get();
		Assert.assertNull(cliente);
	}
}
