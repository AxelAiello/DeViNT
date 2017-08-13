package dvt.map;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Antoine on 14/05/2016.
 */
public class F2Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Mapview fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "F2"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public F2Action(Mapview fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.getSIVOX().stop();
        fenetre.getSIVOX().playText(ConstantesMap.LEGENDE_SANS_HTML,fenetre.getSyntheseNiveau());
    }
}