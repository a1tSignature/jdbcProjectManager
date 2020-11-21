package employee.builder;

import employee.EmployeeImpl;
import employee.Rank;
import position.BasePosition;

public final class EmployeeBuilderImpl implements EmployeeBuilder {

    private String name;
    private String surname;
    private String patronymic;

    private BasePosition position;
    private Rank rank;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setPatronymic(String patronomic) {
        this.patronymic = patronomic;
    }

    @Override
    public void setPosition(BasePosition position) {
        this.position = position;
    }

    @Override
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public EmployeeImpl getEmployee() {
        return new EmployeeImpl(name, surname, patronymic, position, rank);
    }
}
