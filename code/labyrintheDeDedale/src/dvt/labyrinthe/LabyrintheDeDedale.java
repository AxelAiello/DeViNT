package dvt.labyrinthe;

import dvt.formulairenom.FormulaireNom;
import dvt.menu.ConstantesMenu;
import dvt.option.TypeOfDifficulty;
import dvt.partie.Partie;

import javax.swing.*;
import java.awt.*;

import static dvt.devint.ConstantesDevint.*;
import static dvt.labyrinthe.ConstantesJeu.*;

public class LabyrintheDeDedale extends dvt.devint.Jeu {

    private JPanel world;
    private boolean play, win;
    private int compteurTuto, score;
    private TypeOfDifficulty difficulty;
    private JLabel info;
    private JLabel image;
    private JLabel imageM;
    private String nomJoueur = "";

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    public LabyrintheDeDedale(TypeOfDifficulty difficulty) {
        this.difficulty = difficulty;
        initi();
        this.setVisible(true);
    }

    @Override
    public void init() {
    }

    public void initi() {
        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);
        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        win = false;

        world = new JPanel();
        world.setForeground(getButtonUnselectedForeground());
        world.setBackground(getColorQuestion());

        // Insertion de l'image de l'emplacement selectionné
        image = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/zeusSurNuage.png").getImage().getScaledInstance((int) (DIM * ratioX), (int) (DIM * ratioY), Image.SCALE_DEFAULT)));

        image.setLocation((int) (POSX_IMAGE * ratioX), (int) (POSY_IMAGE * ratioY));
        image.setVisible(true);
        world.add(image);

        // Insertion de l'image de l'emplacement selectionné
        imageM = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/Méchant.png").getImage().getScaledInstance((int) (600 * ratioX), (int) (420 * ratioY), Image.SCALE_DEFAULT)));
        imageM.setBounds((int) (1270 * ratioX), (int) (610 * ratioY), (int) (600 * ratioX), (int) (420 * ratioY));
        imageM.setVisible(true);
        world.add(imageM);

        info = new JLabel(INTRO, JLabel.CENTER);
        this.getSIVOX().playText(INTRO_WITHOUT_HTML, getSyntheseNiveau());
        info.setFont(getFont());
        info.setLocation((int) (POSX_INFO * ratioX), (int) (POSY_INFO * ratioY));
        info.setVisible(true);
        world.add(info);

        addControl("ENTER", new EnterAction(this));
        addControl("SPACE", new SpaceAction(this));
        this.setAlwaysOnTop(true);
        this.add(world);
    }

    @Override
    public void loop() {
        long lastLoopTime, timeLoop;

        // initialisation des valeurs

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            // mise � jour des informations
            update();

            // r�-affichage � chaque tour
            // optimisation possible : afficher seulement quand certaines informations ont chang�
            // n�cessite de lisser l'affichage d'un temps t � t+delta
            render();

            if (play) {
                this.getSIVOX().stop();
                this.setVisible(false);
                FormulaireNom formNom = new FormulaireNom();
                formNom.changeFont(getFontChoice());
                formNom.changeColor(getColorChoice());
                formNom.loop();
                changeColor(formNom.getColorChoice());
                changeFont(formNom.getFontChoice());
                nomJoueur = formNom.getNom();
                Partie partie = new Partie(difficulty,nomJoueur);
                partie.changeFont(getFontChoice());
                partie.changeColor(getColorChoice());
                partie.loop();
                changeColor(partie.getColorChoice());
                changeFont(partie.getFontChoice());
                this.setVisible(true);
                play = false;
                if (partie.isWin()) {
                    image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/Fin-Win.png").getImage().getScaledInstance((int) (1500 * ratioX), (int) (560 * ratioY), Image.SCALE_DEFAULT)));
                    info.setText(WIN);
                    this.getSIVOX().playText(WIN_WITHOUT_HTML, getSyntheseNiveau());
                    win = true;
                } else {
                    image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/hadesSurNuage.png").getImage().getScaledInstance((int) (DIM * ratioX), (int) (DIM * ratioY), Image.SCALE_DEFAULT)));
                    info.setText(LOOSE);
                    this.getSIVOX().playText(LOOSE_WITHOUT_HTML, getSyntheseNiveau());
                }
            }

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
    }

    @Override
    public void render() {
        if (win) {
            this.info.setLocation((int) (450 * ratioX), (int) (600 * ratioY));
            this.image.setBounds((int) (250 * ratioX), (int) (50 * ratioY), (int) (1500 * ratioX), (int) (560 * ratioY));
        } else {
            this.info.setLocation((int) (POSX_INFO * ratioX), (int) (POSY_INFO * ratioY));
            this.image.setLocation((int) (POSX_IMAGE * ratioX), (int) (POSY_IMAGE * ratioY));
        }
        if (play) {
            info.setVisible(false);
            image.setVisible(false);
        } else {
            info.setVisible(true);
            image.setVisible(true);
        }

        imageM.setBounds((int) (1270 * ratioX), (int) (610 * ratioY), (int) (600 * ratioX), (int) (420 * ratioY));
        info.setFont(getFont());
        info.setForeground(getButtonUnselectedForeground());
        world.setBackground(getColorQuestion());


    }

    @Override
    public void reset() {
    }

    /**
     * Permet de gerer les actions lie a l'appuie sur la touche entrée
     */
    public void launch() {
        if (!play) {
            playTuto();
        } else {
            this.play = true;
        }
    }

    public void playTuto() {
        switch (compteurTuto) {
            case 0:
                imageM.setVisible(false);
                info.setText(INTRO2);
                this.getSIVOX().playText(INTRO2_WITHOUT_HTML, getSyntheseNiveau());
                compteurTuto++;
                break;
            case 1:
                this.play = true;
                compteurTuto++;
                break;
            case 2:
                this.getSIVOX().stop();
                this.dispose();
                break;
        }
    }

    public int getScore() {
        return this.score;
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
