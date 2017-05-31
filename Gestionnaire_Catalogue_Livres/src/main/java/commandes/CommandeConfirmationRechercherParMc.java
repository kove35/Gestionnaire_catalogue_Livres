package commandes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Livre;

public class CommandeConfirmationRechercherParMc extends Commande {

    @Override
    public String getNomCommande() {
        // TODO Auto-generated method stub
        return "confirmationRechercheParMc";
    }

    @Override
    public Action executerAction( HttpServletRequest req ) {
        String motCle = req.getParameter( "motCle" );
        System.out.println( "***********motCle :" + motCle + "***********" );
        List<Livre> livres = dao.lookupBookByMc( motCle );
        System.out.println( "*************nombre de livres: " + livres.size() );
        req.setAttribute( "livres", livres );
        // req.setAttribute( "motCle", motCle );

        return new Action( "resultatRchercheParMc.jsp", false );
    }

}
