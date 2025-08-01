/**
 * 
 */
package br.com.mello.services;

import br.com.mello.dao.IProdutoDAO;
import br.com.mello.domain.Produto;
import br.com.mello.services.generic.GenericService;

/**
 * @author victor.mello
 *
 */
public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
