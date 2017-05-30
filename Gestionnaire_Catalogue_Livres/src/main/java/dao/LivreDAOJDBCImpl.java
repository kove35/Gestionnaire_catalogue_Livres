package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import beans.Livre;

@ApplicationScoped
@InMemory
@JDBC
public class LivreDAOJDBCImpl implements LivreDAO {

    public Livre addBook( String title, String description, Double price, Date pubDate ) {
        // TODO Auto-generated method stub
        return null;

    }

    public Livre lookupBookById( String id ) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateBook( String id, String title, String description, Double price, String pubDate ) {
        // TODO Auto-generated method stub

    }

    public void removeBook( String id ) {
        // TODO Auto-generated method stub

    }

    /*****************************************************************************
     * Cette m�thode renvoie l'ex�cution de la m�thode withDB que nous avons
     * d�j� vue : celle qui fait la tambouille JDBC. Le param�tre pass� � cette
     * fonction est une instance d'une classe interne anonyme : new
     * JDBCRunner<List<Book>>() {...}; est une instance anonyme (on a pas mis le
     * r�sultat dans une variable, on l'a juste pass� en param�tre � withDB),
     * qui impl�mente JDBCRunner et donc poss�de une m�thode run. Ca tombe,
     * bien, la m�thode withDB appelle la m�thode run de l'objet qu'elle re�oit
     * en param�tres !!! Donc que fait la m�thode doList ? 1. Elle appelle
     * withDB, lui passe en param�tre un objet qui a la m�thode run() � appeler
     * juste apr�s qu'elle ait ouvert la connexion et d�marr� une transaction,
     * et juste avant de fermer la connexion et comiter la transaction. 2. Elle
     * renvoie (si tout s'est bien pass�, s'il n'y a pas eu de rollback) le
     * r�sultat renvoy� par la m�thode run()...
     *****************************************************************************/
    public List<Livre> listBooks( final String orderBy ) {
        return withDB( new JDBCRunner<List<Livre>>() {

            public List<Livre> run( Connection connection ) throws Exception {
                List<Livre> listing = new ArrayList<Livre>();
                Statement statement = connection.createStatement();
                final String query = "select * from book" + ( orderBy != null ? " ORDER BY " + orderBy + "" : "" );
                ResultSet rs = statement.executeQuery( query );
                System.out.println( "SELECT * nb=" );
                while ( rs.next() ) {
                    System.out.println( "FOUND NEW BOOK" );
                    Livre livre = new Livre();
                    livre.setId( rs.getInt( "id" ) );
                    livre.setPrice( rs.getDouble( "price" ) );
                    livre.setPubDate( rs.getDate( "pubdate" ) );
                    livre.setTitle( rs.getString( "title" ) );
                    // Missing from online tutorial !

                    livre.setDescription( rs.getString( "description" ) );
                    listing.add( livre );
                }
                return listing;
            }
        } );
    }

    /******************************************************************************
     * On acc�de � la base de donn�es avec une annotation de code. C'est
     * l'annotation @Resource qui va obtenir la connexion, aupr�s du serveur une
     * fois l'application d�ploy�e. Pas besoin de driver, login, password,
     * etc...
     *****************************************************************************/
    @Resource( lookup = "java:app/jdbc/bookstore" )
    private DataSource       dataSource;
    private SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

    /******************************************************************************
     * 1. On ouvre la connextion, on d�marre une transaction (begin...) 2. On
     * envoie la requ�te SQL, on r�cup�re et traite les r�sultats, 3. On commit
     * la transaction et on ferme la connexion. Bien entendu il faut faire des
     * try... catch... finally pour traiter tous les cas d'erreur, etc... Plutot
     * que de re-�crire le code qui ne change pas dans chaque m�thode d'ajout,
     * recherche, suppression de livres, on a opt� pour une conception plus
     * g�n�rique. Tout le sale travail est en r�alit� effectu� dans la m�thode
     * suivante :
     *******************************************************************************/

    private <T> T withDB( JDBCRunner<T> runner ) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            boolean auto = connection.getAutoCommit();
            connection.setAutoCommit( false );
            T result = runner.run( connection );
            connection.commit();
            connection.setAutoCommit( auto ); // set it to what it was
                                              // previously.
            return result;
        } catch ( Exception ex ) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
            return null;
        } finally {
            if ( connection != null ) {
                try {
                    connection.close();
                } catch ( Exception ex ) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /***************************************************************************
     * La seule ligne qui varie dans ce code est : T result =
     * runner.run(connection); qui revient � appeler la m�thode run() d'un objet
     * de type JDBCRunner pass� en param�tre. Le reste du code c'est de "la
     * tambouille JDBC". Cette m�thode est tr�s classique aupr�s des
     * programmeurs JDBC exp�riment�s. JDBCRunner est juste une interface priv�e
     * que l'on trouve dans le m�me fichier :
     **************************************************************************/

    static interface JDBCRunner<T> {
        T run( Connection connection ) throws Exception;
    }

    @Override
    public List<Livre> listBooks() {
        // TODO Auto-generated method stub
        return null;
    }

}
