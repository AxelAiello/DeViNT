package dvt.menu;

/**
 * Permet de rassembler l'ensemble des constanstes du menu dans une classe
 */
public final class ConstantesMenu {

    public static final String TITLE_GAME = "Labyrinthe de Dédale";
    public static final String MENU_SON = "Bienvenue sur le jeu du labyrinthe de dédale.";

    public static final String AIDE_MENU = "Tu peux naviguer dans les différents menu ,grâce aux touches ,HAUT, et ,BAS, de ton clavier. Pour valider ton choix, appuis sur la touche ,ESPACE.";

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final int MARGE_LEFT_RIGHT = 80;
    public static final int MARGE_TOP = 0;
    public static final int MARGE_BOT = 20;
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesMenu() {
        throw new AssertionError();
    }
}
