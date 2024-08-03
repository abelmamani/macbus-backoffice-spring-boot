package shape.inpúts;

import route.models.RouteStatus;
import shape.models.CreateShapeRequestModel;

public interface CreateShapeInput {
    RouteStatus createShape(CreateShapeRequestModel createShapeRequestModel);
}
