package dvt.itemtrouve;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Antoine Steyer
 * @since 12/04/2016
 */
public class SpaceAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient ItemTrouveView itemtrouve;

    public SpaceAction(ItemTrouveView itemtrouve) {
        this.itemtrouve = itemtrouve;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        itemtrouve.getSIVOX().stop();
        itemtrouve.dispose();
    }
}
