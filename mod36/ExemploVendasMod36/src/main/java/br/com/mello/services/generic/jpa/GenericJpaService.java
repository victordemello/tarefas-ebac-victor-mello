/**
 * 
 */
package br.com.mello.services.generic.jpa;

import java.io.Serializable;
import java.util.Collection;

import br.com.mello.dao.generic.jpa.IGenericJapDAO;
import br.com.mello.domain.jpa.Persistente;
import br.com.mello.exceptions.DAOException;
import br.com.mello.exceptions.MaisDeUmRegistroException;
import br.com.mello.exceptions.TableException;
import br.com.mello.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author victor.mello
 *
 */
public abstract class GenericJpaService<T extends Persistente, E extends Serializable> 
	implements IGenericJpaService<T, E> {
	
	protected IGenericJapDAO<T, E> dao;
	
	public GenericJpaService(IGenericJapDAO<T, E> dao) {
		this.dao = dao;
	}
	

	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(T entity) throws DAOException {
		this.dao.excluir(entity);
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		return this.dao.alterar(entity);
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		return this.dao.consultar(valor);
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		return this.dao.buscarTodos();
	}
	

}
