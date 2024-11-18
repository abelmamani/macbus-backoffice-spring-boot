package shape.inpúts;

import busroute.models.RouteProgressStatus;
import shape.models.CreateShapeRequestModel;

public interface CreateShapeInput {
    RouteProgressStatus createShape(CreateShapeRequestModel createShapeRequestModel);
}
