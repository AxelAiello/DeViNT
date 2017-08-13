package dvt.itemtrouve;

import dvt.devint.EchapAction;
import dvt.devint.Fenetre;
import dvt.inventaire.NameOfItem;

import javax.swing.*;

import java.awt.*;

import static dvt.devint.ConstantesDevint.*;

import static dvt.inventaire.ConstantesInventaire.*;
import static dvt.itemtrouve.ConstantesItemTrouve.*;

/**
 * Classe pour afficher un item trouvé dans une salle
 * @author Antoine Steyer
 * @since 08/04/2016
 */
public class ItemTrouveView extends Fenetre {

    private JLabel info;
    private JLabel image;
    private JPanel world;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;


    public ItemTrouveView(NameOfItem name) {
        world = new JPanel();

        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        info = new JLabel("<html></html>", JLabel.RIGHT);
        this.info.setFont(getFont());
        this.info.setForeground(getForeground());
        this.info.setLocation((int) (POS_X_DESCRIPTION * ratioX), (int) (POS_Y_DESCRIPTION * ratioY));
        this.info.setVisible(true);

        image = new JLabel(new ImageIcon("../ressources/images/videGrand.png"), JLabel.LEFT);
        this.image.setBounds((int) (POS_X_OBJET * ratioX), (int) (POS_Y_OBJET * ratioY),
               (int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY));
        this.image.setVisible(true);

        choisirObjet(name);

        world.add(image);
        world.add(info);

        addControl("SPACE", new SpaceAction(this));

        this.add(world);
        this.setVisible(true);

        world.setBackground(getBackground());

    }

    public void choisirObjet(NameOfItem name) {
        switch (name) {
            case EPEE:
                info.setText(FIND_EPEE);
                this.getSIVOX().playText(FIND_EPEE_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(EPEE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case POMME:
                info.setText(FIND_POMME);
                this.getSIVOX().playText(FIND_POMME_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(POMME_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case PANDORE:
                info.setText(FIND_PANDORE);
                this.getSIVOX().playText(FIND_PANDORE_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(PANDORE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case BATON:
                info.setText(FIND_BATON);
                this.getSIVOX().playText(FIND_BATON_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(BATON_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case FLECHE_R:
                info.setText(FIND_FLECHE_R);
                this.getSIVOX().playText(FIND_FLECHE_R_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_R_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case FLECHE_B:
                info.setText(FIND_FLECHE_B);
                this.getSIVOX().playText(FIND_FLECHE_B_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_B_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case FLECHE_V:
                info.setText(FIND_FLECHE_V);
                this.getSIVOX().playText(FIND_FLECHE_V_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_V_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case CORNE:
                info.setText(FIND_CORNE);
                this.getSIVOX().playText(FIND_CORNE_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(CORNE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
            case LION:
                info.setText(FIND_LION);
                this.getSIVOX().playText(FIND_LION_SANS_HTML, getSyntheseNiveau());
                image.setIcon(new ImageIcon(new ImageIcon(LION_GRAND).getImage().getScaledInstance((int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY), Image.SCALE_DEFAULT)));
                break;
        }
    }

    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();

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

    public void render() {
        info.setLocation((int) (POS_X_DESCRIPTION * ratioX), (int) (POS_Y_DESCRIPTION * ratioY));
        info.setForeground(getForeground());
        image.setBounds((int) (POS_X_OBJET * ratioX), (int) (POS_Y_OBJET * ratioY),
                (int) (TAILLE_X_OBJET * ratioX), (int) (TAILLE_Y_OBJET * ratioY));
        world.setBackground(getBackground());
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
