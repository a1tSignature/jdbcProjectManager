package di.com.a1tSign.projectManager.position.developer.frontend;

import di.com.a1tSign.projectManager.position.BasePosition;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;

import java.util.Arrays;

public final class AndroidFrontendDeveloper extends BasePosition implements FrontendDeveloper {

    //private final boolean[] status = new boolean[3];

    public AndroidFrontendDeveloper(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[3]);
    }

    @Override
    public void developForms() {

        System.out.println("Developing android frontend forms...");
        status[0] = true;
    }

    @Override
    public void developDisplayOfObj() {

        System.out.println("Developing android display of objects...");
        status[1] = true;
    }

    @Override
    public void developMetrica() {

        System.out.println("Developing android Metrica...");
        status[2] = true;
    }


    /*@Override
    public void developingCircle() {

        developForms();
        developDisplayOfObj();
        developMetrica();
    }*/

    @Override
    public void doReport() {

        if (status[0])
            System.out.println("Performed work on the development of a android forms");
        if (status[1])
            System.out.println("Performed work on the development of a android displaying of objects");
        if (status[2])
            System.out.println("Performed work on the development of a metrica");

        System.out.println("A report is generated");

        Arrays.fill(status, Boolean.FALSE);
    }

    @Override
    public void doWork(int actionCode) {

        switch (actionCode) {
            case (0):
                developForms();
                doReport();
                break;
            case (1):
                developDisplayOfObj();
                doReport();
                break;
            case (2):
                developMetrica();
                doReport();
                break;
            default:
                doReport();
                break;
        }
    }
}
