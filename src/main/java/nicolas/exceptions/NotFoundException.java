package nicolas.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super(id + " non è stato trovato");
    }
}
