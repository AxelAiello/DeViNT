package dvt.question;

import dvt.devint.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static dvt.devint.ConstantesDevint.*;
import static dvt.question.ConstantesQuestion.*;

/**
 * Permet de gerer la question et la fenetre qui contient la question
 * @author Axel Aiello
 */
public class Question extends Fenetre {
    private static final long serialVersionUID = 1L;

    private int reponseChoisi;
    private int reponseSelected;

    private List<JButton> listeReponses;

    private JLabel titre;
    private JLabel texteQuestion;
    private JPanel monde = new JPanel();
    private JLabel image;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    private DonneesQuestion donnees;
    private boolean valideReponse;
    private boolean fin;
    private String bonnePorte;

    /**
     * Le constructeur de la question
     * Permet de construire un Question avec tout les composants
     */
    public Question(TypeOfQuestion type, String bonnePorte) {
        fin = false;
        donnees = new DonneesQuestion(type);
        valideReponse = false;
        this.bonnePorte = bonnePorte;

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        // Création et ajout du titre "Iventaire"
        this.titre = new JLabel(TITLE, JLabel.CENTER);
        this.titre.setFont(getFontTitle());
        this.titre.setForeground(getButtonUnselectedForeground());
        this.titre.setLocation((int) (POS_X_TITRE * ratioX), (int) (POS_Y_TITRE * ratioY));
        this.titre.setVisible(true);
        monde.add(titre);
        this.getSIVOX().playText(donnees.getQuestionSansHtml(), SYNTHESE_MAXIMALE);

        // Insertion de l'image de l'emplacement selectionné
        this.image = new JLabel(new ImageIcon(new ImageIcon(donnees.getImage()).getImage().getScaledInstance((int) (DIM * ratioX), (int) (DIM * ratioY), Image.SCALE_DEFAULT)));
        this.image.setLocation((int) (POS_X_IMAGE * ratioX), (int) (POS_Y_IMAGE * ratioY));
        this.image.setVisible(true);
        monde.add(image);

        // Insertion du résumé de l'emplacement selectionné
        this.texteQuestion = new JLabel(donnees.getQuestion(), JLabel.CENTER);
        this.texteQuestion.setFont(getFont());
        this.texteQuestion.setForeground(getButtonUnselectedForeground());
        this.texteQuestion.setLocation((int) (POS_X_TEXTE * ratioX), (int) (POS_Y_TEXTE * ratioY));
        this.texteQuestion.setVisible(true);
        monde.add(texteQuestion);

        // les options possibles
        listeReponses = new ArrayList<JButton>();
        addMenu(donnees.getReponses().get(0), new dvt.question.Action(this, 1));
        addMenu(donnees.getReponses().get(1), new dvt.question.Action(this, 2));
        addMenu(donnees.getReponses().get(2), new dvt.question.Action(this, 3));

        // la gestion des touches directionnelles haut et bas
        addControl("LEFT", new LeftAction(this));
        addControl("RIGHT", new RightAction(this));
        addControl("SPACE", new SpaceAction(this));
        addControl("F2",new F2Action(this));

        this.add(monde);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        monde.setBackground(getColorQuestion());
    }

    /**
     * La loop de la question
     */
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            reponseChoisi = 0;
            if (fin) {
                this.getSIVOX().stop();
                this.dispose();
            }
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
     * Permet de gerer le rendu de la question
     */
    private void render() {
        for (int i = 0; i < listeReponses.size(); i++) {
            listeReponses.get(i).setBounds((int) ((MARGE_REPONSES + ((MARGE_REPONSES + DIM_X_REPONSES) * i)) * ratioX), (int) ((LONGUEUR_STANDARD - (DIM_Y_REPONSES + MARGE_REPONSES)) * ratioY), (int) (DIM_X_REPONSES * ratioX), (int) (DIM_Y_REPONSES * ratioY));
        }
        this.image.setLocation((int) (POS_X_IMAGE * ratioX), (int) (POS_Y_IMAGE * ratioY));
        this.texteQuestion.setLocation((int) (POS_X_TEXTE * ratioX), (int) (POS_Y_TEXTE * ratioY));
        this.titre.setFont(getFontTitle());
        this.titre.setForeground(getButtonUnselectedForeground());
        this.titre.setLocation((int) (POS_X_TITRE * ratioX), (int) (POS_Y_TITRE * ratioY));
        monde.setBackground(getColorQuestion());
    }

