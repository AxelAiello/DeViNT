package dvt.inventaire;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche gauche
 * @author Axel Aiello
 */
public class LeftAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Inventaire sac;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "GAUCHE"
     * @param s La fenetre que l'on utilise
     */
    public LeftAction(Inventaire s) {
        this.sac = s;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        sac.left();
    }

}
