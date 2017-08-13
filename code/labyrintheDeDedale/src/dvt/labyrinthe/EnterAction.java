package dvt.labyrinthe;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private transient LabyrintheDeDedale jeu;

    public EnterAction(LabyrintheDeDedale jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }
}