    /**ujhy
     * Permet d'ajouter un bouton
     * @param title  Le texte sur le bouton
     * @param action L'action que l'on lie au bouton
     */
    public void addMenu(String title, ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        if (listeReponses.size() == 0) {
            selectedButton(button);
        } else {
            unselectedButton(button);
        }
        listeReponses.add(button);
        listeReponses.get(listeReponses.size() - 1).setBounds((int) (((MARGE_REPONSES + DIM_X_REPONSES) * (listeReponses.size())) * ratioX), (int) ((LONGUEUR_STANDARD - (DIM_Y_REPONSES + MARGE_REPONSES)) * ratioY), (int) (DIM_X_REPONSES * ratioX), (int) (DIM_Y_REPONSES * ratioY));
        monde.add(button);
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        this.titre.setFont(getFontTitle());
        this.texteQuestion.setFont(getFont());
        for (int i = 0; i < listeReponses.size(); i++) {
            if (i != reponseSelected) {
                unselectedButton(listeReponses.get(i));
            }
        }
        selectedButton(listeReponses.get(reponseSelected));
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes
     */
    @Override
    public void changeFont(int j) {
        super.changeFont(j);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        this.titre.setFont(getFontTitle());
        this.texteQuestion.setFont(getFont());
        for (int i = 0; i < listeReponses.size(); i++) {
            if (i != reponseSelected) {
                unselectedButton(listeReponses.get(i));
            }
        }
        selectedButton(listeReponses.get(reponseSelected));
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor() {
        super.changeColor();
        for (int i = 0; i < listeReponses.size(); i++) {
            if (i != reponseSelected) {
                unselectedButton(listeReponses.get(i));
            }
        }
        selectedButton(listeReponses.get(reponseSelected));
        this.titre.setForeground(getButtonUnselectedForeground());
        this.texteQuestion.setForeground(getButtonUnselectedForeground());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor(int j) {
        super.changeColor(j);
        for (int i = 0; i < listeReponses.size(); i++) {
            if (i != reponseSelected) {
                unselectedButton(listeReponses.get(i));
            }
        }
        selectedButton(listeReponses.get(reponseSelected));
        this.titre.setForeground(getButtonUnselectedForeground());
        this.texteQuestion.setForeground(getButtonUnselectedForeground());
    }

    /**
     * Permet de gere l'action lorsque l'on appuie sur gauche
     */
    public void left() {
        unselectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        reponseSelected = reponseSelected == 0 ? (listeReponses.size() - 1) : reponseSelected - 1;
        selectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(donnees.getReponsesSansHtml().get(reponseSelected % listeReponses.size()), SYNTHESE_MAXIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur droite
     */
    public void right() {
        unselectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        reponseSelected = reponseSelected == (listeReponses.size() - 1) ? 0 : reponseSelected + 1;
        selectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        this.getSIVOX().stop();
        this.getSIVOX().playText(donnees.getReponsesSansHtml().get(reponseSelected % listeReponses.size()), SYNTHESE_MAXIMALE);
    }

    /**
     * Permet de gerer les choix
     */
    public void actionChoice(int choice) {
        reponseSelected = choice;
        unselectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        reponseSelected = reponseSelected == 0 ? (listeReponses.size() - 1)
                : reponseSelected - 1;
        selectedButton(listeReponses.get(reponseSelected % listeReponses.size()));
        for (int i = 0; i < listeReponses.size(); i++) {
            listeReponses.get(i).setFocusable(false);
        }
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur espace
     */
    public void space() {
        this.getSIVOX().stop();

        if (texteQuestion.getText().equals(MAUVAISE_REPONSE + "BLEUE" + MAUVAISE_REPONSE_SUITE)
                || texteQuestion.getText().equals(MAUVAISE_REPONSE + "ROUGE" + MAUVAISE_REPONSE_SUITE)
                || texteQuestion.getText().equals(MAUVAISE_REPONSE + "VERTE" + MAUVAISE_REPONSE_SUITE)
                || texteQuestion.getText().equals(BONNE_REPONSE + bonnePorte + BONNE_REPONSE_SUITE)) {
            fin = true;
        }

        for (int i = 0; i < listeReponses.size(); i++) {
            listeReponses.get(i).setVisible(false);
        }

        if (!fin) {
            if (reponseSelected == donnees.getBonneReponse()) {
                texteQuestion.setText(BONNE_REPONSE + bonnePorte + BONNE_REPONSE_SUITE);
                this.getSIVOX().playText(BONNE_REPONSE_SANS_HTML + bonnePorte + BONNE_REPONSE_SUITE_SANS_HTML, getSyntheseNiveau());
                valideReponse = true;
            } else {
                if (bonnePorte.equals("ROUGE")) {
                    texteQuestion.setText(MAUVAISE_REPONSE + "BLEUE" + MAUVAISE_REPONSE_SUITE);
                    this.getSIVOX().playText(MAUVAISE_REPONSE_SANS_HTML + "BLEUE" + MAUVAISE_REPONSE_SUITE_SANS_HTML, getSyntheseNiveau());
                } else if (bonnePorte.equals("BLEUE")) {
                    texteQuestion.setText(MAUVAISE_REPONSE + "VERTE" + MAUVAISE_REPONSE_SUITE);
                    this.getSIVOX().playText(MAUVAISE_REPONSE_SANS_HTML + "VERTE" + MAUVAISE_REPONSE_SUITE_SANS_HTML, getSyntheseNiveau());
                } else if (bonnePorte.equals("VERTE")) {
                    texteQuestion.setText(MAUVAISE_REPONSE + "ROUGE" + MAUVAISE_REPONSE_SUITE);
                    this.getSIVOX().playText(MAUVAISE_REPONSE_SANS_HTML + "ROUGE" + MAUVAISE_REPONSE_SUITE_SANS_HTML, getSyntheseNiveau());
                }
            }
        }
        this.setVisible(true);
    }

    /**
     * Permet de gerer les action lie au choix dans les réponses
     * @param choice Le choix que l'on a effectue dans les réponses
     */
    public void chooseChoice(int choice) {
        actionChoice(choice);
        this.reponseChoisi = choice;
    }

    public boolean getValideReponse(){
        return valideReponse;
    }
}
