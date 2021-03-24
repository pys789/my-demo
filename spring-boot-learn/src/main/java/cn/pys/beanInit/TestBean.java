package cn.pys.beanInit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TestBean implements BeanNameAware, InitializingBean, ApplicationContextAware {
    private String beanName;
    static String staticWord;
    static  {
        System.out.println("father staticWord init in static");
        staticWord="hi";
    }
    public TestBean(){
        System.out.println("testBean construct method invoke...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterProperties Set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applictionContextAware");
    }
}