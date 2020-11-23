package di.com.a1tSign.projectManager.di;

import di.com.a1tSign.projectManager.repository.jdbc.employee.H2jdbcEmployeeEntityManagerImpl;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.annotation.javax.PostConstruct;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;
import di.org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

@Component
@Primary
@SuppressWarnings("unused")
public class TestDI {

    @Autowired
    private static H2jdbcEmployeeEntityManagerImpl h2jdbcEmployeeEntityManagerImpl;

    private static ApplicationContext applicationContext;
    private static int test;

    @PostConstruct
    private void setTest() {
        TestDI.test = 1;
    }

    @BeforeAll
    static void initializeContext() throws ReflectiveOperationException {
        TestDI.applicationContext = new ApplicationContext("");
    }

    @AfterAll
    static void closeContext() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assert applicationContext != null;
        applicationContext.close();
    }

    public void setH2jdbcEmployeeEntityManagerImpl(H2jdbcEmployeeEntityManagerImpl h2jdbcEmployeeEntityManagerImpl) {
        TestDI.h2jdbcEmployeeEntityManagerImpl = h2jdbcEmployeeEntityManagerImpl;
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(h2jdbcEmployeeEntityManagerImpl);
        //TODO: how to fix it? it was assumed that a different type of bean could be inserted here
        assertTrue(h2jdbcEmployeeEntityManagerImpl instanceof H2jdbcEmployeeEntityManagerImpl);
    }

    @Test
    public void testPostConstruct() {
        assertEquals(1, test);
    }
}
