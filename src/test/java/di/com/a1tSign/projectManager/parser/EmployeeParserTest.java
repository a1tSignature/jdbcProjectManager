package di.com.a1tSign.projectManager.parser;

import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.util.EmployeeParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeParserTest {

    @Test
    public void testParsing() {
        List<String> list = new ArrayList<>() {{
            {{add("Name"); }}
            {{add("Surname");}}
            {{add("Patr"); }}
            {{add("BACKEND");}}
            {{ add("IOS"); }}
            {{add("JUNIOR");}}
        }};

        EmployeeImpl employee = (EmployeeImpl) EmployeeParser.parse(list);

        assertEquals(employee.getName(), "Name");
    }
}
