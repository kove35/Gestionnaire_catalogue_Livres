package commandes;

import javax.servlet.http.HttpServletRequest;

import beans.Livre;

public class CommandeconfirmationDelete extends Commande {

    @Override
    public String getNomCommande() {

        return "confirmationDelete";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        Livre l = dao.lookupBookById( req.getParameter( "id" ) );
        req.setAttribute( "livre", l );

        return new Action( "/confirmationDelete.jsp?idsupprimer=id", false );
    }

}
