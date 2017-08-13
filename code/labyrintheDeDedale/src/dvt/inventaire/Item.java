package dvt.inventaire;

/**
 * Permet de créer un objet item, représentant un item avec un nom, une descprition
 * et un lien pour sa représentation
 * @author Axel Aiello
 * @Date 02/03/2016.
 */
public class Item {

    private String cheminImagePetite;
    private String cheminImageGrande;
    private NameOfItem nom;
    private String texteSansHtml;
    private String texte;

    public Item(String cheminImageGrande, String cheminImagePetite, String texteSansHtml, String texte, NameOfItem nom) {
        this.cheminImagePetite = cheminImagePetite;
        this.cheminImageGrande = cheminImageGrande;
        this.texteSansHtml = texteSansHtml;
        this.texte = texte;
        this.nom = nom;
    }

    public NameOfItem getNom() {
        return nom;
    }

    public String getCheminImagePetite() {
        return cheminImagePetite;
    }

    public String getCheminImageGrande() {
        return cheminImageGrande;
    }

    public String getTexteSansHtml() {
        return texteSansHtml;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
