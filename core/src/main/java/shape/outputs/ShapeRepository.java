package shape.outputs;

import shape.models.ShapeResponseModel;
import java.util.List;

public interface ShapeRepository {
    List<ShapeResponseModel> findAllByRouteLongName(String longName);
    void deleteShapesByRoute(String longName);
}
