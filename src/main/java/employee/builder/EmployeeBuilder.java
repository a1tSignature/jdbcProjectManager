package employee.builder;

import employee.EmployeeImpl;
import position.BasePosition;
import employee.Rank;

public interface EmployeeBuilder {

    void setName(String name);
    void setSurname(String name);
    void setPatronymic(String name);
    void setPosition(BasePosition position);
    void setRank(Rank rank);
    EmployeeImpl getEmployee();

}
