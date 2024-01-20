
/*
 * Build a custom Java annotation called "Test" (similar to Junit's)
 * to mark method's used for unit / non-unit testing
 */

// Import statements
import java.lang.annotation.*;;

// Define test annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    boolean groupTest() default false;

    String testGroupName() default "";

    String testName() default "";
}