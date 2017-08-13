package dvt.controle;

import javax.swing.*;

import static dvt.controle.ConstantesControle.CONTROLE;
import static dvt.controle.ConstantesControle.CONTROLE_WITHOUT_HTML;
import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * Les contrôles de jeu
 * @author Antoine Steyer
 */
public class Controles extends dvt.devint.Jeu {

    private JPanel world;
    private JLabel info;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());
        world.setLayout(null);

        info = new JLabel(CONTROLE, JLabel.CENTER);
        this.getSIVOX().playText(CONTROLE_WITHOUT_HTML, SYNTHESE_MAXIMALE);
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
        info.setBackground(getBackground());
        info.setForeground(getForeground());
        world.setBackground(getBackground());
    }

    @Override
    public void reset() {
        /** Pas besoin de reset **/
    }
}
