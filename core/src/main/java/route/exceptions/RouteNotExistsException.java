package route.exceptions;

public class RouteNotExistsException extends RuntimeException{
    public RouteNotExistsException(String msg){
        super(msg);
    }
}
