/**
 * 
 */
package br.com.mello.dao;

import br.com.mello.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.mello.dao.jpa.IVendaJpaDAO;
import br.com.mello.domain.jpa.VendaJpa;
import br.com.mello.exceptions.DAOException;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author victor.mello
 *
 * Classe utilizada somente no teste para fazer a exclusão das vendas
 *
 */
public class VendaExclusaoJpaDAO extends GenericJpaDB1DAO<VendaJpa, Long> implements IVendaJpaDAO {

	public VendaExclusaoJpaDAO() {
		super(VendaJpa.class);
	}

	@Override
	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJpa consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
