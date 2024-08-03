package shape.outputs;

import shape.models.ShapeResponseModel;
import java.util.List;

public interface GetShapesByRouteRepository {
    List<ShapeResponseModel> findAllShapesByRouteLongName(String longName);
}
