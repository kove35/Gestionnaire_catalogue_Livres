package commandes;

import javax.servlet.http.HttpServletRequest;

public class CommandeAjouter extends Commande {

    @Override
    public String getNomCommande() {

        return "ajouterLivre";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {

        return new Action( "addBook.jsp", false );
    }

}
