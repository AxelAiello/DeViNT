package dvt.option;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Axel Aiello
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Option option;
    private int choice;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param option l'option que l'on souhaite appliquer
     * @param choice La valeur que l'on souhaite modifie
     */
    public Action(Option option, int choice) {
        this.option = option;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        option.chooseChoice(choice);
    }
}
