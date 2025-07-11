/**
 * 
 */
package br.com.mello.dao.generic.jpa;

import java.io.Serializable;

import br.com.mello.domain.jpa.Persistente;

/**
 * @author victor.mello
 *
 */
public abstract class GenericJpaDB2DAO <T extends Persistente, E extends Serializable>
	extends GenericJpaDAO<T,E> {

	public GenericJpaDB2DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre2");
	}

}
