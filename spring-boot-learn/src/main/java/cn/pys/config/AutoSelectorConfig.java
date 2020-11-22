package cn.pys.config;

import cn.pys.annotation.AutoSelectorConfigAnno;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class AutoSelectorConfig implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(AutoSelectorConfigAnno.class.getName());
        boolean isLinux = (boolean) annotationAttributes.get("isLinux");
        // 可以根据属性值做出不同的装配
        if (isLinux) {
            return new String[]{AutoConfig.class.getName()};
        } else {
            return new String[]{AutoConfig2.class.getName()};
        }
    }
}
