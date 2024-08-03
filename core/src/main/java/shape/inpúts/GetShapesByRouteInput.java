package shape.inpúts;

import shape.models.ShapeResponseModel;
import java.util.List;

public interface GetShapesByRouteInput {
    List<ShapeResponseModel> getAllShapes(String longName);
}
