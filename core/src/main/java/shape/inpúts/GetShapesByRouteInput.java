package shape.inpúts;

import shape.models.Shape;

import java.util.List;

public interface GetShapesByRouteInput {
    List<Shape> getAllShapes(String longName);
}
