package ar.edu.undec.adapter.data.shape.repoimplementations;

import ar.edu.undec.adapter.data.shape.crud.ShapeCRUD;
import ar.edu.undec.adapter.data.shape.mapper.ShapeDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shape.models.ShapeResponseModel;
import shape.outputs.ShapeRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ShapeRepoImpelemtation implements ShapeRepository {
    private ShapeCRUD shapeCRUD;
    @Override
    public List<ShapeResponseModel> findAllByRouteLongName(String longName) {
        List<Map<String, Object>> results = shapeCRUD.findAllShapesByRouteLongName(longName);
        return results.stream()
                .map(ShapeDataMapper::mapToShapeResponseModel)
                .collect(Collectors.toList());
    }
}
