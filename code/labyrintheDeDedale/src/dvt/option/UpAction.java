package dvt.option;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gérer l'action lors de l'appuie sur l'appuie sur la touche haut
 * @author Axel Aiello
 */
public class UpAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Option fenetre;

    /**
     * Le constructeur appelé lors de l'appuie sur la touche haut
     * @param fenetre La fenetre que l'on utilise
     */
    public UpAction(Option fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.up();
    }

}
