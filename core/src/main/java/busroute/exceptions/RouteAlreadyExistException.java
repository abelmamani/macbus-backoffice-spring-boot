package busroute.exceptions;

public class RouteAlreadyExistException extends RuntimeException{
    public RouteAlreadyExistException(String msg){
        super(msg);
    }
}
