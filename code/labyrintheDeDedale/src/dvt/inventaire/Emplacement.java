package dvt.inventaire;

import static dvt.inventaire.ConstantesInventaire.*;
import javax.swing.*;
import java.awt.*;

/**
 * Permet de créer un emplacement contenant un item, et ses deux représentations graphiques
 * @author Axel Aiello
 * @Date 02/03/2016.
 */
public class Emplacement {
    private Item objet;
    private JLabel imageGrande;
    private JLabel imagePetite;

    private double ratioX;
    private double ratioY;

    public Emplacement(double ratioX, double ratioY) {
        this.ratioX = ratioX;
        this.ratioY = ratioY;
        this.objet = null;
        this.imagePetite = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/videPetit.png").getImage().getScaledInstance((int) (TAILLE * ratioX), (int) (TAILLE * ratioY), Image.SCALE_DEFAULT)));
        this.imageGrande = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));

    }

    public Item getObjet() {
        return objet;
    }

    public JLabel getImageGrande() {
        return imageGrande;
    }

    public JLabel getImagePetite() {
        return imagePetite;
    }

    public void setImagePetite() {
        if (objet != null) {
            this.imagePetite.setIcon(new ImageIcon(new ImageIcon(objet.getCheminImagePetite()).getImage().getScaledInstance((int) (TAILLE * ratioX), (int) (TAILLE * ratioY), Image.SCALE_DEFAULT)));
        } else {
            this.imagePetite.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videPetit.png").getImage().getScaledInstance((int) (TAILLE * ratioX), (int) (TAILLE * ratioY), Image.SCALE_DEFAULT)));
        }
    }

    public void setObjet(Item objet) {
        this.objet = objet;
        setImageGrande();
        setImagePetite();
    }

    public void setImageGrande() {
        if (objet != null) {
            this.imageGrande.setIcon(new ImageIcon(new ImageIcon(objet.getCheminImageGrande()).getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        } else {
            this.imageGrande.setIcon(new ImageIcon(new ImageIcon("../ressources/images/videGrand.png").getImage().getScaledInstance((int) (TAILLE_X_IMAGE * ratioX), (int) (TAILLE_Y_IMAGE * ratioY), Image.SCALE_DEFAULT)));
        }
    }

}
