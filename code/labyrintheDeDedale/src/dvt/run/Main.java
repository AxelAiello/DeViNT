package dvt.run;

    import java.net.MalformedURLException;

    import dvt.fond.Fond;
    import dvt.menu.Menu;

    /**
     * Permet de lancer le jeu
     */
    public final class Main {

    /**
     * Le constructeur qui ne sera jamais utiliser.
     */
    private Main() {
    }

    /**
     * La methode Main
     * @param arg
     * @throws MalformedURLException
     */
    public static void main(String[] arg) throws MalformedURLException {
        Fond fond = new Fond();
        Menu menu = new Menu();
        menu.loop();
        fond.loop();
    }
}
