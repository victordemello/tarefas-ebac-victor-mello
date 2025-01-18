package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victor.mello
 *
 */
public class Stock {
    private int id;
    private List<Product> productList;

    public Stock(){

    }

    public Stock(int id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    /**
     * Description: Método para adicionar um produto ao estoque
     * @param product
     */
    public void addProduct(Product product){
        this.productList.add(product);
    }

    public void removeProduct(Product product){
        this.productList.remove(product);
    }

    public void showStock(){
        System.out.println("===============================");
        System.out.println("Lista de produtos em estoque");
        System.out.println("===============================");

        for(Product product: this.productList){
            System.out.println(" ");
            System.out.println("------------------------");
            System.out.println(product.toString());
            System.out.println("------------------------");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
