package commandes;

import javax.servlet.http.HttpServletRequest;

public class CommandeSoumettreLivre extends Commande {

    @Override
    public String getNomCommande() {

        return "enregistrer";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        String title = req.getParameter( "titre" );
        String description = req.getParameter( "description" );
        String auteur = req.getParameter( "auteur" );
        Double price = Double.parseDouble( req.getParameter( "prix" ) );
        String dateString = req.getParameter( "date" );

        dao.addBook( title, description, price, dateString, auteur );
        return new Action( "/book", false );
    }

}
