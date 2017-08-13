package dvt.quitter;

/**
 * Permet de rassembler l'ensemble des constanstes du menu dans une classe
 * @author Axel Aiello
 */
public final class ConstantesQuitter {

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;

    public static final String QUITTER = "<html><center><br/><br/>Etes vous certain de vouloir quitter ?<br/>" +
            "Attention, la partie ne sera pas enregistrée.</center></html>";
    public static final String QUITTER_WITHOUT_HTML ="Etes vous certain de vouloir quitter ? " +
            "Attention, la partie ne sera pas enregistrée.";

    public static final int POSY_TEXT = 100;
    public static final int SIZEX = 300;
    public static final int SIZEY = 300;
    public static final int MARGE = 350;
    public static final int SPACE = 400;

    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesQuitter() {
        throw new AssertionError();
    }
}
