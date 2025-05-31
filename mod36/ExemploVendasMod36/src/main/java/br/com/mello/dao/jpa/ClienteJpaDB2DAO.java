/**
 * 
 */
package br.com.mello.dao.jpa;

import br.com.mello.dao.generic.jpa.GenericJpaDB2DAO;
import br.com.mello.domain.jpa.ClienteJpa;

/**
 * @author victor.mello
 *
 */
public class ClienteJpaDB2DAO extends GenericJpaDB2DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

	public ClienteJpaDB2DAO() {
		super(ClienteJpa.class);
	}

}
