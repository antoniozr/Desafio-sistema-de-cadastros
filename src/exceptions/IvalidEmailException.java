package exceptions;

public class IvalidEmailException extends Exception{
    public IvalidEmailException(String mensagem){
        super(mensagem);
    }
}
