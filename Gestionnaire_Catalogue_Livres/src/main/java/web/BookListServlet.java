package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import commandes.Action;
import commandes.Commande;
import commandes.GestionnaireCommandes;
import dao.LivreDAO;

@WebServlet( { "/book", "/ajouter", "/sauvegarder", "/editer", "/deleteBook", "/updateBook", "/confirmationDelete",
        "/rechercherParMc", "/confirmationRechercherParMc" } )
public class BookListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LivreDAO          livreDAO;

    @Override
    public void init() throws ServletException {
        WebApplicationContext springContext = WebApplicationContextUtils
                .getRequiredWebApplicationContext( this.getServletContext() );
        livreDAO = (LivreDAO) springContext.getBean( "dao" );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // livreDAO = new LivreDAOJDBCSQLImpl();
        doPost( request, response );

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Action action = null;
        Commande commande;
        String path;
        path = request.getServletPath();
        commande = GestionnaireCommandes.getCommande( path );
        if ( commande != null ) {
            commande.setDao( livreDAO );
            action = commande.executerAction( request );
            System.out.println( action );
        }
        if ( action.estRedirige() ) {
            response.sendRedirect( action.getChemin() );
        } else {
            request.getRequestDispatcher( "/" + action.getChemin() ).forward( request, response );
        }

    }

}