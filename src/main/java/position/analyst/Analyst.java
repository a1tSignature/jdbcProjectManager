package position.analyst;

import position.BasePosition;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;

import java.util.Arrays;

public final class Analyst extends BasePosition implements AnalystAction {

    //private final boolean[] status = new boolean[2];

    public Analyst(PositionType positionType, Specification specification) {
        super(positionType, specification, new boolean[2]);
    }

    @Override
    public void toCollectStatistic() {

        System.out.println("Collecting statistic...");
        status[0] = true;
    }

    @Override
    public void doResearch() {

        System.out.println("Do researching...");
        status[1] = true;
    }

    @Override
    public void doReport() {

        if(status[0])
            System.out.println("Statistics were collected");
        if(status[1])
            System.out.println("The research was conducted");

        System.out.println("A report is generated");

        Arrays.fill(status, Boolean.FALSE);
    }

    @Override
    public void doWork(int actionCode) {

        switch (actionCode) {
            case (0):
                toCollectStatistic();
                break;

            case (1):
                doResearch();
                break;

            default:
                doReport();
                break;
        }
    }
}
