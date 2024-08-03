package stop.exceptions;

public class StopAlreadyExistException extends RuntimeException{
    public StopAlreadyExistException(String msg){
        super(msg);
    }
}
