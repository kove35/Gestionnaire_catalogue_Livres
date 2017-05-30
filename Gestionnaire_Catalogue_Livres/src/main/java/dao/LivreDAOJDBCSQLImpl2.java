package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import beans.Livre;

@ApplicationScoped
@JDBCMySQL
public class LivreDAOJDBCSQLImpl2 implements LivreDAO {
    @Resource( lookup = "jdbc/catalogue_livres" )
    private DataSource dataSource;
    // private SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy"
    // );

    public Livre addBook( final String title, final String description, final Double price, final String pubDate,
            final String auteur ) {
        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        java.util.Date date = null;
        int id = 0;
        Livre l = null;
        try {
            date = df.parse( pubDate );
        } catch ( ParseException e1 ) {
            e1.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date( date.getTime() );

        Connection connection = SingletonConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = connection
                    .prepareStatement( "insert into book (title, description,price,pubDate,auteur) value(?,?,?,?,?) " );
            ps.setString( 1, title );
            ps.setString( 2, description );
            ps.setDouble( 3, price );
            ps.setDate( 4, sqlDate );
            ps.setString( 5, auteur );
            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement( "select MAX(id) AS MAX_ID from book" );
            ResultSet rs = ps2.executeQuery();
            if ( rs.next() ) {
                id = rs.getInt( "MAX_ID" );
            }
            ps.close();
        } catch ( SQLException e ) {

            e.printStackTrace();
        }

        l = new Livre( id, title, description, price, date, auteur );
        return l;

    }

    public List<Livre> listBooks() {
        Connection connection = SingletonConnection.getConnection();
        List<Livre> listing = new ArrayList<Livre>();
        try {
            PreparedStatement ps = connection.prepareStatement( "select * from book" );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                Livre l = new Livre();
                l.setId( rs.getInt( "id" ) );
                l.setTitle( rs.getString( "title" ) );
                l.setDescription( rs.getString( "description" ) );
                l.setPubDate( rs.getDate( "pubdate" ) );
                l.setPrice( rs.getDouble( "price" ) );
                l.setAuteur( rs.getString( "auteur" ) );
                listing.add( l );
            }
            ps.close();
        } catch ( SQLException e ) {

            e.printStackTrace();
        }

        return listing;
    }

    public Livre lookupBookById( Long id ) {
        Connection connection = SingletonConnection.getConnection();
        Livre l = null;
        try {
            PreparedStatement ps = connection.prepareStatement( "select * from book where id like ?" );
            ps.setLong( 1, id );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                l = new Livre();
                l.setId( rs.getInt( "id" ) );
                l.setTitle( rs.getString( "title" ) );
                l.setDescription( rs.getString( "description" ) );
                l.setPubDate( rs.getDate( "pubdate" ) );
                l.setPrice( rs.getDouble( "price" ) );
                l.setAuteur( rs.getString( "auteur" ) );

            }
            ps.close();
        } catch ( SQLException e ) {

            e.printStackTrace();
        }

        return l;

    }

    public void updateBook( Livre l ) {
        Date date = (Date) l.getPubDate();
        java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
        Connection connection = SingletonConnection.getConnection();
        try {

            PreparedStatement ps = connection
                    .prepareStatement( "update  book set title=?,description=?,pubDate=?,auteur=? where id like ?" );
            ps.setString( 1, l.getTitle() );
            ps.setString( 2, l.getDescription() );
            ps.setDate( 3, sqlDate );
            ps.setString( 4, l.getAuteur() );
            ps.setLong( 5, l.getId() );
            ps.executeUpdate();
            ps.close();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

    }

    public void removeBook( String code ) {
        Connection conn = SingletonConnection.getConnection();
        try {
            // int id=Integer.parseInt(req.getParameter( "id" ));
            PreparedStatement ps = conn.prepareStatement( "delete from book where ID=? " );
            int id = Integer.parseInt( code );
            ps.setInt( 1, id );
            ps.executeUpdate();
            ps.close();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

    }

}