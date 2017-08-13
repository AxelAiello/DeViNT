package dvt.bloquee;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action pour la touche "Space"
 * @author Axel Aiello
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Bloquee salle;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param salle La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(Bloquee salle) {
        this.salle = salle;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.salle.launch();
    }
}