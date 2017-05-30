package commandes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Livre;

public class CommandeLister extends Commande {

    @Override
    public String getNomCommande() {

        return "listerLivres";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        // Integer p = Integer.parseInt( req.getParameter( "page" ) );
        // Page<Livre> livresPages = dao.listBooks( new PageRequest( 1, 5 ) );

        List<Livre> livres = null;

        livres = dao.listBooks();
        req.setAttribute( "livres", livres );

        return new Action( "book-list.jsp", false );
    }

}
