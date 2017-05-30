
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import beans.Livre;
import dao.LivreDAO;
import dao.LivreDAOJDBCSQLImpl2;

public class TestMetier {

    public static void main( String[] args ) throws ParseException {
        LivreDAO metier = new LivreDAOJDBCSQLImpl2();
        String dateString = "1999-02-03";
        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        java.util.Date date = df.parse( dateString );
        java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
        // Livre nouveauLivre = metier.addBook( "Conception d'applicatios en
        // java/jee","patterns et architectures", 20.0, "1999-02-03" );

        List<Livre> livres = metier.listBooks();
        for ( Livre livre : livres ) {
            System.out.println( livre );
        }

    }

}
