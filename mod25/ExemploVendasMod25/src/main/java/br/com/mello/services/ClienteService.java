/**
 * 
 */
package br.com.mello.services;

import br.com.mello.dao.IClienteDAO;
import br.com.mello.domain.Cliente;
import br.com.mello.services.generic.GenericService;

/**
 * @author victor.mello
 *
 */
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) {
		return this.dao.consultar(cpf);
	}

}
