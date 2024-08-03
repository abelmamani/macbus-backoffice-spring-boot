package ar.edu.undec.adapter.data.shape.repoimplementation;

import ar.edu.undec.adapter.data.shape.crud.ShapeCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shape.models.ShapeResponseModel;
import shape.outputs.GetShapesByRouteRepository;
import java.util.List;

@AllArgsConstructor
@Service
public class GetShapesByRouteRepoImplementation implements GetShapesByRouteRepository {
    private ShapeCRUD shapeCRUD;

    @Override
    public List<ShapeResponseModel> findAllShapesByRouteLongName(String longName) {
        return shapeCRUD.findAllShapesByRouteLongName(longName);
    }
}
