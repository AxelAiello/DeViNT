package dvt.fond;

import dvt.devint.Fenetre;
import javax.swing.*;
import static dvt.fond.ConstantesFond.OPTIMAL_TIME;

/**
 * Permet de gerer la fenetre de fond
 * @author Axel Aiello
 */
public class Fond extends Fenetre {
    private static final long serialVersionUID = 1L;

    private JPanel monde = new JPanel();

    /**
     * Le constructeur
     * Permet de construire une fenetre de fond
     */
    public Fond() {
        this.add(monde);
        this.setAlwaysOnTop(false);
        this.setVisible(true);
        monde.setBackground(getColorQuestion());
    }

    /**
     * La loop
     */
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            this.dispose();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (timeLoop > 0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }
}
