package entities;

import java.math.BigDecimal;

/**
 * @author victor.mello
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private ProductCategory productCategory;

    public Product(){

    }

    /**
     * Description: Construtor principal para instância de novos produtos.
     * @param id
     * @param name
     * @param description
     * @param price
     * @param quantity
     * @param productCategory
     */
    public Product(int id, String name, String description, int price, int quantity, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.productCategory = productCategory;
    }

    /** Description: Método receber como parametro o preço em centavos e converte para real. Ex: 100 centavos = R$1,00
     *
     * @param price
     * @return price in real
     */
    public BigDecimal convertToReal(int price){
        return new BigDecimal(price * 100);
    }

    @Override
    public String toString() {
        return String.format("Produto: %s\nID: %d\nDescrição: %s\nPreço: R$ %.2f\nQuantidade: %d\nCategoria: %s",
                name,
                id,
                description,
                (double) price / 100,  // Supondo que price seja em centavos
                quantity,
                (productCategory != null ? productCategory.toString() : "Não especificada"));

    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
