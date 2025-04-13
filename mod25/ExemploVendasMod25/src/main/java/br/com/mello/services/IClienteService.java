/**
 * 
 */
package br.com.mello.services;

import br.com.mello.domain.Cliente;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author victor.mello
 *
 */
public interface IClienteService {

	Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;

	Cliente buscarPorCPF(Long cpf);

	void excluir(Long cpf);

	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;

}
