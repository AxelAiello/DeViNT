package dvt.score;

import static dvt.devint.ConstantesDevint.*;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Cette classe permet de gerer les scores.
 * @author Axel Aiello
 */
public class Score extends dvt.devint.Jeu  {
    ArrayList<PlayerScore> listPlayerScore;
    private static final long serialVersionUID = 1L;

    private JPanel world;
    private JLabel info;

    private String score;
    private double ratioY;
    
    @Override
    public void init() {

        //calcule la différence avec la largeur et la longueur standard
        double longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        // Adapte la taille de la police
        setFont(adapteSizeFont(getFont(), ratioY));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioY));

        world = new JPanel();
        info = new JLabel("", JLabel.CENTER);
        parseJSON();
        info.setFont(getFont());
        info.setVisible(true);
        world.add(info);
        this.add(world);

        addControl("F2",new F2Action(this));

        info.setForeground(getForeground());
        world.setBackground(getBackground());
    }
    
    @Override
    public void update() {
        /** Pas besoin d'update **/
    }

    @Override
    public void render() {
        info.setForeground(getForeground());
        info.setFont(getFont());
        world.setBackground(getBackground());
    }
    
    /**
     * Permet de parcourir le fichier de score et d'afficher les meilleurs scores du jeu
     */
    public void parseJSON() {
        listPlayerScore = new ArrayList<>();

        JSONArray scores = openJSONFile("../ressources/scores.json");

        if(scores != null) {
            for (Object o : scores) {
                JSONObject scorex = (JSONObject) o;
                PlayerScore ps = new PlayerScore(scorex.get("nom").toString(), scorex.get("score").toString());
                listPlayerScore.add(ps);
            }

            Collections.sort(listPlayerScore);

            score = "<html><center><br/>SCORE";
            score += "<br />____________________________________<br />";
            score += "<table>";
            for (int i = 0; i < 10 && i < listPlayerScore.size(); i++) {
                score += "<tr><td>" + (i + 1) + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>" + listPlayerScore.get(i).getName() + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>" + listPlayerScore.get(i).getScore() + "</td></tr>";
            }
            score += "</table></center></html>";
            info.setText(score);
        }
    }
    
    @Override
    public void reset() {
        String tmp = "";
        for(int i=0;i<5 && i<listPlayerScore.size();i++) {
            if(i == 0) {
                tmp += "Le meilleur score est de ";
            }
            if(i == 1) {
                tmp += ". Le second score est de ";
            }
            if(i == 2) {
                tmp += ". Le troisieme score est de ";
            }
            if(i == 3) {
                tmp += ". Le Quatrieme score est de ";
            }
            if(i == 4) {
                tmp += ". Le cinquieme score est de ";
            }
            tmp+=listPlayerScore.get(i).getScore();
        }
        tmp+=".";
        this.getSIVOX().playText(tmp,SYNTHESE_MAXIMALE);
    }
    
    class PlayerScore implements Comparator<Object>,Comparable<Object>{ 
        String name;
        int score;
        
        public PlayerScore(String name,String score) {
            this.name = name;
            this.score = Integer.valueOf(score);
        }
        
        public String getName() {
            return name;
        }
        
        public int getScore() {
            return score;
        }

        @Override
        public int compare(Object arg0, Object arg1) {
            PlayerScore ps1 = (PlayerScore) arg0;
            PlayerScore ps2 = (PlayerScore) arg1;
            if(ps1.getScore() == ps2.getScore()) {
                return 0;
            } else if(ps1.getScore() < ps2.getScore()) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public int compareTo(Object arg0) {
            PlayerScore ps1 = (PlayerScore) arg0;
            if(ps1.getScore() == this.getScore()) {
                return 0;
            } else if(ps1.getScore() < this.getScore()) {
                return -1;
            } else {
                return 1;
            }
        }
        
        
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont() {
        super.changeFont();
        setFont(adapteSizeFont(getFont(), ratioY));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioY));
    }

    /**
     * Permet de conserver l'utilisation de la touche F4 (changement de taille des textes)
     */
    @Override
    public void changeFont(int i) {
        super.changeFont(i);
        setFont(adapteSizeFont(getFont(), ratioY));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioY));
    }

    private JSONArray openJSONFile(String path) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            return new JSONArray(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
