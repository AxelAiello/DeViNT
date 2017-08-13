package dvt.option;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gérer l'action lors de l'appuie sur la touche bas
 * @author Axel Aiello
 */
public class DownAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Option fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "BAS"
     * @param fenetre La fenetre que l'on utilise
     */
    public DownAction(Option fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.down();
    }

}
