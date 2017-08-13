package dvt.fond;

/**
 * Permet de rassembler l'ensemble des constanstes du menu dans une classe
 * @author Axel Aiello
 */
public final class ConstantesFond {

    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesFond() {
        throw new AssertionError();
    }
}
