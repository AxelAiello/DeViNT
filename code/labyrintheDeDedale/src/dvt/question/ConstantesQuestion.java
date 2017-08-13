package dvt.question;

/**
 * Permet de rassembler l'ensemble des constanstes des questions dans une classe
 * @author Axel Aiello
 */
public final class ConstantesQuestion {

    public static final String TITLE = "Question";

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final String AIDE_QUESTION = "Pour répondre à la question, tu dois choisir ta réponse parmis les trois proposées." +
            "Tu peux te déplacer, entre les réponses ,avec les touches GAUCHE et DROITE de ton clavier," +
            "et valider ton choix avec la touche ESPACE.";

    public static final String BONNE_REPONSE = "<html><center>Bonne réponse !</center><br/>"
            + "Bravo jeune Héro, pour te<br/>"
            + "récompenser voici un indice : <br/>"
            + "La porte ";
    public static final String BONNE_REPONSE_SUITE = " est la bonne porte<br/>"
            + "pour continuer ton aventure."
            + "</html>";

    public static final String MAUVAISE_REPONSE = "<html><center>Mauvaise réponse !</center><br/>"
            + "Dommage jeune Héro, mais pour <br/>"
            + "t'aider voici un indice : <br/>"
            + "La porte ";
    public static final String MAUVAISE_REPONSE_SUITE = " est une mauvaise porte<br/>"
            + "pour continuer ton aventure."
            + "</html>";

    public static final String BONNE_REPONSE_SANS_HTML = "Bonne réponse !"
            + "Bravo jeune Héro, pour te"
            + "récompenser voici un indice :"
            + "La porte ";
    public static final String BONNE_REPONSE_SUITE_SANS_HTML = " est la bonne porte"
            + "pour continuer ton aventure.";

    public static final String MAUVAISE_REPONSE_SANS_HTML = "Mauvaise réponse !"
            + "Dommage jeune Héro, mais pour"
            + "t'aider voici un indice :"
            + "La porte ";
    public static final String MAUVAISE_REPONSE_SUITE_SANS_HTML = " est une mauvaise porte"
            + "pour continuer ton aventure.";

    public static final int POS_X_TITRE = 200;
    public static final int POS_Y_TITRE = 10;

    public static final int POS_X_IMAGE = 55;
    public static final int POS_Y_IMAGE = 145;
    public static final int DIM = 660;

    public static final int POS_X_TEXTE = 800;
    public static final int POS_Y_TEXTE = 100;

    public static final int MARGE_REPONSES = 30;
    public static final int DIM_X_REPONSES = 600;
    public static final int DIM_Y_REPONSES = 200;

    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesQuestion() {
        throw new AssertionError();
    }
}
