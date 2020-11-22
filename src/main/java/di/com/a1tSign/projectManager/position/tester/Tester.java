package di.com.a1tSign.projectManager.position.tester;

import di.com.a1tSign.projectManager.position.BasePosition;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;

import java.util.Arrays;

public final class Tester extends BasePosition implements TestAction {

    //private final boolean[] status = new boolean[2];

    public Tester(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[2]);
    }

    @Override
    public void testFrontend() {

        System.out.println("Testing frontend... ");
        status[0] = true;
    }

    @Override
    public void testBackend() {

        System.out.println("Testing backend...");
        status[1] = true;
    }

    @Override
    public void doReport() {

        if (status[0])
            System.out.println("The frontend was tested");
        if (status[1])
            System.out.println("The backend was tested");

        System.out.println("A report is generated");

        Arrays.fill(status, Boolean.FALSE);
    }

    @Override
    public void doWork(int actionCode) {

        switch (actionCode) {
            case (0):
                testFrontend();
                break;

            case (1):
                testBackend();
                break;

            default:
                doReport();
                break;
        }
    }

}
