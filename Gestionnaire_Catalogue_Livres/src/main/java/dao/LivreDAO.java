package dao;

import java.util.List;

import beans.Livre;

public interface LivreDAO {

    Livre addBook( String title, String description, Double price, String pubDate, String auteur );

    public List<Livre> listBooks();

    Livre lookupBookById( Long id );

    void updateBook( Livre l );

    void removeBook( String id );

    // public void saveBook( String id, HttpServletRequest request );

}
