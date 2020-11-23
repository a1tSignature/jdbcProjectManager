package di.com.a1tSign.projectManager._old;

import di.org.springframework.beans.factory.BeanFactory;
import di.org.springframework.beans.factory.BeanFactoryAware;
import di.org.springframework.beans.factory.config.BeanPostProcessor;

public class CommonAnnotationBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory factory) {
        beanFactory = factory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
