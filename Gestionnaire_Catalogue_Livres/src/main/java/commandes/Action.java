package commandes;

public class Action {
    private String  chemin;
    private boolean redirection;

    public Action( String chemin, boolean redirection ) {
        this.chemin = chemin;
        this.redirection = redirection;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin( String chemin ) {
        this.chemin = chemin;
    }

    public boolean estRedirige() {
        return redirection;
    }

    public void setRedirection( boolean redirection ) {
        this.redirection = redirection;
    }

}
