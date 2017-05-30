package commandes;

import javax.servlet.http.HttpServletRequest;

public class CommandeSupprimerLivre extends Commande {

    @Override
    public String getNomCommande() {

        return "SupprimerLivre";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {

        dao.removeBook( req.getParameter( "id" ) );
        return new Action( "/book", false );
    }

}
