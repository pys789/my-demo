package cn.pys.annotation;

import cn.pys.config.AutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({AutoConfig.class})
public @interface AutoConfigAnno {
}
