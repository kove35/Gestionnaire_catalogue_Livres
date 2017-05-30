package commandes;

import javax.servlet.http.HttpServletRequest;

import dao.LivreDAO;

public abstract class Commande {
    protected LivreDAO dao;

    public void setDao( LivreDAO dao ) {
        this.dao = dao;
    }

    public abstract String getNomCommande();

    public abstract Action executerAction( HttpServletRequest req );
}
