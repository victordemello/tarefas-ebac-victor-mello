/**
 * 
 */
package br.com.mello.dao;

import br.com.mello.dao.generic.GenericDAO;
import br.com.mello.domain.Venda;
import br.com.mello.domain.Venda.Status;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author victor.mello
 *
 */
public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {

	@Override
	public Class<Venda> getTipoClasse() {
		return Venda.class;
	}

	@Override
	public void atualiarDados(Venda entity, Venda entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setStatus(entity.getStatus());
	}

	@Override
	public void excluir(String valor) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
		venda.setStatus(Status.CONCLUIDA);
		super.alterar(venda);
	}
	
	

}
