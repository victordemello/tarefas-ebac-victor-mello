/**
 * 
 */
package br.com.mello.dao.jpa;

import br.com.mello.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.mello.domain.jpa.ClienteJpa;

/**
 * @author victor.mello
 *
 */
public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}

}
