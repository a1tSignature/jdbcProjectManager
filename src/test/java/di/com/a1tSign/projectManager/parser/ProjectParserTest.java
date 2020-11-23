package di.com.a1tSign.projectManager.parser;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.util.ProjectParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectParserTest {


    @Test
    public void testParse() {
        List<String> list = new ArrayList<>() {{
            {{add("title"); }}
            {{add("BACKEND_ONLY"); }}
        }};

        Project project = ProjectParser.parse(list);

        assertEquals(project.getTitle(), "title");
        assertNotNull(project.getSprints());
        
    }
}
