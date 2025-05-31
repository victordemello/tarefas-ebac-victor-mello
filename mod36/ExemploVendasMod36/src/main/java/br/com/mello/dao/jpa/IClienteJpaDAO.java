/**
 * 
 */
package br.com.mello.dao.jpa;

import br.com.mello.dao.generic.jpa.IGenericJapDAO;
import br.com.mello.domain.jpa.Persistente;

/**
 * @author victor.mello
 *
 */
public interface IClienteJpaDAO<T extends Persistente> extends IGenericJapDAO<T, Long>{

}
