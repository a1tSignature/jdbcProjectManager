package position;

import position.developer.specification.PositionType;
import position.developer.specification.Specification;


public abstract class BasePosition implements BaseAction {

    private PositionType positionType;
    protected boolean[] status;
    private Specification specification;

    public BasePosition(PositionType positionType,
                        Specification specification,
                        boolean[] status) {
        this.positionType = positionType;
        this.specification = specification;
        this.status = status;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public boolean[] getStatus() {
        return status;
    }

}
