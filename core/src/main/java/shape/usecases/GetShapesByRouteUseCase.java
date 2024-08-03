package shape.usecases;

import shape.inpúts.GetShapesByRouteInput;
import shape.models.ShapeResponseModel;
import shape.outputs.GetShapesByRouteRepository;
import java.util.List;

public class GetShapesByRouteUseCase implements GetShapesByRouteInput {
    private GetShapesByRouteRepository getShapesByRouteRepository;

    public GetShapesByRouteUseCase(GetShapesByRouteRepository getShapesByRouteRepository) {
        this.getShapesByRouteRepository = getShapesByRouteRepository;
    }

    @Override
    public List<ShapeResponseModel> getAllShapes(String longName) {
        return getShapesByRouteRepository.findAllShapesByRouteLongName(longName);
    }
}
