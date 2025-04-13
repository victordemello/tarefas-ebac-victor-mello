package anotacao;

import java.lang.annotation.*;

/**
 * @author Victor Mello
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {

    String value();
}
