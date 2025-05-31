package anotacao;

import java.lang.annotation.*;

/**
 * @author victor.mello
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {

    String value();
}
