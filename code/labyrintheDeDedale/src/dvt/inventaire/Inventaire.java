package dvt.inventaire;

import dvt.devint.Fenetre;
import javax.swing.*;

import java.awt.*;

import static dvt.devint.ConstantesDevint.*;
import static dvt.inventaire.ConstantesInventaire.*;

/**
 * Permet de créer un inventaire contenant un titre, les objets avec un texte résumé et une image agrandie
 * @author Axel Aiello
 * @Date 02/03/2016.
 */
public class Inventaire extends Fenetre {
    private static final long serialVersionUID = 1L;
    private JPanel monde = new JPanel();
    private JLabel titre;
    private JLabel texteOfSelectedItem;
    private JLabel image;
    private Emplacement[] emplacements;
    private int inventaireSelected;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    /**
     * Le constructeur de l'inventaire
     * Permet de construire un inventaire avec tous ses composants
     */
    public Inventaire() {
        monde = new JPanel();

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        // Création et ajout du titre "Iventaire"
        titre = new JLabel(TITRE, JLabel.CENTER);
        this.titre.setFont(getFontTitle());
        this.titre.setForeground(getColorTitle());
        this.titre.setLocation(POS_X_TITRE, POS_Y_TITRE);
        this.titre.setVisible(true);
        monde.add(titre);

        // Création et insertion des emplacements
        emplacements = new Emplacement[NOMBRE_OBJET];
        for (int i = 0; i < NOMBRE_OBJET; i++) {
            emplacements[i] = new Emplacement(ratioX, ratioY);
            // On met ici à la bonne place chaque emplacement
            emplacements[i].getImagePetite().setBounds((int) ((MARGE_GAUCHE_EMPLACEMENT * ratioX) + ((i % NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioX))), (int) ((MARGE_HAUT_EMPLACEMENT * ratioY) + ((i / NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioY))), (int) (TAILLE_EMPLACEMENT * ratioX), (int) (TAILLE_EMPLACEMENT * ratioY));
            //emplacements[i].getImagePetite().setSize((int) (emplacements[i].getImagePetite().getWidth() * ratioX),(int) (emplacements[i].getImagePetite().getHeight() * ratioY));
            if (i == inventaireSelected) {
                selectedImage(emplacements[i].getImagePetite());
            } else {
                unselectedImage(emplacements[i].getImagePetite());
            }
            monde.add(emplacements[i].getImagePetite());
        }

        // Création de l'image et du résumé de l'emplacement selectionné
        // Si l'emplacement est vide
        if (emplacements[inventaireSelected].getObjet() == null) {
            texteOfSelectedItem = new JLabel("<html></html>", JLabel.CENTER);
            image = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        }
        // Si il contient un Item
        else {
            texteOfSelectedItem = new JLabel(emplacements[inventaireSelected].getObjet().getTexte(), JLabel.CENTER);
            image = emplacements[inventaireSelected].getImageGrande();
        }

        // Insertion de l'image de l'emplacement selectionné
        this.image.setBounds((int) (POS_X_IMAGE * ratioX), (int) (POS_Y_IMAGE * ratioY), (int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY));
        selectedImage(image);
        this.image.setVisible(true);
        monde.add(image);

        // Insertion du résumé de l'emplacement selectionné
        this.texteOfSelectedItem.setFont(getFont());
        this.texteOfSelectedItem.setForeground(getForeground());
        this.texteOfSelectedItem.setLocation((int) (POS_X_TEXTE * ratioX), (int) (POS_Y_TEXTE * ratioY));
        this.texteOfSelectedItem.setVisible(true);
        monde.add(texteOfSelectedItem);

        // Ajout des contrôles
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
        addControl("LEFT", new LeftAction(this));
        addControl("RIGHT", new RightAction(this));
        addControl("F2", new F2Action(this));

        this.add(monde);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        monde.setBackground(getBackground());
    }

