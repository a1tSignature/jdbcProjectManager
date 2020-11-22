package di.com.a1tSign.projectManager.employee.builder;

import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.position.BasePosition;
import di.com.a1tSign.projectManager.employee.Rank;

public interface EmployeeBuilder {

    void setName(String name);
    void setSurname(String name);
    void setPatronymic(String name);
    void setPosition(BasePosition position);
    void setRank(Rank rank);
    EmployeeImpl getEmployee();

}
