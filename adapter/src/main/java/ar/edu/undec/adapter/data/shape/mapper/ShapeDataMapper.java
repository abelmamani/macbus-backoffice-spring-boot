package ar.edu.undec.adapter.data.shape.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.shape.models.ShapeNode;
import shape.models.Shape;

public class ShapeDataMapper {
    public static Shape dataCoreMapper(ShapeNode shapeNode){
        try {
            return Shape.getInstance(shapeNode.getId(),
                    shapeNode.getLatitude(),
                    shapeNode.getLongitude(),
                    shapeNode.getSequence(),
                    shapeNode.getDistanceTraveled());
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static ShapeNode dataNodeMapper(Shape shape){
        try {
            return ShapeNode.builder()
                    .id(shape.getId())
                    .latitude(shape.getLatitude())
                    .longitude(shape.getLongitude())
                    .sequence(shape.getSequence())
                    .distanceTraveled(shape.getDistanceTraveled())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}
