/**
 * 
 */
package br.com.mello;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.mello.dao.ClienteDaoMock;
import br.com.mello.dao.IClienteDAO;
import br.com.mello.domain.Cliente;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;
import br.com.mello.services.ClienteService;
import br.com.mello.services.IClienteService;

/**
 * @author victor.mello
 *
 */
public class ClienteServiceTest {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Victor");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		
	}
	
	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Victor Mello");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Victor Mello", cliente.getNome());
	}
}
