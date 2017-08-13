package dvt.quitter;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Axel Aiello
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Quitter quitter;
    private int choice;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param quitter La fenetre ou se trouve le lien entre la touche et l'action
     * @param choice La valeur que l'on souhaite modifie
     */ 
    public Action(Quitter quitter, int choice) {
        this.quitter = quitter;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        quitter.chooseChoice(choice);
    }
}
