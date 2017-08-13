package dvt.menu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static dvt.devint.ConstantesDevint.*;
import static dvt.menu.ConstantesMenu.*;

import dvt.devint.F1Action;
import dvt.devint.Fenetre;
import dvt.labyrinthe.LabyrintheDeDedale;
import dvt.option.Option;
import dvt.option.TypeOfDifficulty;
import dvt.question.Question;
import dvt.question.TypeOfQuestion;
import dvt.score.Score;

/**
 * Permet de gerer le menu et la fenetre qui contient le menu
 */
public class Menu extends Fenetre {

    private static final long serialVersionUID = 1L;
    private TypeOfDifficulty difficulty;
    private GridBagConstraints c = new GridBagConstraints();

    private JPanel menuPrincipal = new JPanel();
    private List<JButton> listeBoutton;
    private JLabel titleJeu;

    private int gameChoice, countMenu, menuSelected;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;


    /**
     * Le constructeur du menu
     * Permet de construire un Question avec tout les composants
     */
    public Menu() {
        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        difficulty = TypeOfDifficulty.FACILE;
        this.getSIVOX().stop();
        listeBoutton = new ArrayList<JButton>();
        this.getSIVOX().playText(MENU_SON,getSyntheseNiveau());

        GridBagLayout layoutMenu = new GridBagLayout();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets((int) (MARGE_TOP * ratioY), (int) (MARGE_LEFT_RIGHT * ratioX), (int) (MARGE_BOT * ratioY), (int) (MARGE_LEFT_RIGHT * ratioX));
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;
        c.gridwidth = 3;
        menuPrincipal.setLayout(layoutMenu);

        addLabel(TITLE_GAME);

        // les options possibles
        addMenu("Nouvelle partie", new Action(this, 1));
        addMenu("Score", new Action(this, 2));
        addMenu("Option", new Action(this, 3));
        addMenu("Quitter", new Action(this, 4));

        // la gestion des touches directionnelles haut et bas
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
        addControl("SPACE", new SpaceAction(this));
        addControl("F2",new F2Action(this));
        addControl("F1",new F1Action(this));

        this.add(menuPrincipal);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    /**
     * La loop du menu
     */
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            if (gameChoice == 4)
                this.dispose();
            else if (gameChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();
                switch (gameChoice) {
                    case 1:
                        LabyrintheDeDedale laby = new LabyrintheDeDedale(difficulty);
                        laby.changeFont(getFontChoice());
                        laby.changeColor(getColorChoice());
                        laby.loop();
                        changeColor(laby.getColorChoice());
                        changeFont(laby.getFontChoice());
                        break;
                    case 2:
                        Score score = new Score();
                        score.changeFont(getFontChoice());
                        score.changeColor(getColorChoice());
                        score.loop();
                        changeColor(score.getColorChoice());
                        changeFont(score.getFontChoice());
                        break;
                    case 3:
                        Option option = new Option();
                        option.changeFont(getFontChoice());
                        option.changeColor(getColorChoice());
                        option.loop();
                        changeColor(option.getColorChoice());
                        changeFont(option.getFontChoice());
                        difficulty = option.getDifficulty();
                        break;
                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.setVisible(true);
            }

            gameChoice = 0;

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (timeLoop > 0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    /**
     * Permet de gerer le rendu du menu
     */
    private void render() {
        menuPrincipal.setBackground(getBackground());
        this.titleJeu.setFont(getFontTitle());
        this.titleJeu.setForeground(getColorTitle());

        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i == menuSelected % listeBoutton.size()) {
                selectedButton(listeBoutton.get(i));
            } else {
                unselectedButton(listeBoutton.get(i));
            }
        }
    }

    /**
     * Permet d'ajouter du texte dans le menu
     * @param title Le titre du jeu
     */
    public void addLabel(String title) {
        this.titleJeu = new JLabel(title.toUpperCase(), JLabel.CENTER);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(this.titleJeu, c);
    }

    /**
     * Permet d'ajouter un bouton
     * @param title  Le texte sur le bouton
     * @param action L'action que l'on lie au bouton
     */
    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title.toUpperCase());
        button.addActionListener(action);
        unselectedButton(button);
        c.weightx = c.weighty = 1.0;
        c.gridy = countMenu++;
        menuPrincipal.add(button, c);
        listeBoutton.add(button);
    }

    /**
     * Permet de gere l'action lorsque 'lon appuie sur bas
     */
    public void down() {
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == listeBoutton.size() - 1 ? (0)
                : menuSelected + 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void up() {
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(menuSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    public void actionChoice(int choice) {
        menuSelected = choice;
        unselectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));
        menuSelected = menuSelected == 0 ? (listeBoutton.size() - 1)
                : menuSelected - 1;
        selectedButton(listeBoutton.get(menuSelected % listeBoutton.size()));

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setFocusable(false);
        }
    }

    public void space() {
        gameChoice = menuSelected + 1;
    }

    /**
     * Permet de gerer les action lie au choix dans le menu
     * @param choice Le choix que l'on a effectue dans le menu
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.gameChoice = choice;
    }

    public GridBagConstraints getC() {
        return c;
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

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont(int i) {
        super.changeFont(i);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
    }

}
