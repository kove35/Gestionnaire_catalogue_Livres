package beans;

import java.util.Date;

public class Livre implements Cloneable {
    private int    id;
    private String title;
    private String description;
    private Double price;
    private Date   pubDate;
    private String auteur;

    public Livre( int id, String title, String description, Double price, Date pubDate, String gauteur ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubDate = pubDate;
    }

    public Livre() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice( Double price ) {
        this.price = price;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate( Date pubDate ) {
        this.pubDate = pubDate;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur( String auteur ) {
        this.auteur = auteur;
    }

    public Livre cloneMe() {
        try {
            return (Livre) super.clone();
        } catch ( CloneNotSupportedException e ) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Livre [title=" + title + ", description=" + description + ", price=" + price + ", pubDate=" + pubDate
                + ", id=" + id + ", auteur=" + auteur + "]";
    }

}
