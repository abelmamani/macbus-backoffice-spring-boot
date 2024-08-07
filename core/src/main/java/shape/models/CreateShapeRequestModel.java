package shape.models;

import java.util.ArrayList;

public class CreateShapeRequestModel {
    private String routeName;
    private ArrayList<Shape> shapes;
    public CreateShapeRequestModel(){}

    public CreateShapeRequestModel(String routeName, ArrayList<Shape> shapes) {
        this.routeName = routeName;
        this.shapes = shapes;
    }

    public String getRouteName() {
        return routeName;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
