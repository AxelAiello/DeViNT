package dvt.option;

import dvt.partie.ConstantesPartie;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Antoine on 14/05/2016.
 */
public class F2Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Option fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "F2"
     * @param fenetre La fenetre que l'on utilise
     */
    public F2Action(Option fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.getSIVOX().stop();
        fenetre.getSIVOX().playText(ConstantesOption.AIDE_OPTION,fenetre.getSyntheseNiveau());
    }
}
