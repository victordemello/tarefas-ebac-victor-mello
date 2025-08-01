import entities.Product;
import entities.ProductCategory;
import entities.Stock;

import java.util.ArrayList;

/**
 * @author victor.mello
 * Start principal da aplicação
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=========================");
        System.out.println("SISTEMA DE PRODUTOS V1");
        System.out.println("=========================");
        System.out.println(" ");

        Stock stock = new Stock(
                1,
                new ArrayList<>()
        );

        Product prd1 = new Product(
                1,
                "Laptop",
                "High-performance laptop",
                250_000, // Representado em centavos
                10,
                ProductCategory.ELECTRONICS
        );

        Product prd2 = new Product(
                2,
                "Table",
                "Wooden dining table",
                50_000, // Representado em centavos
                5,
                ProductCategory.FURNITURE
        );

        Product prd3 = new Product(
                3,
                "Apple",
                "Fresh red apples",
                300, // Representado em centavos
                50,
                ProductCategory.GROCERY
        );

        Product prd4 = new Product(
                4,
                "T-Shirt",
                "Comfortable cotton T-shirt",
                2_000, // Representado em centavos
                30,
                ProductCategory.CLOTHING
        );

        Product prd5 = new Product(
                5,
                "Lipstick",
                "Matte red lipstick",
                1_500, // Representado em centavos
                20,
                ProductCategory.BEAUTY
        );

        stock.addProduct(prd1);
        stock.addProduct(prd2);
        stock.addProduct(prd3);
        stock.addProduct(prd4);
        stock.addProduct(prd5);

        stock.showStock();
    }
}