package dvt.labyrinthe;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "Space"
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient LabyrintheDeDedale jeu1;

    /**
     * L'objet qui sera cree lors de l'appuie sur la touche Space
     * @param jeu1 La fentre ou se trouve le lien entre l'action et la touche space
     */
    public SpaceAction(LabyrintheDeDedale jeu1) {
        this.jeu1 = jeu1;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.jeu1.launch();
    }
}