/**
 * 
 */
package br.com.mello.dao;

import br.com.mello.dao.generic.IGenericDAO;
import br.com.mello.domain.Venda;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author victor.mello
 *
 */
public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
