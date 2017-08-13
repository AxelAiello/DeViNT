package dvt.labyrinthe;

public class ConstantesJeu {
    public static final int POSX_INFO = 800;
    public static final int POSY_INFO = 100;
    public static final int POSX_IMAGE = 55;
    public static final int POSY_IMAGE = 145;
    public static final int DIM = 660;

    public static final String INTRO = "<html>" + "<center>Bonjour jeune Héros, le pays est menacé.</center><br />"
            + "En effet Hadès, le Dieu de la Mort a volé<br />"
            + "la Corne d'Abondance et sème la famine<br />"
            + "et la maladie à travers tout le pays.</html>";
    public static final String INTRO_WITHOUT_HTML = "Bonjour jeune Héros, le pays est menacé."
            + "En effet Hadès, le Dieu de la Mort"
            + "a volé la Corne d'Abondance et sème la famine"
            + "et la maladie à travers tout le pays.";
    public static final String INTRO2 = "<html>Il faut que tu nous aides à retrouver<br />"
            + "la Corne d'Abondance, en allant la chercher<br />"
            + "dans le Labyrinthe de Dédale !<br /><br />"
            + "<center>Avance Champion et bon courage !</center></html>";
    public static final String INTRO2_WITHOUT_HTML = "Il faut que tu nous aides à retrouver"
            + "la Corne d'Abondance, en allant la chercher"
            + "dans le Labyrinthe de Dédale !"
            + "Avance Champion et bon courage !";
    public static final String WIN = "<html><center>Bravo jeune Héros !!!</center><br />"
            + "Tu as trouvé la corne d'abondance et tu es <br/>"
            + "sortie du labyrinthe !<br/>"
            + "Grâce à toi nous avons pu vaincre la<br/>"
            + "malédiction d'Hadès et sauver ton village !</html>";
    public static final String WIN_WITHOUT_HTML= "Bravo jeune Héros !!!"
            + "Tu as trouvé la corne d'abondance et tu es"
            + "sortie du labyrinthe !"
            + "Grâce à toi nous avons pu vaincre la"
            + "malédiction d'Hadès et sauver ton village !" +
            " ";
    public static final String LOOSE = "<html><center>Dommage jeune Héros ...</center><br />"
            + "Tu n'as pas encore pu sortir du labyrinthe,<br/>"
            + "avec la corne d'Abondannce.<br/>"
            + "Reviens vite pour vaincre la<br/>"
            + "malédiction d'Hadès et sauver ton village !</html>";
    public static final String LOOSE_WITHOUT_HTML = "Dommage jeune Héros ..."
            + "Tu n'as pas encore pu sortir du labyrinthe,"
            + "avec la corne d'Abondannce."
            + "Reviens vite pour vaincre la"
            + "malédiction d'Hadès et sauver ton village !";
    public static final String CONTROLE = "<html>" + "<center>RACCOURCIS CLAVIER :<br /><br />"
            + "Touches Directionnelles :<br />"
            + "HAUT , BAS - Monter et Descendre dans les menus<br />"
            + "GAUCHE , DROITE - Défilement à Gauche et à Droite<br /><br />"
            + "J - Ouvrir la carte<br />"
            + "F - Ouvrir l'inventaire<br /><br />"
            + "ESPACE - Valider une action<br />"
            + "ECHAP - Quitter le jeu</center></html>";
    public static final String CONTROLE_WITHOUT_HTML = "RACCOURCIS CLAVIER."
            + "Touches Directionnelles :"
            + "HAUT , BAS - Monter et Descendre dans les menus."
            + "GAUCHE , DROITE - Défilement à Gauche et à Droite."
            + "J - Ouvrir la carte."
            + "F - Ouvrir l'inventaire."
            + "ESPACE - Valider une action."
            + "ECHAP - Quitter le jeu.";
    public static final String BUT_DU_JEU = "Trouvez la corne d'abondance cachée dans le labyrinthe,"
            + "et rendez la à Zeus pour sauver votre village.";
    public static final String CONSIGNE = "<html>" + "<center>BUT DU JEU :<br /><br />"
            + "Trouvez la corne d'abondance cachée dans le labyrinthe <br/>"
            + "et rendez la à Zeus pour sauver votre village !</center><br/>"
            + "<center>OBJECTIFS FACULTATIFS :</center><br/>"
            + "- Trouvez la pomme d'or,<br/>"
            + "- Trouvez l'épée de Persée,<br/>"
            + "- Trouvez les  3 flèches de Cupidon,<br/>"
            + "- Trouvez le baton d'Hermès,<br/>"
            + "- Trouvez la peau du lion de Némée,<br/>"
            + "- Trouvez la boite de Pandore.<br/>"
            + "</html>";
    public static final String CONSIGNE_WITHOUT_HTML = "BUT DU JEU."
            + "Trouvez la corne d'abondance cachée dans le labyrinthe,"
            + "et rendez la à Zeus pour sauver votre village."
            + "OBJECTIFS FACULTATIFS."
            + "- Trouvez la pomme d'or."
            + "- Trouvez l'épée de Persée."
            + "- Trouvez les  3 flèches de Cupidon."
            + "- Trouvez le baton d'Hermès."
            + "- Trouvez la peau du lion de Némée."
            + "- Trouvez la boite de Pandore.";


    private ConstantesJeu() {
        throw new AssertionError();
    }
}
