package dvt.formulairenom;

import javax.swing.*;

import java.awt.*;

import static dvt.devint.ConstantesDevint.LARGEUR_STANDARD;
import static dvt.devint.ConstantesDevint.LONGUEUR_STANDARD;
import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

public class FormulaireNom extends dvt.devint.Jeu {
    private JPanel world;
    private JLabel info;
    private JTextField txtFld;
    private JPanel txtFldPanel;
    private JLabel aide;
    private String nom;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    @Override
    public void init() {

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        world = new JPanel();
        world.setBackground(getBackground());
        world.setLayout(null);

        info = new JLabel("Comment t'appelles-tu?", JLabel.CENTER);
        this.getSIVOX().playText("Comment t'appelles-tu?", SYNTHESE_MAXIMALE);
        info.setFont(getFontTitle());
        info.setVisible(true);

        aide = new JLabel("Appuie sur la touche ESPACE pour continuer.", JLabel.CENTER);
        this.getSIVOX().playText("Appuie sur la touche espace pour continuer.");
        aide.setFont(getFont());
        aide.setVisible(true);

        txtFld = new JTextField();
        txtFld.setFont(getFontTitle());
        txtFld.setVisible(true);
        txtFldPanel = new JPanel(null);

        txtFldPanel.add(txtFld);
        txtFldPanel.setBackground(getButtonUnselectedForeground() );

        world.add(info);
        world.add(txtFldPanel);
        world.add(aide);

        this.add(world);
    }

    @Override
    public void update() {
        if(txtFld.getText().length() > 0)
            if(txtFld.getText().charAt(txtFld.getText().length()-1) == ' ') {
                this.nom = txtFld.getText();
                this.dispose();
                this.getSIVOX().stop();
            }
    }

    @Override
    public void render() {
        info.setBounds(0, this.getHeight()/2 - 400, this.getWidth(), 200);

        info.setFont(getFontTitle());


        info.setForeground(getButtonUnselectedForeground());
        info.setBackground(getColorQuestion());

        txtFld.setBounds(50,50,500,200);
        txtFld.setFont(getFontTitle());
        txtFld.setHorizontalAlignment(JTextField.CENTER);

        txtFld.setForeground(getButtonUnselectedForeground());
        txtFld.setBackground(getColorQuestion());

        txtFldPanel.setBounds(this.getWidth()/2-300,this.getHeight()/2-200,600,300);

        aide.setBounds(0,this.getHeight()/2+200,this.getWidth(),200);
        aide.setFont(getFont());

        aide.setForeground(getButtonUnselectedForeground());
        aide.setBackground(getColorQuestion());

        txtFld.requestFocusInWindow();


        world.setBackground(getColorQuestion());

        info.setSize((int)getSize().getWidth(),200);
    }

    @Override
    public void reset() {

    }

    public String getNom(){
        if(nom.equals(" ") || nom.equals("") || nom == null)
            return "[  ]";
        if(nom.length()>20) {
            String tmp = nom.substring(0,20);
            return tmp;
        }
        return nom;
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont(int i) {
        super.changeFont(i);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
    }

}
