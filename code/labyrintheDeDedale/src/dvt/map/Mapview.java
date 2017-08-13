package dvt.map;

import dvt.partie.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import static dvt.devint.ConstantesDevint.*;
import static dvt.map.ConstantesMap.*;

/**
 * @author Thomas Moras
 */
public class Mapview extends dvt.devint.Jeu {

    private JPanel mainPanel, textPanel, mapPanel, ligne1, ligne2, ligne3, ligne4, ligne5;

    private JLabel titre, legend, depart, position, bloque, arrive;

    private Map m;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;
    private Direction orientationActuelle;

    public Mapview(Map map, Direction orientationActuelle) {
        this.m = map;
        this.orientationActuelle = orientationActuelle;
        this.setVisible(true);
        initi();
    }

    @Override
    public void init() {
    }

    public void initi() {
        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD_MAP);
        ratioY = (longueurEcran / LONGUEUR_STANDARD_MAP);

        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        mainPanel = new JPanel();
        textPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        mapPanel = drawMap();

        // Ajout du titre sur le panel principal
        titre = new JLabel(TITRE, JLabel.CENTER);
        titre.setFont(getFontTitle());
        titre.setForeground(getColorTitle());
        titre.setLocation(POS_X_TITRE,POS_Y_TITRE);
        titre.setVisible(true);

        legend = new JLabel(LEGENDE);
        depart = new JLabel(DEPART);
        position = new JLabel(POSITION);
        bloque = new JLabel(BLOQUE);
        arrive = new JLabel(ARRIVE);

        this.titre.setForeground(getButtonUnselectedForeground());
        this.legend.setForeground(getButtonUnselectedForeground());
        this.depart.setForeground(getButtonUnselectedForeground());
        this.position.setForeground(getButtonUnselectedForeground());
        this.bloque.setForeground(getButtonUnselectedForeground());
        this.arrive.setForeground(getButtonUnselectedForeground());

        // Un panel correspond a un ligne de la legend, qui contient 2 Jlabel image + text

        ligne1 = new JPanel();
        ligne2 = new JPanel();
        ligne3 = new JPanel();
        ligne4 = new JPanel();
        ligne5 = new JPanel();

        ligne1.setBackground(getBackground());
        ligne2.setBackground(getBackground());
        ligne3.setBackground(getBackground());
        ligne4.setBackground(getBackground());
        ligne5.setBackground(getBackground());
        // Affichage de la légende en fonction de la taille de l'écran

        //ligne5.add(ligne5.add(drawBoussole()));
        ligne5.add(new JLabel(new ImageIcon(new ImageIcon("../ressources/images/boussole1.png").getImage().getScaledInstance((int) (180 * ratioX),(int)(180 * ratioY), Image.SCALE_DEFAULT))));

