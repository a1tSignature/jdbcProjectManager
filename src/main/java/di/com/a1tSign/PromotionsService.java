package di.com.a1tSign;

import di.org.springframework.beans.factory.BeanFactory;
import di.org.springframework.beans.factory.BeanFactoryAware;
import di.org.springframework.beans.factory.BeanNameAware;
import di.org.springframework.beans.factory.DisposableBean;
import di.org.springframework.beans.factory.annotation.javax.PostConstruct;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.context.ApplicationListener;
import di.org.springframework.context.event.ContextClosedEvent;

@Component
public class PromotionsService implements BeanNameAware, BeanFactoryAware, DisposableBean,
        ApplicationListener<ContextClosedEvent> {

    private String beanName;
    private BeanFactory beanFactory;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory factory) {
        beanFactory = factory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @PostConstruct
    void prints() {
        System.out.println("after destroying");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(">> ContextClosed EVENT");
    }
}
