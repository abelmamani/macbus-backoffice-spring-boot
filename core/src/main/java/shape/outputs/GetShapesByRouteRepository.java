package shape.outputs;

import shape.models.Shape;

import java.util.List;

public interface GetShapesByRouteRepository {
    List<Shape> findAllShapesByRouteLongName(String longName);
}
