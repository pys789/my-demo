package cn.pys.annotation;

import cn.pys.config.AutoSelectorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({AutoSelectorConfig.class})
public @interface AutoSelectorConfigAnno {

    boolean isLinux() default false;
}
