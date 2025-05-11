package br.com.mello.dao;

import br.com.mello.domain.Car;
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

public class CarDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persiste um novo carro no banco de dados
     *
     * @param car O carro a ser salvo
     * @return O carro persistido com ID gerado
     */
    @Transactional
    public Car save(Car car) {
        entityManager.persist(car);
        return car;
    }

    /**
     * Atualiza um carro existente no banco de dados
     *
     * @param car O carro com as alterações a serem atualizadas
     * @return O carro atualizado
     */
    @Transactional
    public Car update(Car car) {
        return entityManager.merge(car);
    }

    /**
     * Remove um carro do banco de dados pelo seu ID
     *
     * @param id O ID do carro a ser removido
     */
    @Transactional
    public void delete(Long id) {
        Car car = findById(id).orElse(null);
        if (car != null) {
            entityManager.remove(car);
        }
    }

    /**
     * Busca um carro pelo seu ID
     *
     * @param id O ID do carro a ser buscado
     * @return Um Optional contendo o carro, se encontrado
     */
    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    /**
     * Lista todos os carros cadastrados
     *
     * @return Lista de todos os carros
     */
    public List<Car> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Busca carros por modelo
     *
     * @param model O modelo do carro a ser buscado
     * @return Lista de carros do modelo especificado
     */
    public List<Car> findByModel(String model) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE LOWER(c.model) LIKE LOWER(:model)", Car.class);
        query.setParameter("model", "%" + model + "%");
        return query.getResultList();
    }

    /**
     * Busca carros por marca
     *
     * @param brandId O ID da marca
     * @return Lista de carros da marca especificada
     */
    public List<Car> findByBrand(Long brandId) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE c.brand.id = :brandId", Car.class);
        query.setParameter("brandId", brandId);
        return query.getResultList();
    }

    /**
     * Busca carros por faixa de preço
     *
     * @param minPrice Preço mínimo
     * @param maxPrice Preço máximo
     * @return Lista de carros dentro da faixa de preço
     */
    public List<Car> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE c.price BETWEEN :minPrice AND :maxPrice", Car.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

    /**
     * Busca carros por tipo de combustível
     *
     * @param fuelType O tipo de combustível
     * @return Lista de carros com o tipo de combustível especificado
     */
    public List<Car> findByFuelType(Car.FuelType fuelType) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE c.fuelType = :fuelType", Car.class);
        query.setParameter("fuelType", fuelType);
        return query.getResultList();
    }

    /**
     * Busca carros por ano de fabricação
     *
     * @param year O ano de fabricação
     * @return Lista de carros fabricados no ano especificado
     */
    public List<Car> findByManufacturingYear(Integer year) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE c.manufacturingYear = :year", Car.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    /**
     * Busca carros com eager loading de acessórios
     *
     * @param id O ID do carro
     * @return Um Optional contendo o carro com seus acessórios carregados, se encontrado
     */
    public Optional<Car> findByIdWithAccessories(Long id) {
        try {
            TypedQuery<Car> query = entityManager.createQuery(
                    "SELECT c FROM Car c LEFT JOIN FETCH c.accessoryList WHERE c.id = :id", Car.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Busca carros de uma determinada marca e modelo
     *
     * @param brandName Nome da marca
     * @param modelName Nome do modelo
     * @return Lista de carros que correspondem à marca e modelo especificados
     */
    public List<Car> findByBrandNameAndModel(String brandName, String modelName) {
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c JOIN c.brand b WHERE LOWER(b.name) = LOWER(:brandName) AND LOWER(c.model) LIKE LOWER(:modelName)",
                Car.class);
        query.setParameter("brandName", brandName);
        query.setParameter("modelName", "%" + modelName + "%");
        return query.getResultList();
    }

    /**
     * Conta o número total de carros cadastrados
     *
     * @return O número total de carros
     */
    public Long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Car> root = query.from(Car.class);
        query.select(cb.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }
}
