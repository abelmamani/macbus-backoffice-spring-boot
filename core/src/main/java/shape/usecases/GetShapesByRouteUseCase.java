package shape.usecases;

import shape.inpúts.GetShapesByRouteInput;
import shape.models.ShapeResponseModel;
import shape.outputs.ShapeRepository;
import java.util.List;

public class GetShapesByRouteUseCase implements GetShapesByRouteInput {
    private ShapeRepository shapeRepository;

    public GetShapesByRouteUseCase(ShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    @Override
    public List<ShapeResponseModel> getAllShapes(String longName) {
        return shapeRepository.findAllByRouteLongName(longName);
    }
}