        if((longueurEcran < 800) && (largeurEcran < 1400)) {
            legend.setFont(new Font("Serif", Font.BOLD, 40));
            depart.setFont(new Font("Serif", Font.PLAIN, 30));
            position.setFont(new Font("Serif", Font.PLAIN, 30));
            bloque.setFont(new Font("Serif", Font.PLAIN, 30));
            arrive.setFont(new Font("Serif", Font.PLAIN, 30));
            setLegend("../ressources/images/startCase.png", 60, 60, ligne1, depart);
            setLegend("../ressources/images/currentCase.png", 60, 60, ligne2, position);
            setLegend("../ressources/images/blockedCase.png", 60, 60, ligne3, bloque);
            setLegend("../ressources/images/endCase.png", 60, 60, ligne4, arrive);

            textPanel.add(titre);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(legend);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(ligne1);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 20), new Dimension(32, 0)));
            textPanel.add(ligne2);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 20), new Dimension(32, 0)));
            textPanel.add(ligne3);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 20), new Dimension(32, 0)));
            textPanel.add(ligne4);
            textPanel.add(Box.createVerticalGlue());
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 50), new Dimension(32, 0)));
            textPanel.add(ligne5);
        }
        else {
            legend.setFont(new Font("Serif", Font.BOLD, 70));
            depart.setFont(new Font("Serif", Font.PLAIN, 44));
            position.setFont(new Font("Serif", Font.PLAIN, 44));
            bloque.setFont(new Font("Serif", Font.PLAIN, 44));
            arrive.setFont(new Font("Serif", Font.PLAIN, 44));
            setLegend("../ressources/images/startCase.png", 80, 80, ligne1, depart);
            setLegend("../ressources/images/currentCase.png", 80, 80, ligne2, position);
            setLegend("../ressources/images/blockedCase.png", 80, 80, ligne3, bloque);
            setLegend("../ressources/images/endCase.png", 80, 80, ligne4, arrive);

            textPanel.add(titre);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 50), new Dimension(32, 0)));
            textPanel.add(legend);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 40), new Dimension(32, 0)));
            textPanel.add(ligne1);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(ligne2);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(ligne3);
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(ligne4);
            textPanel.add(Box.createVerticalGlue());
            textPanel.add(new Box.Filler(new Dimension(4,0), new Dimension(0, 30), new Dimension(32, 0)));
            textPanel.add(ligne5);
        }

        //drawMap();

        addControl("F2",new F2Action(this));

        mainPanel.add(textPanel);
        mainPanel.add(mapPanel);
        this.add(mainPanel);
        mapPanel.setBackground(getBackground());
        mainPanel.setBackground(getBackground());
        textPanel.setBackground(getBackground());
    }

    @Override
    public void update() {


    }

    @Override
    public void render() {

        mapPanel.setBackground(getBackground());
        mainPanel.setBackground(getBackground());
        textPanel.setBackground(getBackground());

        //mainPanel.revalidate();
        //mainPanel.repaint();
        /// Why ?????
        ligne5.revalidate();
        ligne5.repaint();

    }
    private JPanel drawMap() {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 15;
        gbc.ipady = 15;

        Component newRoom;
        // Initialisation de la map
        Set cles = m.getRoomList().keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            Object cle = it.next();
            Room room = m.getRoomList().get(cle);
            if(room.isVisite()) {
                newRoom = new JLabel(room.getRoomImage());
            } else {
                newRoom = Box.createRigidArea(new Dimension(room.getRoomImage().getIconWidth(), room.getRoomImage().getIconHeight()));
            }

            //if (!room.isVisite()) {newRoom.setVisible(false);}
            int valueX = (int)(POS_X_INITIAL * ratioX);
            int valueY = (int)(POS_Y_INITIAL * ratioY);

            gbc.gridx = POS_X_INITIAL + room.getC().getX()*80;
            gbc.gridy = POS_Y_INITIAL - room.getC().getY()*80;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.ipadx = 1;
            gbc.ipady = 1;
            mapPanel.add(newRoom,gbc);

            if (room.getBehind() != null) {
                if(room.isVisite() || room.getBehind().isVisite()) {
                    JLabel lien = new JLabel(new ImageIcon("../ressources/images/lienHorizontale.png"));
                    //change la position
                    //if (!room.isVisite()) {lien.setVisible(false);}
                    gbc.gridy = POS_Y_INITIAL - room.getC().getY() * 80 + 1;

                    mapPanel.add(lien, gbc);
                }
            }
            if (room.getRight() != null) {
                if(room.isVisite() || room.getRight().isVisite()) {
                    JLabel lien = new JLabel(new ImageIcon("../ressources/images/lienVerticale.png"));
                    //change la position
                    gbc.gridy = POS_Y_INITIAL - room.getC().getY() * 80;
                    gbc.gridx = POS_X_INITIAL + room.getC().getX() * 80 + 1;
                    //if (!room.isVisite()) {lien.setVisible(false);}

                    mapPanel.add(lien, gbc);
                }
            }

        }
        return  mapPanel;
    }

    private JLabel drawBoussole() {

        ImageIcon bousole;
        switch (orientationActuelle) {
            case UP:
                bousole = new ImageIcon("../ressources/images/boussole1.png");
                break;
            case RIGHT:
                bousole = new ImageIcon("../ressources/images/boussole2.png");
                break;
            case DOWN:
                bousole = new ImageIcon("../ressources/images/boussole3.png");
                break;
            case LEFT:
                bousole = new ImageIcon("../ressources/images/boussole4.png");
                break;
            default:
                System.out.println("Error direction");
                bousole = null;
        }

        if((longueurEcran < 800) && (largeurEcran < 1400))
            return new JLabel(new ImageIcon(bousole.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        else
            return new JLabel(new ImageIcon(bousole.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
    }

    @Override
    public void reset() {

    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        this.titre.setFont(getFontTitle());
        this.legend.setFont(getFont());
        this.depart.setFont(getFont());
        this.position.setFont(getFont());
        this.bloque.setFont(getFont());
        this.arrive.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes
     */
    @Override
    public void changeFont(int i) {
        super.changeFont(i);
        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));
        this.titre.setFont(getFontTitle());
        this.legend.setFont(getFont());
        this.depart.setFont(getFont());
        this.position.setFont(getFont());
        this.bloque.setFont(getFont());
        this.arrive.setFont(getFont());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor() {
        super.changeColor();
        this.titre.setForeground(getButtonUnselectedForeground());
        this.legend.setForeground(getButtonUnselectedForeground());
        this.depart.setForeground(getButtonUnselectedForeground());
        this.position.setForeground(getButtonUnselectedForeground());
        this.bloque.setForeground(getButtonUnselectedForeground());
        this.arrive.setForeground(getButtonUnselectedForeground());
        mapPanel.setBackground(getBackground());
        mainPanel.setBackground(getBackground());
        textPanel.setBackground(getBackground());
        ligne1.setBackground(getBackground());
        ligne2.setBackground(getBackground());
        ligne3.setBackground(getBackground());
        ligne4.setBackground(getBackground());
        ligne5.setBackground(getBackground());
    }

    /**
     * Permet de conserver l'utilisation de la touche F3 (changement de couleur)
     */
    @Override
    public void changeColor(int i) {
        super.changeColor(i);
        this.titre.setForeground(getButtonUnselectedForeground());
        this.legend.setForeground(getButtonUnselectedForeground());
        this.depart.setForeground(getButtonUnselectedForeground());
        this.position.setForeground(getButtonUnselectedForeground());
        this.bloque.setForeground(getButtonUnselectedForeground());
        this.arrive.setForeground(getButtonUnselectedForeground());
        mapPanel.setBackground(getBackground());
        mainPanel.setBackground(getBackground());
        textPanel.setBackground(getBackground());
        ligne1.setBackground(getBackground());
        ligne2.setBackground(getBackground());
        ligne3.setBackground(getBackground());
        ligne4.setBackground(getBackground());
        ligne5.setBackground(getBackground());
    }

    public void setLegend(String pathCase,int height, int weight, JPanel ligne, JLabel text) {
        ImageIcon caseLegend;
        ligne.add(new Box.Filler(new Dimension(4,0), new Dimension(100, 0), new Dimension(32, 0))); // Gère les espaces
        ligne.setLayout(new BoxLayout(ligne, BoxLayout.X_AXIS));
        caseLegend = new ImageIcon(new ImageIcon(pathCase).getImage().getScaledInstance(height, weight, Image.SCALE_DEFAULT));
        ligne.add(new JLabel(caseLegend));
        ligne.add(new Box.Filler(new Dimension(4,0), new Dimension(20, 0), new Dimension(32, 0)));
        ligne.add(text);
    }
}
