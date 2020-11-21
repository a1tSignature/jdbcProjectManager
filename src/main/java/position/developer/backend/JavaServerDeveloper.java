package position.developer.backend;

import position.BasePosition;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;
import sprint.Sprint;

import java.util.ArrayList;
import java.util.Arrays;

public final class JavaServerDeveloper extends BasePosition implements BackendDeveloper {

    //private final boolean[] status = new boolean[4];

    public JavaServerDeveloper(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[4]);
    }

    @Override
    public void developDbModule() {

        System.out.println("Developing database module...");
        status[0] = true;
    }

    @Override
    public void developSecurityModule() {

        System.out.println("Developing security module...");
        status[1] = true;
    }

    @Override
    public void developServiceModule() {

        System.out.println("Developing service module...");
        status[2] = true;
    }

    /*@Override
    public void developingCircle() {

        developDbModule();
        developSecurityModule();
        developServiceModule();
    }*/

    @Override
    public void doReport() {

        if(status[0])
            System.out.println("Performed work on the development of a database module");
        if(status[1])
            System.out.println("Performed work on the development of a security module");
        if(status[2])
            System.out.println("Performed work on the development of a service module");

        System.out.println("A report is generated");

        Arrays.fill(status, Boolean.FALSE);
    }

    @Override
    public void doWork(int actionCode) {

        switch (actionCode) {
            case (0):
                developDbModule();
                doReport();
                break;
            case (1):
                developSecurityModule();
                doReport();
                break;
            case (2):
                developServiceModule();
                doReport();
                break;
            default:
                doReport();
                break;
        }
    }
}
