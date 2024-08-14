package shape.usecases;

import route.inputs.GetRouteInput;
import shape.inpúts.GetShapesByRouteInput;
import shape.models.Shape;
import java.util.List;

public class GetShapesByRouteUseCase implements GetShapesByRouteInput {
    private GetRouteInput getRouteInput;

    public GetShapesByRouteUseCase(GetRouteInput getRouteInput) {
        this.getRouteInput = getRouteInput;
    }

    @Override
    public List<Shape> getAllShapes(String longName) {
        return getRouteInput.getRouteByName(longName).getShapes();
    }
}
