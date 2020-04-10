package cn.azzhu.myo2o.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
    String[] value() default {};

    String[] menus() default {};

    String[] roles() default {};
}