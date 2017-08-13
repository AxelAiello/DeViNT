package dvt.option;

import dvt.devint.Fenetre;
import static dvt.devint.ConstantesDevint.*;
import static dvt.option.ConstantesOption.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Permet de gerer les optins et la fenetre qui contient le menu
 * @author Axel Aiello
 */
public class Option extends Fenetre {
    private static final long serialVersionUID = 1L;
    private static TypeOfDifficulty difficulty;

    private GridBagConstraints c = new GridBagConstraints();
    private JPanel optionPrincipal = new JPanel();
    private int countOption, answerSelected;

    private List<JButton> listeBoutton;
    private JLabel question;
    private int difficultyChoice;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    /**
     * Le constructeur des options
     * Permet de construire des option avec tout les composants
     */
    public Option() {

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        this.getSIVOX().playText(QUESTION, getSyntheseNiveau());
        difficulty = TypeOfDifficulty.FACILE;
        listeBoutton = new ArrayList<>();

        GridBagLayout layoutMenu = new GridBagLayout();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets((int) (MARGE_TOP * ratioY), (int) (MARGE_LEFT_RIGHT *ratioX), (int) (MARGE_BOT * ratioY), (int) (MARGE_LEFT_RIGHT *ratioX));
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;
        c.gridwidth = 3;
        optionPrincipal.setLayout(layoutMenu);

        addLabel(QUESTION);

        // les options possibles
        addMenu("Facile", new Action(this, 1));
        addMenu("Difficile", new Action(this, 2));
        addMenu("Extrème", new Action(this, 3));
        addMenu("Quitter", new Action(this, 4));

        // la gestion des touches directionnelles haut et bas
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
        addControl("SPACE", new SpaceAction(this));
        addControl("F2",new F2Action(this));

        this.add(optionPrincipal);
        this.setVisible(true);
    }

    /**
     * La loop des options
     */
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();

            if (difficultyChoice == 4)
                this.dispose();
            else if (difficultyChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();
                switch (difficultyChoice) {
                    case 1:
                        difficulty = TypeOfDifficulty.FACILE;
                        break;
                    case 2:
                        difficulty = TypeOfDifficulty.DIFFICILE;
                        break;
                    case 3:
                        difficulty = TypeOfDifficulty.EXTREME;
                        break;
                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.dispose();
            }

            difficultyChoice = 0;

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
     * Permet de gerer le rendu des options
     */
    private void render() {
        optionPrincipal.setBackground(getBackground());
        this.question.setFont(getFontTitle());
        this.question.setForeground(getColorTitle());

        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i == answerSelected % listeBoutton.size()) {
                selectedButton(listeBoutton.get(i));
            } else {
                unselectedButton(listeBoutton.get(i));
            }
        }
    }

    /**
     * Permet d'ajouter du texte dans les options
     * @param title Le titre
     */
    public void addLabel(String title) {
        this.question = new JLabel(title.toUpperCase(), JLabel.CENTER);
        c.weightx = c.weighty = 1.0;
        c.gridy = countOption++;
        optionPrincipal.add(this.question, c);
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
        c.gridy = countOption++;
        optionPrincipal.add(button, c);
        listeBoutton.add(button);
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur bas
     */
    public void down() {
        unselectedButton(listeBoutton.get(answerSelected++ % listeBoutton.size()));
        selectedButton(listeBoutton.get(answerSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(answerSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur haut
     */
    public void up() {
        unselectedButton(listeBoutton.get(answerSelected % listeBoutton.size()));
        answerSelected = answerSelected == 0 ? (listeBoutton.size() - 1) : answerSelected - 1;
        selectedButton(listeBoutton.get(answerSelected % listeBoutton.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(answerSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     * Permet de choisir l'action
     */
    public void actionChoice(int choice) {
        answerSelected = choice;
        unselectedButton(listeBoutton.get(answerSelected % listeBoutton.size()));
        answerSelected = answerSelected == 0 ? (listeBoutton.size() - 1) : answerSelected - 1;
        selectedButton(listeBoutton.get(answerSelected % listeBoutton.size()));
        this.getSIVOX().playText(listeBoutton.get(answerSelected % listeBoutton.size()).getText(), getSyntheseNiveau());

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setFocusable(false);
        }
    }

    /**
     * Permet de gérer les actions liées au choix dans les options
     * @param choice Le choix que l'on a effectué dans le menu
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.difficultyChoice = choice;
    }

    public void space() {
        difficultyChoice = answerSelected + 1;
    }

    public TypeOfDifficulty getDifficulty() {return difficulty;}

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
