package dvt.partie;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpAction extends AbstractAction{
    private static final long serialVersionUID = 1L;
    private transient Partie partie;

    public UpAction(Partie partie){this.partie = partie;}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        partie.up();
    }
}
