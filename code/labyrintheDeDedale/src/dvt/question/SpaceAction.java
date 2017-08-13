package dvt.question;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action pour la touche "Space"
 * @author Axel Aiello
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Question question;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param question La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(Question question) {
        this.question = question;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.question.space();
    }
}