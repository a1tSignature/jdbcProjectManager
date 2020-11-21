package util;

import employee.Employee;
import employee.Rank;
import employee.builder.EmployeeBuilder;
import employee.builder.EmployeeBuilderImpl;
import position.BasePosition;
import position.analyst.Analyst;
import position.developer.backend.AndroidBackendDeveloper;
import position.developer.backend.IosBackendDeveloper;
import position.developer.backend.JavaServerDeveloper;
import position.developer.frontend.AndroidFrontendDeveloper;
import position.developer.frontend.IosFrontendDeveloper;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;
import position.tester.Tester;

import java.util.List;

public final class EmployeeParser {

    public static Employee parse(List<String> data) {
        EmployeeBuilder builder = new EmployeeBuilderImpl();

        builder.setName(data.get(0));
        builder.setSurname(data.get(1));
        builder.setPatronymic(data.get(2));
        builder.setPosition(getPosition(parsePositionType(data.get(3)), parseSpecification(data.get(4))));
        builder.setRank(parseRank(data.get(5)));

        return builder.getEmployee();
    }

    private static PositionType parsePositionType(String position) {
        PositionType positionType = null;
        var list = PositionType.values();
        for(PositionType pos : list) {
            if(pos.name().equalsIgnoreCase(position)) positionType = pos;
        }

        return positionType;
    }

    private static Specification parseSpecification(String position) {
        Specification positionType = null;
        var list = Specification.values();
        for(Specification pos : list) {
            if(pos.name().equalsIgnoreCase(position)) positionType = pos;
        }

        return positionType;
    }

    private static Rank parseRank(String position) {
        Rank positionType = null;
        var list = Rank.values();
        for(Rank pos : list) {
            if(pos.name().equalsIgnoreCase(position)) positionType = pos;
        }

        return positionType;
    }

    private static BasePosition getPosition(PositionType positionType, Specification specification) {

        if (positionType == PositionType.ANALYST)
            return new Analyst(positionType, Specification.NONE);
        else if (positionType == PositionType.TESTER)
            return new Tester(positionType, Specification.NONE);

        else if (positionType == PositionType.BACKEND) {
            if (specification == Specification.ANDROID)
                return new AndroidBackendDeveloper(positionType, specification);
            else if (specification == Specification.IOS)
                return new IosBackendDeveloper(positionType, specification);

            else return new JavaServerDeveloper(positionType, specification);
        }
        else {
            if (specification == Specification.ANDROID)
                return new AndroidFrontendDeveloper(positionType, specification);
            else
                return new IosFrontendDeveloper(positionType, specification);
        }
    }
}
