package dvt.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action pour la touche "Space"
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Menu menu;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param menu La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.menu.space();
    }
}