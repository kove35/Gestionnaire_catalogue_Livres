package commandes;

import javax.servlet.http.HttpServletRequest;

import beans.Livre;

public class CommandeEditerLivre extends Commande {

    @Override
    public String getNomCommande() {

        return "EditerLivre";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        Livre l = dao.lookupBookById( Long.parseLong( req.getParameter( "id" ) ) );
        req.setAttribute( "livre", l );
        Action action = new Action( "editLivre.jsp", false );

        return action;
    }

}
