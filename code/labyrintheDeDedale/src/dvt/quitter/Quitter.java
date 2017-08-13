package dvt.quitter;

import dvt.devint.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static dvt.devint.ConstantesDevint.*;
import static dvt.quitter.ConstantesQuitter.*;

/**
 * Permet de gerer la fenetre qui contient la confirmation pour quitter
 * @author Axel Aiello
 */
public class Quitter extends Fenetre {
    private static final long serialVersionUID = 1L;

    private JPanel monde = new JPanel();
    private int countBoutton, bouttonSelected;
    private List<JButton> listeBoutton;
    private int bouttonChoice;
    private boolean confirmation;
    private JLabel texte;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    /**
     * Le constructeur
     * Permet de construire une fenetre de confirmation pour quitter avec tout les composants
     */
    public Quitter() {

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        confirmation = false;
        listeBoutton = new ArrayList<>();

        // les options possibles
        addMenu("NON", new Action(this, 1));
        addMenu("OUI", new Action(this, 2));

        // la gestion des touches directionnelles haut et bas
        addControl("RIGHT", new RightAction(this));
        addControl("LEFT", new LeftAction(this));
        addControl("SPACE", new SpaceAction(this));

        // Insertion du résumé de l'emplacement selectionné
        texte = new JLabel(QUITTER);
        this.getSIVOX().playText(QUITTER_WITHOUT_HTML, getSyntheseNiveau());
        this.texte.setLocation((int) ((LARGEUR_STANDARD - texte.getWidth())/2 * ratioX), (int) (POSY_TEXT * ratioY));
        this.texte.setForeground(getButtonUnselectedForeground());
        this.texte.setFont(getFont());
        this.texte.setVisible(true);
        monde.add(texte);

        this.add(monde);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        monde.setBackground(getColorQuestion());
    }

    /**
     * La loop
     */
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            if (bouttonChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();
                switch (bouttonChoice) {
                    case 1:
                        break;
                    case 2:
                        confirmation = true;
                        break;
                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.setVisible(true);
                this.dispose();
            }

            bouttonChoice = 0;

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
     * Permet de gerer le rendu
     */
    private void render() {
        monde.setBackground(getColorQuestion());

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setBounds((int) ((LARGEUR_STANDARD/2 - MARGE + (i * SPACE)) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 50) * ratioY), (int) (SIZEY * ratioX), (int) (SIZEY * ratioY));
        }
        this.texte.setLocation((int) ((LARGEUR_STANDARD - texte.getWidth())/2 * ratioX), (int) (POSY_TEXT * ratioY));
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i != bouttonSelected) {
                unselectedButton(listeBoutton.get(i));
            }
        }
        selectedButton(listeBoutton.get(bouttonSelected));
        this.texte.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont(int j) {
        super.changeFont(j);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i != bouttonSelected) {
                unselectedButton(listeBoutton.get(i));
            }
        }
        selectedButton(listeBoutton.get(bouttonSelected));
        this.texte.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor() {
        super.changeColor();
        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i != bouttonSelected) {
                unselectedButton(listeBoutton.get(i));
            }
        }
        selectedButton(listeBoutton.get(bouttonSelected));
        this.texte.setForeground(getButtonUnselectedForeground());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor(int j) {
        super.changeColor(j);
        for (int i = 0; i < listeBoutton.size(); i++) {
            if (i != bouttonSelected) {
                unselectedButton(listeBoutton.get(i));
            }
        }
        selectedButton(listeBoutton.get(bouttonSelected));
        this.texte.setForeground(getButtonUnselectedForeground());
    }

    /**
     * Permet d'ajouter un bouton
     * @param title  Le texte sur le bouton
     * @param action L'action que l'on lie au bouton
     */
    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title.toUpperCase());
        button.addActionListener(action);
        button.setBounds((int) ((LARGEUR_STANDARD/2 - MARGE + ((listeBoutton.size()) * SPACE)) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 50) * ratioY), (int) (SIZEX * ratioX), (int) (SIZEY * ratioY));
        if (listeBoutton.size() == 0) {selectedButton(button);}
        else {unselectedButton(button);}
        monde.add(button);
        listeBoutton.add(button);
    }

    /**
     * Permet de gere l'action lorsque l'on appuie sur droit
     */
    public void right() {
        unselectedButton(listeBoutton.get(bouttonSelected));
        if (bouttonSelected == 0) {bouttonSelected++;}
        else {bouttonSelected--;}
        selectedButton(listeBoutton.get(bouttonSelected));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(bouttonSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur gauche
     */
    public void left() {
        unselectedButton(listeBoutton.get(bouttonSelected));
        if (bouttonSelected == 1) {bouttonSelected--;}
        else {bouttonSelected++;}
        selectedButton(listeBoutton.get(bouttonSelected));
        this.getSIVOX().stop();
        this.getSIVOX().playText(listeBoutton.get(bouttonSelected % listeBoutton.size()).getText(), getSyntheseNiveau());
    }

    /**
     *
     */
    public void actionChoice(int choice) {
        bouttonSelected = choice - 1;
        unselectedButton(listeBoutton.get(bouttonSelected));
        bouttonSelected = bouttonSelected == 0 ? (listeBoutton.size() - 1) : bouttonSelected - 1;
        selectedButton(listeBoutton.get(bouttonSelected));

        for (int i = 0; i < listeBoutton.size(); i++) {
            listeBoutton.get(i).setFocusable(false);
        }
    }

    public void space() {
        bouttonChoice = bouttonSelected + 1;
    }

    /**
     * Permet de gerer les action lie au choix
     * @param choice Le choix que l'on a effectue
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.bouttonChoice = choice;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

}
