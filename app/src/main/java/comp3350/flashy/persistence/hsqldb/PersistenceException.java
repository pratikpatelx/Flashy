package comp3350.flashy.persistence.hsqldb;

public class PersistenceException extends Throwable {
    public PersistenceException(final Exception cause){
        super(cause);
    }
}
