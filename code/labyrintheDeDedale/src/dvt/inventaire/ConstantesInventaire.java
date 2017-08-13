package dvt.inventaire;

/**
 * Permet de rassembler l'ensemble des constantes de l'inventaire dans une classe
 * @author Axel Aiello
 * @Date 02/03/2016.
 */
public final class ConstantesInventaire {
    public static final String TITRE = "<html><center>Inventaire</center></html>";
    public static final String TITRE_SANS_HTML = "Inventaire";

    public static final int NOMBRE_X_OBJET = 4;
    public static final int NOMBRE_Y_OBJET = 4;
    public static final int NOMBRE_OBJET = NOMBRE_X_OBJET * NOMBRE_Y_OBJET;

    public static final int POS_X_TITRE = 277;
    public static final int POS_Y_TITRE = 40;

    public static final int POS_X_TEXTE = 50;
    public static final int POS_Y_TEXTE = 550;

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final int MARGE_GAUCHE_EMPLACEMENT = 1000;
    public static final int MARGE_HAUT_EMPLACEMENT = 50;
    public static final int TAILLE_EMPLACEMENT = 207;
    public static final int POS_EMPLACEMENT = 219;

    public static final int TAILLE_X_IMAGE = 700;
    public static final int TAILLE_Y_IMAGE = 300;
    public static final int POS_X_IMAGE = 150;
    public static final int POS_Y_IMAGE = 200;

    public static final int TAILLE = 207;

    public static final String AIDE_INVENTAIRE = "L'inventaire contient tous les objects ,"+
            "que tu as ramassé au cours de ton aventure." +
            "Tu peux te déplacer entre les différents objects ,"+
            "à l'aide des touches directionelles de ton clavier."+
            "Tu verras alors  apparaître l'image de l'object, et sa description.";

    public static final String EPEE_GRAND = "../ressources/images/epeeGrand.png";
    public static final String EPEE_PETIT = "../ressources/images/epeePetit.png";
    public static final String EPEE_AVEC_HTML = "<html>L'épée de Persée :<br/><br/>"
            + "Persée est le fils de Zeus.<br />"
            + "Son épée a été créée par<br/>"
            + "le dieu du feu et des volcans.<br/>"
            + "Grâce à elle, il a tué Méduse !"
            + "</html>";
    public static final String EPEE_SANS_HTML = "L'épée de Persée :"
            + "Persée est le fils de Zeus."
            + "Son épée a été créée par"
            + "le dieu du feu et des volcans."
            + "Grâce à elle, il a tué Méduse !";

    public static final String POMME_GRAND = "../ressources/images/pommeGrand.png";
    public static final String POMME_PETIT = "../ressources/images/pommePetit.png";
    public static final String POMME_AVEC_HTML = "<html>La Pomme d'or :<br/><br/>"
            + "C'est la pomme qu'Hercule a<br/>"
            + "volée dans le jardin des dieux !"
            + "</html>";
    public static final String POMME_SANS_HTML = "La Pomme d'or :"
            + "C'est la pomme qu'Hercule a<br/>"
            + "volée dans le jardin des dieux !";

    public static final String PANDORE_GRAND = "../ressources/images/pandoreGrand.png";
    public static final String PANDORE_PETIT = "../ressources/images/pandorePetit.png";
    public static final String PANDORE_AVEC_HTML = "<html>La boite de Pandore :<br/><br/>"
            + "C'est la boite qui a libéré<br/>"
            + "les 11 malheurs de l'humanité.<br/>"
            + "</html>";
    public static final String PANDORE_SANS_HTML = "La boite de Pandore :"
            + "C'est la boite qui a libéré"
            + "les 11 malheurs de l'humanité.";

    public static final String BATON_GRAND = "../ressources/images/batonGrand.png";
    public static final String BATON_PETIT = "../ressources/images/batonPetit.png";
    public static final String BATON_AVEC_HTML = "<html>Le baton de Hermès :<br/><br/>"
            + "C'est le symbole d'Hermès,<br/>"
            + "le dieu des voleurs et des<br/>"
            + "voyages.<br/>"
            + "</html>";
    public static final String BATON_SANS_HTML = "Le baton de Hermès :"
            + "C'est le symbole d'Hermès,"
            + "le dieu des voleurs et des"
            + "voyages.";

    public static final String FLECHE_R_GRAND = "../ressources/images/flecheRGrand.png";
    public static final String FLECHE_R_PETIT = "../ressources/images/flecheRPetit.png";
    public static final String FLECHE_R_AVEC_HTML = "<html>Une flèche d'amour :<br/><br/>"
            + "C'est une flèche de Cupidon,<br/>"
            + "la flèche rose.<br/>"
            + "</html>";
    public static final String FLECHE_R_SANS_HTML = "Une flèche d'amour :"
            + "C'est une flèche de Cupidon,"
            + "la flèche rose.";

    public static final String FLECHE_B_GRAND = "../ressources/images/flecheBGrand.png";
    public static final String FLECHE_B_PETIT = "../ressources/images/flecheBPetit.png";
    public static final String FLECHE_B_AVEC_HTML = "<html>Une flèche d'espoir :<br/><br/>"
            + "C'est une flèche de Cupidon,<br/>"
            + "la flèche bleue.<br/>"
            + "</html>";
    public static final String FLECHE_B_SANS_HTML = "Une flèche d'espoir :"
            + "C'est une flèche de Cupidon,"
            + "la flèche bleue.";

    public static final String FLECHE_V_GRAND = "../ressources/images/flecheVGrand.png";
    public static final String FLECHE_V_PETIT = "../ressources/images/flecheVPetit.png";
    public static final String FLECHE_V_AVEC_HTML = "<html>Une flèche de jalousie :<br/><br/>"
            + "C'est une flèche de Cupidon,<br/>"
            + "la flèche verte.<br/>"
            + "</html>";
    public static final String FLECHE_V_SANS_HTML = "Une flèche de jalousie :"
            + "C'est une flèche de Cupidon,"
            + "la flèche verte.";

    public static final String CORNE_GRAND = "../ressources/images/corneGrand.png";
    public static final String CORNE_PETIT = "../ressources/images/cornePetit.png";
    public static final String CORNE_AVEC_HTML = "<html>La corne d'abondance :<br/><br/>"
            + "Elle guérit toutes les maladies<br/>"
            + "et apporte le bonheur."
            + "</html>";
    public static final String CORNE_SANS_HTML = "La corne d'abondance :"
            + "Elle guérit toutes les maladies,"
            + "et apporte le bonheur.";

    public static final String LION_GRAND = "../ressources/images/lionGrand.png";
    public static final String LION_PETIT = "../ressources/images/lionPetit.png";
    public static final String LION_AVEC_HTML = "<html>La peau du lion de Némée :<br/><br/>"
            + "Elle guérit les blessures<br/>"
            + "et protège des flèches."
            + "</html>";
    public static final String LION_SANS_HTML = "La peau du lion de Némée :"
            + "Elle guérit les blessures,"
            + "et protège des flèches";

    /**
     * Le constructeur ne sera jamais utilisé
     */
    private ConstantesInventaire() {
        throw new AssertionError();
    }
}