    /**
     * Permet d'ajouter un objet à l'inventaire
     * @param nom, nom de l'objet à insérer
     */
    public void addObject(NameOfItem nom) {
        int i = 0;
        while (emplacements[i].getObjet() != null) {i++;}
        Item o;
        if (nom == NameOfItem.EPEE) {
            o = new Item(EPEE_GRAND, EPEE_PETIT, EPEE_SANS_HTML, EPEE_AVEC_HTML, NameOfItem.EPEE);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(EPEE_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(EPEE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.POMME) {
            o = new Item(POMME_GRAND, POMME_PETIT, POMME_SANS_HTML, POMME_AVEC_HTML, NameOfItem.POMME);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(POMME_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(POMME_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.PANDORE) {
            o = new Item(PANDORE_GRAND, PANDORE_PETIT, PANDORE_SANS_HTML, PANDORE_AVEC_HTML, NameOfItem.PANDORE);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(PANDORE_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(PANDORE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.BATON) {
            o = new Item(BATON_GRAND, BATON_PETIT, BATON_SANS_HTML, BATON_AVEC_HTML, NameOfItem.BATON);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(BATON_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(BATON_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.FLECHE_R) {
            o = new Item(FLECHE_R_GRAND, FLECHE_R_PETIT, FLECHE_R_SANS_HTML, FLECHE_R_AVEC_HTML, NameOfItem.FLECHE_R);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(FLECHE_R_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_R_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.FLECHE_B) {
            o = new Item(FLECHE_B_GRAND, FLECHE_B_PETIT, FLECHE_B_SANS_HTML, FLECHE_B_AVEC_HTML, NameOfItem.FLECHE_B);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(FLECHE_B_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_B_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.FLECHE_V) {
            o = new Item(FLECHE_V_GRAND, FLECHE_V_PETIT, FLECHE_V_SANS_HTML, FLECHE_V_AVEC_HTML, NameOfItem.FLECHE_V);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(FLECHE_V_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(FLECHE_V_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.CORNE) {
            o = new Item(CORNE_GRAND, CORNE_PETIT, CORNE_SANS_HTML, CORNE_AVEC_HTML, NameOfItem.CORNE);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(CORNE_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(CORNE_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        } else if (nom == NameOfItem.LION) {
            o = new Item(LION_GRAND, LION_PETIT, LION_SANS_HTML, LION_AVEC_HTML, NameOfItem.LION);
            if (i == inventaireSelected) {
                texteOfSelectedItem.setText(LION_AVEC_HTML);
                image.setIcon(new ImageIcon(new ImageIcon(LION_GRAND).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        }
        else {
            o = null;
        }
        emplacements[i].setObjet(o);
    }

    /**
     * Permet de supprimer un objet de l'inventaire
     * @param n, la position de l'objet à supprimer
     */
    public void removeObject(int n) {
        if (emplacements[n].getObjet() != null) {
            emplacements[n].setObjet(null);
            if (n == inventaireSelected) {
                texteOfSelectedItem.setText("");
                emplacements[n].setImagePetite();
                emplacements[n].setImageGrande();
                image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
            }
        }
    }

    public void organizeObject() {
        int i = 0;

        while (emplacements[i].getObjet() != null || emplacements[i+1].getObjet() != null) {
            if (emplacements[i].getObjet() == null) {
                addObject(emplacements[i+1].getObjet().getNom());
                removeObject(i+1);
            }
            i++;
        }
    }

    /**
     * La loop de l'inventaire
     */
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

    /**
     * Permet de gerer le rendu de l'inventaire
     */
    private void render() {
        this.image.setBounds((int) (POS_X_IMAGE * ratioX), (int) (POS_Y_IMAGE * ratioY), (int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY));
        this.titre.setLocation((int) (POS_X_TITRE * ratioX), (int) (POS_Y_TITRE * ratioY));
        this.texteOfSelectedItem.setLocation((int) (POS_X_TEXTE * ratioX), (int) (POS_Y_TEXTE * ratioY));

        emplacements[inventaireSelected].getImagePetite().setBounds((int) ((MARGE_GAUCHE_EMPLACEMENT * ratioX) + ((inventaireSelected % NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioX))), (int) ((MARGE_HAUT_EMPLACEMENT * ratioY) + ((inventaireSelected / NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioY))), (int) (TAILLE_EMPLACEMENT * ratioX), (int) (TAILLE_EMPLACEMENT * ratioY));
        for (int i = 0; i < NOMBRE_OBJET; i++) {
            if (i != inventaireSelected) {
                emplacements[i].getImagePetite().setBounds((int) ((MARGE_GAUCHE_EMPLACEMENT * ratioX) + ((i % NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioX))), (int) ((MARGE_HAUT_EMPLACEMENT * ratioY) + ((i / NOMBRE_X_OBJET) * (POS_EMPLACEMENT * ratioY))), (int) (TAILLE_EMPLACEMENT * ratioX), (int) (TAILLE_EMPLACEMENT * ratioY));
            }
        }

        monde.setBackground(getBackground());
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
        this.texteOfSelectedItem.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes
     */
    @Override
    public void changeFont(int i) {
        super.changeFont(i);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        this.titre.setFont(getFontTitle());
        this.texteOfSelectedItem.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor() {
        super.changeColor();
        for (int i = 0; i < NOMBRE_OBJET; i++) {
            if (i != inventaireSelected) {
                unselectedImage(emplacements[i].getImagePetite());
            }
        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        selectedImage(image);
        this.titre.setForeground(getColorTitle());
        this.texteOfSelectedItem.setForeground(getForeground());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor(int j) {
        super.changeColor(j);
        for (int i = 0; i < NOMBRE_OBJET; i++) {
            if (i != inventaireSelected) {
                unselectedImage(emplacements[i].getImagePetite());
            }
        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        selectedImage(image);
        this.titre.setForeground(getColorTitle());
        this.texteOfSelectedItem.setForeground(getForeground());
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur bas
     */
    public void down() {
        unselectedImage(emplacements[inventaireSelected].getImagePetite());

        if (inventaireSelected >= NOMBRE_OBJET - NOMBRE_X_OBJET) {
            inventaireSelected -= NOMBRE_OBJET - NOMBRE_X_OBJET;
        } else {inventaireSelected += NOMBRE_X_OBJET;}

        if (emplacements[inventaireSelected].getObjet() == null) {
            texteOfSelectedItem.setText("");
            image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

        } else {
            texteOfSelectedItem.setText(emplacements[inventaireSelected].getObjet().getTexte());
            image.setIcon(new ImageIcon(new ImageIcon(emplacements[inventaireSelected].getObjet().getCheminImageGrande()).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        this.getSIVOX().stop();
        if (emplacements[inventaireSelected].getObjet() != null) {
            this.getSIVOX().playText(emplacements[inventaireSelected].getObjet().getTexteSansHtml(), getSyntheseNiveau());
        }
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur haut
     */
    public void up() {
        unselectedImage(emplacements[inventaireSelected].getImagePetite());

        if (inventaireSelected <= NOMBRE_X_OBJET - 1) {
            inventaireSelected += NOMBRE_OBJET - NOMBRE_X_OBJET;
        } else {inventaireSelected -= NOMBRE_X_OBJET;}

        if (emplacements[inventaireSelected].getObjet() == null) {
            texteOfSelectedItem.setText("");
            image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

        } else {
            texteOfSelectedItem.setText(emplacements[inventaireSelected].getObjet().getTexte());
            image.setIcon(new ImageIcon(new ImageIcon(emplacements[inventaireSelected].getObjet().getCheminImageGrande()).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        this.getSIVOX().stop();
        if (emplacements[inventaireSelected].getObjet() != null) {
            this.getSIVOX().playText(emplacements[inventaireSelected].getObjet().getTexteSansHtml(), getSyntheseNiveau());
        }
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur gauche
     */
    public void left() {
        unselectedImage(emplacements[inventaireSelected].getImagePetite());

        if (inventaireSelected % NOMBRE_X_OBJET == 0) {
            inventaireSelected += NOMBRE_X_OBJET - 1;
        } else {inventaireSelected--;}

        if (emplacements[inventaireSelected].getObjet() == null) {
            texteOfSelectedItem.setText("");
            image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

        } else {
            texteOfSelectedItem.setText(emplacements[inventaireSelected].getObjet().getTexte());
            image.setIcon(new ImageIcon(new ImageIcon(emplacements[inventaireSelected].getObjet().getCheminImageGrande()).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        this.getSIVOX().stop();
        if (emplacements[inventaireSelected].getObjet() != null) {
            this.getSIVOX().playText(emplacements[inventaireSelected].getObjet().getTexteSansHtml(), getSyntheseNiveau());
        }
    }

    /**
     * Permet de gérer l'action lorsque l'on appuie sur droite
     */
    public void right() {
        unselectedImage(emplacements[inventaireSelected].getImagePetite());

        if (inventaireSelected % NOMBRE_X_OBJET == NOMBRE_X_OBJET - 1) {
            inventaireSelected -= NOMBRE_X_OBJET - 1;
        } else {inventaireSelected++;}

        if (emplacements[inventaireSelected].getObjet() == null) {
            texteOfSelectedItem.setText("");
            image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

        } else {
            texteOfSelectedItem.setText(emplacements[inventaireSelected].getObjet().getTexte());
            image.setIcon(new ImageIcon(new ImageIcon(emplacements[inventaireSelected].getObjet().getCheminImageGrande()).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

        }
        selectedImage(emplacements[inventaireSelected].getImagePetite());
        this.getSIVOX().stop();
        if (emplacements[inventaireSelected].getObjet() != null) {
            this.getSIVOX().playText(emplacements[inventaireSelected].getObjet().getTexteSansHtml(), getSyntheseNiveau());
        }
    }

}
