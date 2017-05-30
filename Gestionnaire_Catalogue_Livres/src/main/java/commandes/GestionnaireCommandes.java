package commandes;

import java.util.concurrent.ConcurrentHashMap;

public class GestionnaireCommandes {

    protected static ConcurrentHashMap<String, Commande> commandes = new ConcurrentHashMap<String, Commande>();
    static {
        init();
    }

    public static void init() {
        commandes.put( "/book", new CommandeLister() );
        commandes.put( "/ajouter", new CommandeAjouter() );
        commandes.put( "/sauvegarder", new CommandeSoumettreLivre() );
        commandes.put( "/editer", new CommandeEditerLivre() );
        commandes.put( "/updateBook", new CommandeUpdateLivre() );
        commandes.put( "/deleteBook", new CommandeSupprimerLivre() );
    }

    public static Commande getCommande( String nom ) {
        Commande c = commandes.get( nom );
        return c;
    }

}
