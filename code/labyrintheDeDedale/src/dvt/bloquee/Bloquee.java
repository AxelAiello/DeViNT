package dvt.bloquee;

import dvt.inventaire.NameOfItem;
import dvt.menu.ConstantesMenu;
import static dvt.bloquee.ConstantesBloquee.*;
import static dvt.devint.ConstantesDevint.*;

import javax.swing.*;
import java.awt.*;

/**
 * Permet de gerer les salles bloquées
 * @author Axel Aiello
 */
public class Bloquee extends dvt.devint.Jeu {

    private JPanel world;
    private int compteurTuto;

    private JLabel info;
    private JLabel imageObjet;
    private JLabel imageDieu;

    private NameOfItem item;
    private String dieu;
    private String objet;
    private String question;
    private String questionWithoutHtml;

    private boolean trouve;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    public Bloquee(NameOfItem item, boolean trouve) {

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        // Calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        this.item = item;
        this.trouve = trouve;
        initi();
        this.setVisible(true);
    }

    /**
     * Méthode qui est appelée avant de réaliser ce qui est contenue dans le constructeur Bloquee() à cause de l'héritage
     * Inutile ici, car on a besoin de réalisé d'abord le constructeur avant la méthode init
     */
    @Override
    public void init() {
        /** Pas besoin d'init **/
    }

    /**
     * Méthode qui initialise l'objet Bloquee
     * Appelé dans le constructeur donc après l'initialisation des attributs
     */
    public void initi() {
        // Récupération des données en fonction du nom de l'item
        data();

        // Création du Panel
        world = new JPanel();
        world.setForeground(getButtonUnselectedForeground());
        world.setBackground(getColorQuestion());

        // Création de l'image de l'objet cherché
        imageObjet = new JLabel(new ImageIcon(new ImageIcon(this.objet).getImage().getScaledInstance((int) (DIM_X * ratioX), (int) (DIM_Y * ratioY), Image.SCALE_DEFAULT)));
        imageObjet.setLocation((int) (POSX_OBJET * ratioX), (int) (POSY_OBJET * ratioY));
        imageObjet.setVisible(true);
        selectedImage(imageObjet);
        world.add(imageObjet);

        // Création de l'image du dieu
        imageDieu = new JLabel(new ImageIcon(new ImageIcon(this.dieu).getImage().getScaledInstance((int) (DIM_DIEU * ratioX), (int) (DIM_DIEU * ratioY), Image.SCALE_DEFAULT)));
        imageDieu.setLocation((int) (POSX_DIEU * ratioX), (int) (POSY_DIEU * ratioY));
        imageDieu.setVisible(true);
        world.add(imageDieu);

        // Question
        info = new JLabel(question, JLabel.CENTER);
        this.getSIVOX().playText(questionWithoutHtml, getSyntheseNiveau());
        info.setFont(getFont());
        info.setLocation((int) (POSX_INFO * ratioX), (int) (POSY_INFO * ratioY));
        info.setVisible(true);
        world.add(info);

        // Actions possibles
        addControl("SPACE", new SpaceAction(this));

        this.setAlwaysOnTop(true);
        this.add(world);
    }

    @Override
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            // Mise à jour des positions des images
            render();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + ConstantesMenu.OPTIMAL_TIME) / 1000000;
                if (timeLoop > 0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    @Override
    public void update() {
        /** Pas besoin d'update **/
    }

    /**
     * Mise à jours des images
     */
    @Override
    public void render() {
        info.setFont(getFont());
        info.setForeground(getButtonUnselectedForeground());
        info.setLocation((int) (POSX_INFO * ratioX), (int) (POSY_INFO * ratioY));

        imageObjet.setLocation((int) (POSX_OBJET * ratioX), (int) (POSY_OBJET * ratioY));
        selectedImage(imageObjet);

        imageDieu.setLocation((int) (POSX_DIEU * ratioX), (int) (POSY_DIEU * ratioY));

        world.setBackground(getColorQuestion());
    }

    /**
     * Récupération des données en fonction de l'objet
     */
    public void data() {
        if (item == NameOfItem.LION) {
            dieu = "../ressources/images/zeusSurNuage.png";
            objet = "../ressources/images/lionGrand.png";
            question = LION;
            questionWithoutHtml = LION_WITHOUT_HTML;
        } else if (item == NameOfItem.POMME) {
            dieu = "../ressources/images/aphroditeSurNuage.png";
            objet = "../ressources/images/pommeGrand.png";
            question = POMME;
            questionWithoutHtml = POMME_WITHOUT_HTML;
        } else if (item == NameOfItem.BATON) {
            dieu = "../ressources/images/appolonSurNuage.png";
            objet = "../ressources/images/batonGrand.png";
            question = BATON;
            questionWithoutHtml = BATON_WITHOUT_HTML;
        } else if (item == NameOfItem.EPEE) {
            dieu = "../ressources/images/soldatSurNuage.png";
            objet = "../ressources/images/epeeGrand.png";
            question = EPEE;
            questionWithoutHtml = EPEE_WITHOUT_HTML;
        } else if (item == NameOfItem.FLECHE_B) {
            dieu = "../ressources/images/cupidonSurNuage.png";
            objet = "../ressources/images/flecheBGrand.png";
            question = FLECHE_B;
            questionWithoutHtml = FLECHE_B_WITHOUT_HTML;
        } else if (item == NameOfItem.FLECHE_R) {
            dieu = "../ressources/images/cupidonSurNuage.png";
            objet = "../ressources/images/flecheRGrand.png";
            question = FLECHE_R;
            questionWithoutHtml = FLECHE_R_WITHOUT_HTML;
        } else if (item == NameOfItem.FLECHE_V) {
            dieu = "../ressources/images/cupidonSurNuage.png";
            objet = "../ressources/images/flecheVGrand.png";
            question = FLECHE_V;
            questionWithoutHtml = FLECHE_V_WITHOUT_HTML;
        } else if (item == NameOfItem.PANDORE) {
            dieu = "../ressources/images/poseidonSurNuage.png";
            objet = "../ressources/images/pandoreGrand.png";
            question = PANDORE;
            questionWithoutHtml = PANDORE_WITHOUT_HTML;
        }

    }

    @Override
    public void reset() {
        /** Pas besoin de reset **/
    }

    /**
     * Permet de gerer les actions lie a l'appuie sur la touche space
     */
    public void launch() {
        switch (compteurTuto) {
            case 0:
                if (trouve) {
                    info.setText(WIN);
                    this.getSIVOX().playText(WIN_WITHOUT_HTML, getSyntheseNiveau());
                } else {
                    info.setText(LOOSE);
                    this.getSIVOX().playText(LOOSE_WITHOUT_HTML, getSyntheseNiveau());
                }
                compteurTuto++;
                break;
            case 1:
                this.getSIVOX().stop();
                this.dispose();
                break;
        }
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
    }
}
