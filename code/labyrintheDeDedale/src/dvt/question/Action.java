package dvt.question;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Axel Aiello
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Question question;
    private int choice;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param question La fenetre ou se trouve le lien entre la touche et l'action
     * @param choice La valeur que l'on souhaite modifie
     */ 
    public Action(Question question, int choice) {
        this.question = question;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        question.chooseChoice(choice);
    }
}
