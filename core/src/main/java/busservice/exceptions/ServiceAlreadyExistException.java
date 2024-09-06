package busservice.exceptions;

public class ServiceAlreadyExistException extends RuntimeException{
    public ServiceAlreadyExistException(String msg){
        super(msg);
    }
}
