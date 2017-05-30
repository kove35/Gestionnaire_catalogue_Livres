package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@JDBCMySQL
public class LivreDAOJDBCSQLImpl implements LivreDAO {
    @Resource( lookup = "jdbc/catalogue_livres" )
    private DataSource       dataSource;
    private SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );

    public Livre addBook( final String title, final String description, final Double price,
            final Date pubDate ) {
        withDB( new JDBCRunner<Livre>() {
            public Livre run( Connection connection ) throws Exception {
                int id = 0;

                PreparedStatement ps = connection
                        .prepareStatement( "insert into book (title, description,price,pubDate) value(?,?,?,?) " );
                ps.setString( 1, title );
                ps.setString( 2, description );
                ps.setDouble( 3, price );
                /*
                 * prepareStatement.setBigDecimal( 3, new BigDecimal( price ) );
                 * Calendar calendar = Calendar.getInstance(); calendar.setTime(
                 * dateFormat.parse( pubDate ) ); prepareStatement.setDate( 4,
                 * new Date( calendar.getTimeInMillis() ) );
                 */
                ps.setDate( 4, (java.sql.Date) pubDate );
                int rowCount = ps.executeUpdate();
                if ( rowCount != 1 ) {
                    throw new BookstoreException(
                            "Unable to insert book into bookstore" );

                }
                PreparedStatement ps2 = connection.prepareStatement( "select MAX(id) AS MAX_ID from book" );
                ResultSet rs = ps2.executeQuery();

                if ( rs.next() ) {
                    id = rs.getInt( "MAX_ID" );
                }
                ps.close();

                return new Livre( id, title, description, price, pubDate );
            }
        } );

    }

    public List<Livre> listBooks() {
        System.out.println( "DANS LIST BOOKS" );
        return doList( null );
    }

    private List<Livre> doList( final String orderBy ) {

        return withDB( new JDBCRunner<List<Livre>>() {

            public List<Livre> run( Connection connection ) throws Exception {
                List<Livre> listing = new ArrayList<Livre>();
                Statement statement = connection.createStatement();
                final String query = "select * from book"
                        + ( orderBy != null ? " ORDER BY " + orderBy + ";" : ";" );
                ResultSet rs = statement.executeQuery( query );
                while ( rs.next() ) {
                    Livre livre = new Livre();
                    livre.setId( "" + rs.getLong( "id" ) );
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

    public Livre lookupBookById( String id ) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addBook( String title, String description, BigDecimal price, java.util.Date pubDate ) {
        // TODO Auto-generated method stub

    }

    public void updateBook( String id, String title, String description, String price, String pubDate ) {
        // TODO Auto-generated method stub

    }

    public void removeBook( String id ) {
        // TODO Auto-generated method stub

    }

    public List<Livre> listBooks( String orderBy ) {
        // TODO Auto-generated method stub
        return null;
    }

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
            try {
                connection.rollback();
            } catch ( SQLException e ) {
                // should log this as a warn or info
            }
            return null;
        } finally {
            if ( connection != null ) {
                try {
                    connection.close();
                } catch ( Exception ex ) {
                    // should log this as a warn or info
                }
            }
        }
    }

    static interface JDBCRunner<T> {
        T run( Connection connection ) throws Exception;
    }

    public void updateBook( String id, String title, String description, Double price, String pubDate ) {
        // TODO Auto-generated method stub

    }

}