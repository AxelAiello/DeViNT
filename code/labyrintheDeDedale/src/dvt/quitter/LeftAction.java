package dvt.quitter;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche gauche
 * @author Axel Aiello
 */
public class LeftAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Quitter quitter;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "gauche"
     * @param quitter La fenetre que l'on utilise
     */
    public LeftAction(Quitter quitter) {
        this.quitter = quitter;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        quitter.left();
    }

}
