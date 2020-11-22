package di.com.a1tSign.projectManager.position.developer.backend;

import di.com.a1tSign.projectManager.position.BasePosition;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;

import java.util.Arrays;

public final class IosBackendDeveloper extends BasePosition implements BackendDeveloper {

    //private final boolean[] status = new boolean[4];

    public IosBackendDeveloper(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[4]);
    }

    @Override
    public void developDbModule() {

        System.out.println("Developing IOS database module...");
        status[0] = true;
    }

    @Override
    public void developSecurityModule() {

        System.out.println("Developing IOS security module...");
        status[1] = true;
    }

    @Override
    public void developServiceModule() {

        System.out.println("Developing IOS di.com.a1tSign.projectManager.service module...");
        status[2] = true;
    }

    private void updatePlatform() {
        System.out.println("Updating code for new IOS platform...");
        status[3] = true;
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
            System.out.println("Performed work on the development of a ios  database module");
        if (status[1])
            System.out.println("Performed work on the development of a ios security module");
        if (status[2])
            System.out.println("Performed work on the development of a ios di.com.a1tSign.projectManager.service module");
        if(status[3])
            System.out.println("New ios platform has been installed...");

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
            case (3):
                updatePlatform();
                doReport();
                break;
            default:
                doReport();
                break;
        }
    }
}
