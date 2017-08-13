package dvt.bloquee;

/**
 * Permet de gerer les constantes des salles bloquées
 * @author Axel Aiello
 */
public class ConstantesBloquee {

    public static final int DIM_DIEU = 660;

    public static final int DIM_X = 700;
    public static final int DIM_Y = 300;

    public static final int POSX_OBJET = 55;
    public static final int POSY_OBJET = 705;

    public static final int POSX_DIEU = 85;
    public static final int POSY_DIEU = 15;

    public static final int POSX_INFO = 800;
    public static final int POSY_INFO = 100;

    public static final String WIN = "<html><center>Bravo jeune Héros !!!</center><br />"
            + "Je vois que tu m'as ramené l'objet <br/>"
            + "que je t'ai demandé !<br/>"
            + "Merci pour ton aide, bonne chance<br/>"
            + "pour la suite de ton aventure !</html>";
    public static final String WIN_WITHOUT_HTML= "Bravo jeune Héros !!!"
            + "Je vois que tu m'as ramené l'objet "
            + "que je t'ai demandé !"
            + "Merci pour ton aide, bonne chance"
            + "pour la suite de ton aventure !";

    public static final String LOOSE = "<html><center>Dommage jeune Héros ...</center><br />"
            + "Tu n'as pas encore trouvé l'objet que<br/>"
            + "j'ai perdu dans le labyrinthe.<br/>"
            + "Retourne dans les salles précédentes et<br/>"
            + "reviens vite pour me le rendre, <br/>"
            + "si tu veux continuer ton aventure !</html>";
    public static final String LOOSE_WITHOUT_HTML = "Dommage jeune Héros ..."
            + "Tu n'as pas encore trouvé l'objet que "
            + "j'ai perdu dans le labyrinthe. "
            + "Retourne dans les salles précédentes et "
            + "reviens vite pour me le rendre, "
            + "si tu veux continuer ton aventure !";

    public static final String LION = "<html><center>Bonjour jeune Héros.</center><br />"
            + "J'ai perdu la peau du lion de Némée,<br/>"
            + "l'as tu trouvée dans une des salles <br/>"
            + "que tu as traversée ?</html>";
    public static final String LION_WITHOUT_HTML = "Bonjour jeune Héros."
            + "J'ai perdu la peau du lion de Némée,"
            + "l'as tu trouvée dans une des salles "
            + "que tu as traversée ?";

    public static final String POMME = "<html><center>Bonjour jeune Héros.</center><br />"
            + "J'ai perdu ma jolie pomme d'or,<br/>"
            + "l'as tu trouvée dans une des salles <br/>"
            + "du labyrinthe ?</html>";
    public static final String POMME_WITHOUT_HTML = "Bonjour jeune Héros."
            + "J'ai perdu ma jolie pomme d'or,"
            + "l'as tu trouvée dans une des salles "
            + "du labyrinthe ?";

    public static final String BATON = "<html><center>Yo jeune Héros.</center><br />"
            + "J'ai perdu le baton de mon ami <br/>"
            + "Hermès, l'as tu trouvé dans une <br/>"
            + "des salles que tu as traversée ?</html>";
    public static final String BATON_WITHOUT_HTML = "Yo jeune Héros."
            + "J'ai perdu le baton de mon ami,"
            + "Hermès, l'as tu trouvé dans une"
            + "des salles que tu as traversée ?";

    public static final String EPEE = "<html><center>Halte jeune Héros.</center><br />"
            + "J'ai perdu mon épée, mais j'en ai <br/>"
            + "besoin pour vaincre les monstres. <br/>"
            + "Tu dois m'aider à la trouver <br/>"
            + "dans le labyrinthe !</html>";
    public static final String EPEE_WITHOUT_HTML = "Halte jeune Héros."
            + "J'ai perdu mon épée, mais j'en ai "
            + "besoin pour vaincre les monstres. "
            + "Tu dois m'aider à la trouver "
            + "dans le labyrinthe !";

    public static final String PANDORE = "<html><center>Jeune Héros !!!</center><br />"
            + "Il faut absolument que tu m'aide !!! <br/>"
            + "J'ai perdu ... La boite de pandore ! <br/>"
            + "Il faut que tu m'aide à la trouver <br/>"
            + "avant qu'il ne soit trop tard...</html>";
    public static final String PANDORE_WITHOUT_HTML = "Jeune Héros !!!"
            + "Il faut absolument que tu m'aide !!! "
            + "J'ai perdu ... La boite de pandore ! "
            + "Il faut que tu m'aide à la trouver "
            + "avant qu'il ne soit trop tard...";

    public static final String FLECHE_B = "<html><center>Euh... Bonjour...</center><br />"
            + "Excuse moi de te déranger... Mais <br/>"
            + "j'ai perdu ma flèche d'espoir...<br/>"
            + "Tu ne l'aurais pas vu par hasard ? </html>";
    public static final String FLECHE_B_WITHOUT_HTML = "Euh... Bonjour..."
            + "Excuse moi de te déranger... Mais "
            + "j'ai perdu ma flèche d'espoir..."
            + "Tu ne l'aurais pas vu par hasard ? ";

    public static final String FLECHE_R = "<html><center>Euh... Bonjour...</center><br />"
            + "Excuse moi de te déranger... Mais <br/>"
            + "j'ai perdu ma flèche d'amour...<br/>"
            + "Tu ne l'aurais pas vu par hasard ? </html>";
    public static final String FLECHE_R_WITHOUT_HTML = "Euh... Bonjour..."
            + "Excuse moi de te déranger... Mais "
            + "j'ai perdu ma flèche d'amour..."
            + "Tu ne l'aurais pas vu par hasard ? ";

    public static final String FLECHE_V = "<html><center>Euh... Bonjour...</center><br />"
            + "Excuse moi de te déranger... Mais <br/>"
            + "j'ai perdu ma flèche de jalousie...<br/>"
            + "Tu ne l'aurais pas vu par hasard ? </html>";
    public static final String FLECHE_V_WITHOUT_HTML = "Euh... Bonjour..."
            + "Excuse moi de te déranger... Mais "
            + "j'ai perdu ma flèche de jalousie..."
            + "Tu ne l'aurais pas vu par hasard ? ";

    /**
     * A ne jamais appeler
     */
    private ConstantesBloquee() {
        throw new AssertionError();
    }
}
