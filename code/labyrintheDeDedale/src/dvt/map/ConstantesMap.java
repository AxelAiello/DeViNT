package dvt.map;

/**
 * Permet de rassembler l'ensemble des constantes de la map dans une classe
 * @author Thoas Moras
 */

public final class ConstantesMap {
    public static final String TITRE = "<html><center>Carte</center></html>";
    public static final String TITRE_SANS_HTML = "Carte";
    public static final String LEGENDE = "<html>Légende</html>";
    public static final String DEPART = "<html>Départ</html>";
    public static final String POSITION = "<html>Position</html>";
    public static final String BLOQUE = "<html>Bloquée</html>";
    public static final String ARRIVE = "<html>Arrivée</html>";

    public static final String LEGENDE_SANS_HTML = "Voici la Carte." +
            "La case verte, indique ton emplacement de départ. " +
            "La case bleu, indique ta position actuelle. " +
            "Les cases rouges sont vérouillées, à moins que tu est en ta possession l'object qui les déverouille." +
            "La case jaune, corresponds à ta destination final.";

    public static final int POS_X_TITRE = 277;
    public static final int POS_Y_TITRE = 40;

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final int POS_X_INITIAL = 850;
    public static final int POS_Y_INITIAL = 550;


    /**
     * Le constructeur ne sera jamais utilisé
     */
    private ConstantesMap() {
        throw new AssertionError();
    }
}
