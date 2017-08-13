package dvt.partie;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Antoine on 08/03/2016.
 */
public class JAction extends AbstractAction{
    private Partie jeu;

    public JAction(Partie jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeu.ouvrirInventaire();
    }
}
