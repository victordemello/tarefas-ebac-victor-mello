/**
 * 
 */
package br.com.mello.dao.jpa;

import br.com.mello.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.mello.domain.jpa.ProdutoJpa;

/**
 * @author victor.mello
 *
 */
public class ProdutoJpaDAO extends GenericJpaDB1DAO<ProdutoJpa, Long> implements IProdutoJpaDAO {

	public ProdutoJpaDAO() {
		super(ProdutoJpa.class);
	}

}
