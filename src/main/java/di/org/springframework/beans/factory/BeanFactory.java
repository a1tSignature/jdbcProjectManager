package di.org.springframework.beans.factory;

import di.ScanningTools;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.annotation.javax.PostConstruct;
import di.org.springframework.beans.factory.annotation.javax.PreDestroy;
import di.org.springframework.beans.factory.annotation.javax.Resource;
import di.org.springframework.beans.factory.config.BeanPostProcessor;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.Repository;
import di.org.springframework.beans.factory.stereotype.Service;
import di.org.springframework.beans.factory.stereotype.priority.Primary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@SuppressWarnings ({"deprecated", "unused"})
//TODO: refactor and debug di system into project
//TODO: delegate repository-bean, service-bean etc.. in several groups of beans.
//TODO: find usages for @PostConstruct and @PreDestroy (optional)
//TODO: remove @SuppressWarnings("deprecated"): find replacement for Class<?>#newInstance which is deprecated
public class BeanFactory extends ScanningTools {

    private Map<String, Object> singletons = new HashMap<>();
    private List<BeanPostProcessor> postProcessors = new ArrayList<>();

    public void addPostProcessor(BeanPostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    public List<BeanPostProcessor> getPostProcessors() {
        return postProcessors;
    }

    public void instantiate(String basePackage) {

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            for (String path : paths) {
                //String path = basePackage.replace('.', '/');
                Enumeration<URL> resources = classLoader.getResources(path);
                while (resources.hasMoreElements()) {
                    URL resource = resources.nextElement();

                    File file = new File(resource.toURI());

                    for (File classFile : Objects.requireNonNull(file.listFiles())) {
                        String fileName = classFile.getName();

                        if (fileName.endsWith(".class")) {
                            String className = fileName.substring(0, fileName.lastIndexOf("."));

                            Class<?> classObject = Class.forName(path.replace("/", ".") + "." + className);

                            if (classObject.isAnnotationPresent(Primary.class) && (
                                    classObject.isAnnotationPresent(Component.class)
                                            || classObject.isAnnotationPresent(Repository.class)
                                            || classObject.isAnnotationPresent(Service.class))) {
                                System.out.println("Component: " + classObject);

                                Object instance = classObject.newInstance();
                                String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                                singletons.put(beanName, instance);
                            }
                        }
                    }

                }
            }

        } catch (IOException | URISyntaxException | ClassNotFoundException
                | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void populateProperties() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("==populateProperties==");

        for (Object object : singletons.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {

                    for (String dependency : singletons.keySet()) {
                        if (dependency.toLowerCase().equals(field.getName().toLowerCase())) {

                            String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                            System.out.println("Setter name = " + setterName);

                            Method setter = object.getClass().getMethod(setterName, singletons.get(dependency).getClass());

                            setter.invoke(object, singletons.get(dependency));
                        }
                    }
                } else if (field.isAnnotationPresent(Resource.class)) { // Custom realization of @Resource annotation injection

                    for (Object dependency : singletons.values()) {

                        int position = dependency.getClass().getName().lastIndexOf('.');

                        if ((dependency.getClass().getName().substring(position + 1, position + 2)
                                .toLowerCase() + dependency.getClass().getName().substring(position + 2))
                                .equals(field.getName())) {

                            String setterName = "set" + field.getName().substring(0, 1).toUpperCase() +
                                    field.getName().substring(1);

                            System.out.println("Setter name: " + setterName);

                            Method setter = object.getClass().getMethod(setterName, dependency.getClass());
                            setter.invoke(object, dependency);
                        }
                    }
                }
            }
        }
    }

    public void injectBeanNames() {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(name);
            }
        }
    }

    public void injectBeanFactories() {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }
    }

    public void initializeBeans() {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);

            for (BeanPostProcessor postProcessor : postProcessors) {
                postProcessor.postProcessBeforeInitialization(bean, name);
            }
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
            for (BeanPostProcessor postProcessor : postProcessors) {
                postProcessor.postProcessAfterInitialization(bean, name);
            }
        }
    }

    public void postConstruct() {
        for (Object bean : singletons.values()) {
            Method[] met = bean.getClass().getDeclaredMethods();
            for (Method method : met) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void close() {
        for (Object bean : singletons.values()) {
            Method[] met = bean.getClass().getMethods();
            for (Method method : met) {
                if (method.isAnnotationPresent(PreDestroy.class)) {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (bean instanceof DisposableBean) {
                ((DisposableBean) bean).destroy();
            }
        }
    }

}
