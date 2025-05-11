package br.com.mello.dao;

import br.com.mello.domain.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class BrandDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persiste uma nova marca no banco de dados
     *
     * @param brand A marca a ser salva
     * @return A marca persistida com ID gerado
     */
    @Transactional
    public Brand save(Brand brand) {
        entityManager.persist(brand);
        return brand;
    }

    /**
     * Atualiza uma marca existente no banco de dados
     *
     * @param brand A marca com as alterações a serem atualizadas
     * @return A marca atualizada
     */
    @Transactional
    public Brand update(Brand brand) {
        return entityManager.merge(brand);
    }

    /**
     * Remove uma marca do banco de dados pelo seu ID
     *
     * @param id O ID da marca a ser removida
     */
    @Transactional
    public void delete(Long id) {
        Brand brand = findById(id).orElse(null);
        if (brand != null) {
            entityManager.remove(brand);
        }
    }

    /**
     * Busca uma marca pelo seu ID
     *
     * @param id O ID da marca a ser buscada
     * @return Um Optional contendo a marca, se encontrada
     */
    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Brand.class, id));
    }

    /**
     * Lista todas as marcas cadastradas
     *
     * @return Lista de todas as marcas
     */
    public List<Brand> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> query = cb.createQuery(Brand.class);
        Root<Brand> root = query.from(Brand.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Busca uma marca pelo seu nome
     *
     * @param name O nome da marca a ser buscada
     * @return Um Optional contendo a marca, se encontrada
     */
    public Optional<Brand> findByName(String name) {
        try {
            TypedQuery<Brand> query = entityManager.createQuery(
                    "SELECT b FROM Brand b WHERE LOWER(b.name) = LOWER(:name)", Brand.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException  e) {
            return Optional.empty();
        }
    }

    /**
     * Busca marcas por país de origem
     *
     * @param country O país de origem das marcas
     * @return Lista de marcas do país especificado
     */
    public List<Brand> findByCountry(String country) {
        TypedQuery<Brand> query = entityManager.createQuery(
                "SELECT b FROM Brand b WHERE LOWER(b.countryOrigin) = LOWER(:country)", Brand.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    /**
     * Conta o número total de marcas cadastradas
     *
     * @return O número total de marcas
     */
    public Long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Brand> root = query.from(Brand.class);
        query.select(cb.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

    /**
     * Verifica se uma marca com o nome especificado já existe
     *
     * @param name O nome da marca a verificar
     * @return true se a marca já existe, false caso contrário
     */
    public boolean existsByName(String name) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM Brand b WHERE LOWER(b.name) = LOWER(:name)", Long.class);
        query.setParameter("name", name);
        return query.getSingleResult() > 0;
    }
}
