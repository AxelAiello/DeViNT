package dvt.consigne;

import javax.swing.*;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;
import static dvt.consigne.ConstantesConsignes.*;

/**
 * Permet de créer les consignes de jeu
 * @author Antoine Steyer
 */
public class Consignes extends dvt.devint.Jeu {

    private JPanel world;
    private JLabel info;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());
        world.setLayout(null);

        info = new JLabel(CONSIGNE, JLabel.CENTER);
        this.getSIVOX().playText(CONSIGNE_WITHOUT_HTML, SYNTHESE_MAXIMALE);
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);

        this.add(world);
    }

    @Override
    public void update() {
        /** Pas besoin d'update **/
    }

    @Override
    public void render() {

        info.setBounds(0, 0, this.getWidth(), this.getHeight());
        info.setFont(getFont());
        info.setForeground(getForeground());
        info.setBackground(getBackground());
        world.setBackground(getBackground());
    }

    @Override
    public void reset() {
        /** Pas besoin de reset **/
    }
}
