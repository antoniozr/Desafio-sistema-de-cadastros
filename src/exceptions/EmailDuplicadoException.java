package exceptions;

public class EmailDuplicadoException extends Exception{
    public EmailDuplicadoException(String mensagem){
        super(mensagem);
    }
}
