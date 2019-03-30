package comp3350.flashy.persistence.hsqldb;

public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause){
        super(cause);
    }
}
