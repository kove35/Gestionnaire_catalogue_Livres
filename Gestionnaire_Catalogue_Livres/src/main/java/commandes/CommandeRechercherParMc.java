package commandes;

import javax.servlet.http.HttpServletRequest;

public class CommandeRechercherParMc extends Commande {

    @Override
    public String getNomCommande() {
        // TODO Auto-generated method stub
        return "rechercherParMc";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        // req.setAttribute( "motCle", req.getParameter( "mc" ) );
        return new Action( "formulaireRechercheParMc.jsp", false );
    }

}
