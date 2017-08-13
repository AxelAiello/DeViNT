package dvt.option;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action pour la touche "Space"
 * @author Axel Aiello
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Option option;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param option La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(Option option) {
        this.option = option;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.option.space();
    }
}