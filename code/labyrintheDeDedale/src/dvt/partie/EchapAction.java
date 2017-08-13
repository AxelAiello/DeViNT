package dvt.partie;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Permet de gerer l'action pour la touche "Echap"
 * @author Axel Aiello
 */
public class EchapAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Partie partie;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "Echap"
     * @param partie La fenetre que l'on utilise
     */
    public EchapAction(Partie partie) {
        this.partie = partie;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        partie.quitter();
    }

}
