import br.com.mello.dao.IProductDao;
import br.com.mello.dao.ProductDao;
import br.com.mello.domain.IProduct;
import br.com.mello.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductDaoTest {
    private IProductDao productDao;

    public ProductDaoTest() {
        productDao = new ProductDao();
    }

    @Test
    public void create() {

        IProduct product = new Product("Iphone",
                "iPhone 16 – Potência, elegância e inteligência. Com chip A18 Pro, câmeras de nível profissional, tela Super Retina XDR e design em titânio, o iPhone 16 entrega desempenho extremo e inovação em cada detalhe.",
                new BigDecimal("8499.00"));
        IProduct registeredCourse = productDao.create(product);

        Assertions.assertNotNull(registeredCourse);
        Assertions.assertNotNull(registeredCourse);
    }
}
