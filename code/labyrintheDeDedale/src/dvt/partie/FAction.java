package dvt.partie;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Antoine on 09/03/2016.
 */
public class FAction extends AbstractAction {
    private Partie jeu;

    public FAction(Partie jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeu.ouvrirMap();
    }
}
