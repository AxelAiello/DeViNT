package dvt.question;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche droite
 * @author Axel Aiello
 */
public class RightAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Question question;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "DROITE"
     * @param q La fenetre que l'on utilise
     */
    public RightAction(Question q) {
        this.question = q;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        question.right();
    }

}
