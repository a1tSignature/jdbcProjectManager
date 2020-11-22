package di.com.a1tSign.projectManager.sprint;

import di.com.a1tSign.projectManager.position.developer.specification.PositionType;

public final class Sprint {

    private String name;

    private double elapsedTime;

    private PositionType sprintType;

    public Sprint(String name, double elapsedTime,
                  PositionType sprintType
                  ) {
        this.name = name;
        this.elapsedTime = elapsedTime;
        this.sprintType = sprintType;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionType getSprintType() {
        return sprintType;
    }

    public void setSprintType(PositionType sprintType) {
        this.sprintType = sprintType;
    }
}
