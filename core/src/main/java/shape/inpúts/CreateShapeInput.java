package shape.inpúts;

import busroute.models.RouteStatus;
import shape.models.CreateShapeRequestModel;

public interface CreateShapeInput {
    RouteStatus createShape(CreateShapeRequestModel createShapeRequestModel);
}
