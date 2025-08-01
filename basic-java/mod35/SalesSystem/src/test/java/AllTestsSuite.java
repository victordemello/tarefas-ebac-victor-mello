import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProductDAOTest.class,
        CustomerDAOTest.class,
        ProductServiceTest.class,
        CustomerServiceTest.class,
        StockServiceTest.class,
})
public class AllTestsSuite {
}
