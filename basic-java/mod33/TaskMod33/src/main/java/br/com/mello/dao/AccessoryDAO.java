package br.com.mello.dao;

import br.com.mello.domain.Accessory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AccessoryDAO   {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persiste um novo acessório no banco de dados
     *
     * @param accessory O acessório a ser salvo
     * @return O acessório persistido com ID gerado
     */
    @Transactional
    public Accessory save(Accessory accessory) {
        entityManager.persist(accessory);
        return accessory;
    }

    /**
     * Atualiza um acessório existente no banco de dados
     *
     * @param accessory O acessório com as alterações a serem atualizadas
     * @return O acessório atualizado
     */
    @Transactional
    public Accessory update(Accessory accessory) {
        return entityManager.merge(accessory);
    }

    /**
     * Remove um acessório do banco de dados pelo seu ID
     *
     * @param id O ID do acessório a ser removido
     */
    @Transactional
    public void delete(Long id) {
        Accessory accessory = findById(id).orElse(null);
        if (accessory != null) {
            entityManager.remove(accessory);
        }
    }

    /**
     * Busca um acessório pelo seu ID
     *
     * @param id O ID do acessório a ser buscado
     * @return Um Optional contendo o acessório, se encontrado
     */
    public Optional<Accessory> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Accessory.class, id));
    }

    /**
     * Lista todos os acessórios cadastrados
     *
     * @return Lista de todos os acessórios
     */
    public List<Accessory> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Accessory> query = cb.createQuery(Accessory.class);
        Root<Accessory> root = query.from(Accessory.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Busca acessórios por nome
     *
     * @param name O nome do acessório a ser buscado
     * @return Lista de acessórios com o nome especificado
     */
    public List<Accessory> findByName(String name) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE LOWER(a.name) LIKE LOWER(:name)", Accessory.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    /**
     * Busca acessórios por tipo
     *
     * @param type O tipo de acessório
     * @return Lista de acessórios do tipo especificado
     */
    public List<Accessory> findByType(Accessory.AccessoryType type) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE a.accessoryType = :type", Accessory.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    /**
     * Busca acessórios por carro
     *
     * @param carId O ID do carro
     * @return Lista de acessórios do carro especificado
     */
    public List<Accessory> findByCar(Long carId) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE a.car.id = :carId", Accessory.class);
        query.setParameter("carId", carId);
        return query.getResultList();
    }

    /**
     * Busca acessórios por faixa de preço
     *
     * @param minPrice Preço mínimo
     * @param maxPrice Preço máximo
     * @return Lista de acessórios dentro da faixa de preço
     */
    public List<Accessory> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE a.price BETWEEN :minPrice AND :maxPrice", Accessory.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

    /**
     * Busca acessórios opcionais
     *
     * @param isOptional true para buscar opcionais, false para buscar não opcionais
     * @return Lista de acessórios conforme condição de opcionalidade
     */
    public List<Accessory> findByOptional(Boolean isOptional) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE a.isOptional = :isOptional", Accessory.class);
        query.setParameter("isOptional", isOptional);
        return query.getResultList();
    }

    /**
     * Busca acessórios pelo tipo e carro
     *
     * @param type O tipo do acessório
     * @param carId O ID do carro
     * @return Lista de acessórios do tipo e carro especificados
     */
    public List<Accessory> findByTypeAndCar(Accessory.AccessoryType type, Long carId) {
        TypedQuery<Accessory> query = entityManager.createQuery(
                "SELECT a FROM Accessory a WHERE a.accessoryType = :type AND a.car.id = :carId", Accessory.class);
        query.setParameter("type", type);
        query.setParameter("carId", carId);
        return query.getResultList();
    }

    /**
     * Calcula o valor total de acessórios de um carro
     *
     * @param carId O ID do carro
     * @return O valor total dos acessórios
     */
    public BigDecimal calculateTotalAccessoryValueByCar(Long carId) {
        TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(a.price), 0) FROM Accessory a WHERE a.car.id = :carId", BigDecimal.class);
        query.setParameter("carId", carId);
        return query.getSingleResult();
    }

    /**
     * Conta o número total de acessórios cadastrados
     *
     * @return O número total de acessórios
     */
    public Long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Accessory> root = query.from(Accessory.class);
        query.select(cb.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }
}
