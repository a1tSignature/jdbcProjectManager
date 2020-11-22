package di.com.a1tSign;

import di.org.springframework.context.ApplicationContext;

public class Main {


    public static void main(String[] args) throws ReflectiveOperationException {
        ApplicationContext applicationContext = new ApplicationContext("");
        applicationContext.close();
    }

}
