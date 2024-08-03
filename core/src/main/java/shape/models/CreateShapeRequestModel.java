package shape.models;

import java.util.ArrayList;

public class CreateShapeRequestModel {
    private Long routeId;
    private ArrayList<Shape> shapes;
    public CreateShapeRequestModel(){}
    public CreateShapeRequestModel(Long routeId, ArrayList<Shape> shapes) {
        this.routeId = routeId;
        this.shapes = shapes;
    }

    public Long getRouteId() {
        return routeId;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
