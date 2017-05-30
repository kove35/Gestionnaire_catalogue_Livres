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
     * Cette méthode renvoie l'exécution de la méthode withDB que nous avons
     * déjà vue : celle qui fait la tambouille JDBC. Le paramètre passé à cette
     * fonction est une instance d'une classe interne anonyme : new
     * JDBCRunner<List<Book>>() {...}; est une instance anonyme (on a pas mis le
     * résultat dans une variable, on l'a juste passé en paramètre à withDB),
     * qui implémente JDBCRunner et donc possède une méthode run. Ca tombe,
     * bien, la méthode withDB appelle la méthode run de l'objet qu'elle reçoit
     * en paramètres !!! Donc que fait la méthode doList ? 1. Elle appelle
     * withDB, lui passe en paramètre un objet qui a la méthode run() à appeler
     * juste après qu'elle ait ouvert la connexion et démarré une transaction,
     * et juste avant de fermer la connexion et comiter la transaction. 2. Elle
     * renvoie (si tout s'est bien passé, s'il n'y a pas eu de rollback) le
     * résultat renvoyé par la méthode run()...
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
     * On accède à la base de données avec une annotation de code. C'est
     * l'annotation @Resource qui va obtenir la connexion, auprès du serveur une
     * fois l'application déployée. Pas besoin de driver, login, password,
     * etc...
     *****************************************************************************/
    @Resource( lookup = "java:app/jdbc/bookstore" )
    private DataSource       dataSource;
    private SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

    /******************************************************************************
     * 1. On ouvre la connextion, on démarre une transaction (begin...) 2. On
     * envoie la requête SQL, on récupère et traite les résultats, 3. On commit
     * la transaction et on ferme la connexion. Bien entendu il faut faire des
     * try... catch... finally pour traiter tous les cas d'erreur, etc... Plutot
     * que de re-écrire le code qui ne change pas dans chaque méthode d'ajout,
     * recherche, suppression de livres, on a opté pour une conception plus
     * générique. Tout le sale travail est en réalité effectué dans la méthode
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
     * runner.run(connection); qui revient à appeler la méthode run() d'un objet
     * de type JDBCRunner passé en paramètre. Le reste du code c'est de "la
     * tambouille JDBC". Cette méthode est très classique auprès des
     * programmeurs JDBC expérimentés. JDBCRunner est juste une interface privée
     * que l'on trouve dans le même fichier :
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
