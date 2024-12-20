package User.micro_sevice.Exception;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String e){
        super(e);
    }
}