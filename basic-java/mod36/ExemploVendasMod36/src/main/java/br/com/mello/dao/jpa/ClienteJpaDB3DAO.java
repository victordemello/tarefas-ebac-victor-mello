/**
 * 
 */
package br.com.mello.dao.jpa;

import br.com.mello.dao.generic.jpa.GenericJpaDB3DAO;
import br.com.mello.domain.jpa.ClienteJpa2;

/**
 * @author victor.mello
 *
 */
public class ClienteJpaDB3DAO extends GenericJpaDB3DAO<ClienteJpa2, Long> implements IClienteJpaDAO<ClienteJpa2> {

	public ClienteJpaDB3DAO() {
		super(ClienteJpa2.class);
	}

}
