package dao;

import java.util.List;

import beans.Livre;

public interface LivreDAO {

    public Livre addBook( String title, String description, Double price, String pubDate, String auteur );

    public List<Livre> listBooks();

    public Livre lookupBookById( String id );

    public void updateBook( Livre l );

    public void removeBook( String id );

    public List<Livre> lookupBookByMc( String mc );

    // public void saveBook( String id, HttpServletRequest request );

}
