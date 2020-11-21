package di.com.a1tSign;

import di.org.springframework.beans.factory.BeanFactory;
import di.org.springframework.context.ApplicationContext;

public class Main {


    public static void main(String[] args) throws ReflectiveOperationException {

        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("di.a1tSign");
        ProductService productService = (ProductService) beanFactory.getBean("productService");
        beanFactory.addPostProcessor(new CustomPostProcessor());
        beanFactory.addPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();
        beanFactory.injectBeanFactories();
        beanFactory.initializeBeans();
        beanFactory.close();

        System.out.println(productService.getPromotionsService());

        System.out.println("Bean name = " + productService.getPromotionsService().getBeanName());
        System.out.println("Factory name = " + productService.getPromotionsService().getBeanFactory());

        ApplicationContext applicationContext = new ApplicationContext("di.a1tSign");
        applicationContext.close();

    }
}
