package dvt.devint;

/**
 * L'ensemble des constantes pour le projet entier
 * @author Justal Kevin - SI5
 */
public final class ConstantesDevint {
    public static final String FONT_TYPE_DEFAULT = "Comic Sans MS";

    public static final int NBR_SYNTHESE_NIVEAU = 3;
    public static final int SYNTHESE_MAXIMALE = 2;
    public static final int SYNTHESE_MOYENNE = 1;
    //public static final int SYNTHESE_MINIMALE = 0;

    public static final double LARGEUR_STANDARD = 1920;
    public static final double LONGUEUR_STANDARD = 1080;
    public static final double LARGEUR_STANDARD_MAP = 1366;
    public static final double LONGUEUR_STANDARD_MAP = 768;

    /**
     * Constructeur prive qui ne dois jamais etre utilise.
     */
    private ConstantesDevint() {
        throw new AssertionError();
    }
}
