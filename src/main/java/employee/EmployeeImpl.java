package employee;

import position.BasePosition;

public final class EmployeeImpl implements Employee {

    private final String name;
    private final String surname;
    private final String patronymic;

    private final BasePosition position;
    private Rank rank;

    public EmployeeImpl(String name, String surname, String patronymic, BasePosition position, Rank rank) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.rank = rank;
    }


    public String getFullName() {
        return name + " " + surname + " " + patronymic;
    }

    @Override
    public void performWork(int actionCode) {

        System.out.println("Worker " + getFullName() + " started his work day");

        position.doWork(actionCode);
    }

    @Override
    public void report() {
        position.doReport();
    }

    public BasePosition getPosition() {
        return position;
    }

    public Rank getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String info() {
        return name + " " + surname + " " + patronymic + " Specification: "
                + position.getSpecification() + " Rank: " + rank;
    }
}
