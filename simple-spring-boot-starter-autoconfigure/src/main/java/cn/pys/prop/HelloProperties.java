package cn.pys.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("my.hello")
public class HelloProperties {

    private String name;

    private Integer age;

    private String hometown;


}
