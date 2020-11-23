package di.org.springframework.context;

import di.com.a1tSign.CommonAnnotationBeanPostProcessor;
import di.com.a1tSign.CustomPostProcessor;
import di.com.a1tSign.ProductService;
import di.org.springframework.context.event.ContextClosedEvent;
import di.org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ApplicationContext {

    private BeanFactory beanFactory = new BeanFactory();

    public ApplicationContext(String basePackage) throws ReflectiveOperationException {
        System.out.println("******Context is under construction******");

        beanFactory.instantiate(basePackage);
        beanFactory.addPostProcessor(new CustomPostProcessor());
        beanFactory.addPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();
        beanFactory.injectBeanFactories();
        beanFactory.initializeBeans();
        beanFactory.postConstruct();

//        System.out.println(productService.getPromotionsService());

//        System.out.println("Bean name = " + productService.getPromotionsService().getBeanName());
//        System.out.println("Factory name = " + productService.getPromotionsService().getBeanFactory());
    }

    public void close() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        beanFactory.close();

        for (Object bean : beanFactory.getSingletons().values()) {
            for (Type type: bean.getClass().getGenericInterfaces()){
                if(type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType) type;

                    Type firstParameter = parameterizedType.getActualTypeArguments()[0];
                    if(firstParameter.equals(ContextClosedEvent.class)){
                        Method method = bean.getClass().getMethod("onApplicationEvent", ContextClosedEvent.class);
                        method.invoke(bean, new ContextClosedEvent());
                    }
                }
            }
        }
    }
}
