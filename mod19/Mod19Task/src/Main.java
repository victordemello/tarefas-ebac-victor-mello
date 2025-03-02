import annotation.Table;
import entities.Customer;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer();

        Class<Customer> customerClass = Customer.class;

        Table customerAnnotation = customerClass.getAnnotation(Table.class);

        System.out.println("Object: " + customer);
        System.out.println("Table annotation: " + customerAnnotation);
    }
}