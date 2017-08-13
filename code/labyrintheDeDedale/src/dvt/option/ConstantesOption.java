package dvt.option;

/**
 * Permet de rassembler l'ensemble des constanstes d'option dans une classe
 * @author Axel Aiello
 */
public final class ConstantesOption {
    public static final String QUESTION = "Difficulté";

    public static final String AIDE_OPTION = "Les difficultés changent les énigmes et la taille des cartes, durant le jeu."+
            "Sélectionne la difficulté que tu veux, avec les touches HAUT et BAS de ton clavier,"+
            "valide ton choix avec la touche ESPACE.";

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final int MARGE_LEFT_RIGHT = 650;
    public static final int MARGE_TOP = 0;
    public static final int MARGE_BOT = 50;

    /**
     * Le constructeur ne sera jamais utilisé
     */
    private ConstantesOption() {
        throw new AssertionError();
    }
}
