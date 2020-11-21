package position.developer.backend;

import position.BasePosition;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;

import java.util.Arrays;

public final class AndroidBackendDeveloper extends BasePosition implements BackendDeveloper {

    //private final boolean[] status = new boolean[3];

    public AndroidBackendDeveloper(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[3]);
    }

    @Override
    public void developDbModule() {

        System.out.println("Developing Android database module...");
        status[0] = true;
    }

    @Override
    public void developSecurityModule() {

        System.out.println("Developing Android security module...");
        status[1] = true;
    }

    @Override
    public void developServiceModule() {

        System.out.println("Developing Android service module...");
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

        if (status[0])
            System.out.println("Performed work on the development of a android  database module");
        if (status[1])
            System.out.println("Performed work on the development of a android security module");
        if (status[2])
            System.out.println("Performed work on the development of a android service module");

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
