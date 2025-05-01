import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProductDAOTest.class,
        CustomerDAOTest.class,
        StockDAOTest.class,
        SaleDAOTest.class,
        ProductServiceTest.class,
        CustomerServiceTest.class,
        StockServiceTest.class,
        SaleServiceTest.class
})
public class AllTestsSuite {
}
