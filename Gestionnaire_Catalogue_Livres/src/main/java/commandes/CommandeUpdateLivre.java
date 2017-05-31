package commandes;

import javax.servlet.http.HttpServletRequest;

import beans.Livre;

public class CommandeUpdateLivre extends Commande {

    @Override
    public String getNomCommande() {

        return "updateBook";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        // Long id = Long.parseLong( req.getParameter( "id" ) );
        Livre l = dao.lookupBookById( req.getParameter( "id" ) );
        dao.updateBook( l );
        return new Action( "/book", false );
    }

}
