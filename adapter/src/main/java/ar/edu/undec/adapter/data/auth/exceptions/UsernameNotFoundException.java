package ar.edu.undec.adapter.data.auth.exceptions;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
