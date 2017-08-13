package dvt.partie;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightAction extends AbstractAction{
    private static final long serialVersionUID = 1L;
    private transient Partie partie;

    public RightAction(Partie partie){this.partie = partie;}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        partie.right();
    }
}
