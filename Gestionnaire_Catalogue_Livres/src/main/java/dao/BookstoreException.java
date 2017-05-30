package dao;

public class BookstoreException extends Exception {
    public BookstoreException() {
    }

    public BookstoreException( String message ) {
        super( message );
    }

    public BookstoreException( Exception e ) {
        super( e );
    }
}