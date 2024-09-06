package busservice.exceptions;

public class ServiceNotExistsException extends RuntimeException{
    public ServiceNotExistsException(String msg){
        super(msg);
    }
}
