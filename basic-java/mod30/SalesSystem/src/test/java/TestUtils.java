import java.util.UUID;

public class TestUtils {
    public static String uniqueCode(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }
}
