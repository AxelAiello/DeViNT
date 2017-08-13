package dvt.partie;

import dvt.bloquee.Bloquee;
import dvt.devint.*;
import dvt.partie.F2Action;
import dvt.inventaire.Inventaire;
import dvt.inventaire.NameOfItem;
import dvt.itemtrouve.ItemTrouveView;
import dvt.map.Map;
import dvt.map.Mapview;
import dvt.map.Room;
import dvt.map.RoomState;
import dvt.menu.ConstantesMenu;
import dvt.option.TypeOfDifficulty;
import dvt.question.Question;
import dvt.quitter.Quitter;
import org.json.JSONArray;
import org.json.JSONObject;

import static dvt.devint.ConstantesDevint.LARGEUR_STANDARD;
import static dvt.devint.ConstantesDevint.LONGUEUR_STANDARD;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Partie extends dvt.devint.Jeu {

    private JPanel mainPanel;
    private JLabel alertLabel;
    private JLabel iconF;
    private JLabel iconB;
    private JLabel iconR;
    private JLabel iconL;
    private JLabel image;

    private Player player;
    private String selectedDirection = "";
    private int doorsChoiceNumber;
    private Map map;

    private double largeurEcran;
    private double longueurEcran;
    private double ratioX;
    private double ratioY;

    private int score = 100;
    private String nomJoueur;
    private JSONArray scores;

    Direction orientationActuelle;

    private boolean openInventaire, openMap, quitter, bloquee, corne;
    private Room roomBloquee;
    private TypeOfDifficulty difficulty;

    public Partie(TypeOfDifficulty difficulty, String nomJoueur) {
        this.difficulty = difficulty;
        this.nomJoueur = nomJoueur;
        initi();
        this.setVisible(true);
    }

    @Override
    public void init() {
    }

    public void initi() {
        scores = openJSONFile("../ressources/scores.json");

        bloquee = false;
        quitter = false;
        corne = false;
        roomBloquee = null;
        orientationActuelle = Direction.UP;

        // Permet de connaitre la taille de l'écran en pixel
        longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        //calcule la différence avec la largeur et la longueur standard
        ratioX = (largeurEcran / LARGEUR_STANDARD);
        ratioY = (longueurEcran / LONGUEUR_STANDARD);

        setFont(adapteSizeFont(getFont(), ratioX));
        setFontTitle(adapteSizeFont(getFontTitle(), ratioX));

        // Iniialisation des données du jeu
        map = new Map(difficulty);
        player = new Player("Joueur 1",map.getStartRoom());
        doorsChoiceNumber = 7;

        // Création de l'affichage
        mainPanel = new MonPanel("../ressources/images/salle.PNG", ratioX, ratioY);

        mainPanel.setLayout(null);

        alertLabel = new JLabel();
        alertLabel.setVisible(true);
        alertLabel.setBounds((int) ((LARGEUR_STANDARD/4) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 220) * ratioY), (int) ((LARGEUR_STANDARD/2 + 200) * ratioX), (int) (ratioY * 150));
        mainPanel.add(alertLabel);
        this.setAlwaysOnTop(true);

        this.image = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/boussole1.png").getImage().getScaledInstance((int)(250 * ratioX), (int)(250 * ratioY), Image.SCALE_DEFAULT)));
        this.image.setBounds((int) ((LARGEUR_STANDARD/16) * ratioX), (int)  ((LONGUEUR_STANDARD/2 + 240) * ratioY), (int) (250 * ratioX), (int) (250 * ratioY));
        this.image.setVisible(true);
        mainPanel.add(image);

        iconF = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/flecheFront.png").getImage().getScaledInstance((int) (130 * ratioX), (int) (160 * ratioY), Image.SCALE_DEFAULT)));
        iconF.setBounds((int) ((LARGEUR_STANDARD/2 - 55) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 60) * ratioY), (int) (130 * ratioX), (int) (160 * ratioY));

        iconB = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/flecheBehind.png").getImage().getScaledInstance((int) (130 * ratioX), (int) (160 * ratioY), Image.SCALE_DEFAULT)));
        iconB.setBounds((int) ((LARGEUR_STANDARD/2 - 55) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 380) * ratioY), (int) (130 * ratioX), (int) (160 * ratioY));

        iconR = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/flecheRight.png").getImage().getScaledInstance((int) (125 * ratioX), (int) (165 * ratioY), Image.SCALE_DEFAULT)));
        iconR.setBounds((int) ((3*(LARGEUR_STANDARD/4) + 100) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 150) * ratioY), (int) (125 * ratioX), (int) (165 * ratioY));

        iconL = new JLabel(new ImageIcon(new ImageIcon("../ressources/images/flecheLeft.png").getImage().getScaledInstance((int) (125 * ratioX), (int) (165 * ratioY), Image.SCALE_DEFAULT)));
        iconL.setBounds((int) ((LARGEUR_STANDARD/4 - 200) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 150) * ratioY), (int) (125 * ratioX), (int) (165 * ratioY));

        iconF.setVisible(false);
        iconB.setVisible(false);
        iconR.setVisible(false);
        iconL.setVisible(false);

        mainPanel.add(iconF);
        mainPanel.add(iconB);
        mainPanel.add(iconR);
        mainPanel.add(iconL);

        add(this.mainPanel);

        // Ajout des contrôles
        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
        addControl("LEFT", new LeftAction(this));
        addControl("RIGHT", new RightAction(this));
        addControl("J", new JAction(this));
        addControl("F", new FAction(this));
        addControl("SPACE", new SpaceAction(this));

        addControl("ESCAPE", new EchapAction(this));
        addControl("F2",new F2Action(this));

        // Initialisation des booléens pour la carte et l'inventaire
        openInventaire = false;
        openMap = false;
    }

    @Override
    public void loop() {
        long lastLoopTime, timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            update();

            // Si devinnette
            if (player.getCurrentRoom().getQuestion() != null && !player.getCurrentRoom().isQuestionResolue()) {
                String goodDoorAvantRotation = player.getCurrentRoom().getGoodDoor();
                String goodDoor = goodDoorAvantRotation;


                switch(orientationActuelle){
                    case RIGHT:
                        if(goodDoorAvantRotation.equals("VERTE"))
                            goodDoor = "DE DERRIERE";
                        if(goodDoorAvantRotation.equals("BLEUE"))
                            goodDoor = "VERTE";
                        if(goodDoorAvantRotation.equals("ROUGE"))
                            goodDoor = "BLEUE";
                        if(goodDoorAvantRotation.equals("DE DERRIERE"))
                            goodDoor = "ROUGE";
                        break;
                    case DOWN:
                        if(goodDoorAvantRotation.equals("VERTE"))
                            goodDoor = "ROUGE";
                        if(goodDoorAvantRotation.equals("BLEUE"))
                            goodDoor = "DE DERRIERE";
                        if(goodDoorAvantRotation.equals("ROUGE"))
                            goodDoor = "VERTE";
                        if(goodDoorAvantRotation.equals("DE DERRIERE"))
                            goodDoor = "BLEUE";
                        break;
                    case LEFT:
                        if(goodDoorAvantRotation.equals("VERTE"))
                            goodDoor = "BLEUE";
                        if(goodDoorAvantRotation.equals("BLEUE"))
                            goodDoor = "ROUGE";
                        if(goodDoorAvantRotation.equals("ROUGE"))
                            goodDoor = "DE DERRIERE";
                        if(goodDoorAvantRotation.equals("DE DERRIERE"))
                            goodDoor = "VERTE";
                        break;
                }

                Question devinette = new Question(player.getCurrentRoom().getQuestion(), goodDoor/*player.getCurrentRoom().getGoodDoor()*/);
                this.setVisible(false);
                this.getSIVOX().stop();

                devinette.changeFont(getFontChoice());
                devinette.changeColor(getColorChoice());
                devinette.loop();
                changeColor(devinette.getColorChoice());
                changeFont(devinette.getFontChoice());
                this.getSIVOX().stop();
                //this.getSIVOX().playWav("../ressources/sons/open_door_1.wav");
                this.getSIVOX().stop();
                answerScoreUpdate(devinette.getValideReponse());
                this.setVisible(true);
                player.getCurrentRoom().setQuestionResolue(true);
            }

            if (quitter) {
                this.getSIVOX().stop();
                quitter = false;
                Quitter confirmation = new Quitter();
                confirmation.changeFont(getFontChoice());
                confirmation.changeColor(getColorChoice());
                confirmation.loop();
                changeColor(confirmation.getColorChoice());
                changeFont(confirmation.getFontChoice());
                this.getSIVOX().stop();
                if (confirmation.isConfirmation()) {this.dispose(); }
            }

            if (bloquee) {
                this.getSIVOX().stop();
                bloquee = false;
                Bloquee verification = new Bloquee(roomBloquee.getPerdu(), player.isContaintInventaire(roomBloquee.getPerdu()));
                verification.changeFont(getFontChoice());
                verification.changeColor(getColorChoice());
                verification.loop();
                changeColor(verification.getColorChoice());
                changeFont(verification.getFontChoice());
                if (player.isContaintInventaire(roomBloquee.getPerdu())) {
                    roomBloquee.setBloquee(false);
                    player.getInventaire().remove(roomBloquee.getPerdu());
                } else {
                    roomBloquee.setBloquee(true);
                }
                this.getSIVOX().stop();
            }

            // Si objet
            if(player.getCurrentRoom().getItem() != null) {
                NameOfItem item = player.getCurrentRoom().getItem();
                player.getInventaire().add(item);
                ItemTrouveView itemtrouve = new ItemTrouveView(item);
                this.setVisible(false);
                this.getSIVOX().stop();
                itemtrouve.changeFont(getFontChoice());
                itemtrouve.changeColor(getColorChoice());
                itemtrouve.loop();
                changeColor(itemtrouve.getColorChoice());
                changeFont(itemtrouve.getFontChoice());
                player.getCurrentRoom().setItem(null);
                this.getSIVOX().stop();
                this.setVisible(true);
            }

            render();

            // Si Inventaire ouvert
            if (openInventaire) {
                // Le joueur appuie sur J, on ouvre l'inventaire
                this.setVisible(false);
                this.getSIVOX().stop();
                Inventaire inv = new Inventaire();
                // Ajout des objets
                for (int i = 0; i < player.getInventaire().size(); i++) {
                    inv.addObject(player.getInventaire().get(i));
                }
                this.getSIVOX().playWav("../ressources/sons/zip.wav");
                inv.changeFont(getFontChoice());
                inv.changeColor(getColorChoice());
                inv.loop();
                changeColor(inv.getColorChoice());
                changeFont(inv.getFontChoice());
                this.getSIVOX().stop();
                this.setVisible(true);
                openInventaire = false;
            }
            // Si le joueur ouvre la carte
            else if (openMap) {
                // Le joueur appuie sur F, on ouvre la carte
                this.setVisible(false);
                this.getSIVOX().stop();

                Mapview mapView = new Mapview(map,orientationActuelle);
                this.getSIVOX().playWav("../ressources/sons/paper.wav");
                mapView.changeFont(getFontChoice());
                mapView.changeColor(getColorChoice());
                mapView.loop();
                changeColor(mapView.getColorChoice());
                changeFont(mapView.getFontChoice());

                this.getSIVOX().stop();
                this.setVisible(true);
                openMap = false;
            }

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + ConstantesMenu.OPTIMAL_TIME) / 1000000;
                if (timeLoop > 0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }


    @Override
    public void update() {
        if(checkWin()) {
            this.dispose();

            if(scores != null){
                JSONObject newScore = new JSONObject();
                newScore.put("score",score);
                newScore.put("nom",nomJoueur);

                scores.remove(0);
                scores.put(newScore);

                try {
                    writeJSONFile("../ressources/scores.json",scores.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        // Mise à jour de la salle
        doorsChoiceNumber = 0;
        switch(orientationActuelle) {
            case UP:
                if (player.getCurrentRoom().getLeft() != null)
                    doorsChoiceNumber += 1;
                if (player.getCurrentRoom().getFront() != null)
                    doorsChoiceNumber += 2;
                if (player.getCurrentRoom().getRight() != null)
                    doorsChoiceNumber += 4;
                break;
            case RIGHT:
                if (player.getCurrentRoom().getFront() != null)
                    doorsChoiceNumber += 1;
                if (player.getCurrentRoom().getRight() != null)
                    doorsChoiceNumber += 2;
                if (player.getCurrentRoom().getBehind() != null)
                    doorsChoiceNumber += 4;
                break;
            case DOWN:
                if (player.getCurrentRoom().getRight() != null)
                    doorsChoiceNumber += 1;
                if (player.getCurrentRoom().getBehind() != null)
                    doorsChoiceNumber += 2;
                if (player.getCurrentRoom().getLeft() != null)
                    doorsChoiceNumber += 4;
                break;
            case LEFT:
                if (player.getCurrentRoom().getBehind() != null)
                    doorsChoiceNumber += 1;
                if (player.getCurrentRoom().getLeft() != null)
                    doorsChoiceNumber += 2;
                if (player.getCurrentRoom().getFront() != null)
                    doorsChoiceNumber += 4;
                break;
        }
        switch(doorsChoiceNumber){
            case 0:
                mainPanel.setName("../ressources/images/salle.PNG");
                break;
            case 1:
                mainPanel.setName("../ressources/images/salleG.PNG");
                break;
            case 2:
                mainPanel.setName("../ressources/images/salleC.PNG");
                break;
            case 3:
                mainPanel.setName("../ressources/images/salleGC.PNG");
                break;
            case 4:
                mainPanel.setName("../ressources/images/salleD.PNG");
                break;
            case 5:
                mainPanel.setName("../ressources/images/salleGD.PNG");
                break;
            case 6:
                mainPanel.setName("../ressources/images/salleCD.PNG");
                break;
            case 7:
                mainPanel.setName("../ressources/images/salleGCD.PNG");
                break;
        }
        drawBoussole(image);
    }

    @Override
    public void render() {
        this.setBackground(getBackground());
        this.mainPanel.setBackground(getBackground());
        this.alertLabel.setFont(getFont());
        this.alertLabel.setForeground(getForeground());
        alertLabel.setBounds((int) ((LARGEUR_STANDARD/4) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 220) * ratioY), (int) ((LARGEUR_STANDARD/2 + 200) * ratioX), (int) (ratioY * 150));

        image.setBounds((int) ((LARGEUR_STANDARD/16)* ratioX), (int)  ((LONGUEUR_STANDARD/2 + 240) * ratioY), (int) (250 * ratioX), (int) (250 * ratioY));

        iconF.setBounds((int) ((LARGEUR_STANDARD/2 - 55) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 60) * ratioY), (int) (130 * ratioX), (int) (160 * ratioY));
        iconB.setBounds((int) ((LARGEUR_STANDARD/2 - 55) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 380) * ratioY), (int) (130 * ratioX), (int) (160 * ratioY));
        iconR.setBounds((int) ((3*(LARGEUR_STANDARD/4) + 100) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 150) * ratioY), (int) (125 * ratioX), (int) (165 * ratioY));
        iconL.setBounds((int) ((LARGEUR_STANDARD/4 - 200) * ratioX), (int) ((LONGUEUR_STANDARD/2 + 150) * ratioY), (int) (125 * ratioX), (int) (165 * ratioY));


        // Mise à jour des positions et des flèches
        if(selectedDirection.equals("forward")){
            iconF.setVisible(true);
            iconB.setVisible(false);
            iconR.setVisible(false);
            iconL.setVisible(false);
        }else if(selectedDirection.equals("backwards")){
            iconF.setVisible(false);
            iconB.setVisible(true);
            iconR.setVisible(false);
            iconL.setVisible(false);
        }else if(selectedDirection.equals("right")){
            iconF.setVisible(false);
            iconB.setVisible(false);
            iconR.setVisible(true);
            iconL.setVisible(false);
        }else if(selectedDirection.equals("left")){
            iconF.setVisible(false);
            iconB.setVisible(false);
            iconR.setVisible(false);
            iconL.setVisible(true);
        } else {
            iconF.setVisible(false);
            iconB.setVisible(false);
            iconR.setVisible(false);
            iconL.setVisible(false);
        }
    }

    @Override
    public void reset() {
        /*player.setCurrentRoom(map.getRoomList().get(3));
        this.openInventaire = false;
        this.openMap = false;*/
    }

    private void drawBoussole(JLabel image) {

        switch (orientationActuelle) {
            case UP:
                image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/boussole1.png").getImage().getScaledInstance((int)(250 * ratioX), (int)(250 * ratioY), Image.SCALE_DEFAULT)));
                break;
            case RIGHT:
                image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/boussole4.png").getImage().getScaledInstance((int)(250 * ratioX), (int)(250 * ratioY), Image.SCALE_DEFAULT)));
                break;
            case DOWN:
                image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/boussole3.png").getImage().getScaledInstance((int)(250 * ratioX), (int)(250 * ratioY), Image.SCALE_DEFAULT)));
                break;
            case LEFT:
                image.setIcon(new ImageIcon(new ImageIcon("../ressources/images/boussole2.png").getImage().getScaledInstance((int)(250 * ratioX), (int)(250 * ratioY), Image.SCALE_DEFAULT)));
                break;
            default:
                System.out.println("Error direction");
        }
    }

    public void up() {
        this.selectedDirection = "forward";
    }

    public void right() {
        this.selectedDirection = "right";
    }

    public void down() {
        this.selectedDirection = "backwards";
    }

    public void left(){
        this.selectedDirection = "left";
    }

    private void orientationActuelleRotation(String dirRelative){
        if(dirRelative.equals("right")){
            switch(this.orientationActuelle){
                case UP: this.orientationActuelle = Direction.RIGHT; break;
                case RIGHT: this.orientationActuelle = Direction.DOWN; break;
                case DOWN: this.orientationActuelle = Direction.LEFT; break;
                case LEFT: this.orientationActuelle = Direction.UP; break;
            }
        }
        else if(dirRelative.equals("backwards")){
            switch(this.orientationActuelle){
                case UP: this.orientationActuelle = Direction.DOWN; break;
                case RIGHT: this.orientationActuelle = Direction.LEFT; break;
                case DOWN: this.orientationActuelle = Direction.UP; break;
                case LEFT: this.orientationActuelle = Direction.RIGHT; break;
            }
        }
        else if(dirRelative.equals("left")){
            switch(this.orientationActuelle){
                case UP: this.orientationActuelle = Direction.LEFT; break;
                case RIGHT: this.orientationActuelle = Direction.UP; break;
                case DOWN: this.orientationActuelle = Direction.RIGHT; break;
                case LEFT: this.orientationActuelle = Direction.DOWN; break;
            }
        }
    }


    public void confirmMove() {
        roomBloquee = player.isBloquee(this.selectedDirection,this.orientationActuelle);
        if (roomBloquee != null) {
            bloquee = true;
        } else if(player.move(this.selectedDirection,this.orientationActuelle)) {
            this.getSIVOX().stop();
            if(player.getCurrentRoom().getQuestion() == null || player.getCurrentRoom().isQuestionResolue())
            {
                this.getSIVOX().playWav("../ressources/sons/open_door_1.wav");
            }
            orientationActuelleRotation(this.selectedDirection);
            //alertLabel.setVisible(false);
           alertLabel.setText("");
            moveScoreUpdate();

        }
        else {
            alertLabel.setVisible(true);
            this.getSIVOX().stop();
            alertLabel.setText("Il n'y a pas de salle dans cette direction");
            this.getSIVOX().playText("Il n'y a pas de salle dans cette direction", getSyntheseNiveau());
        }
    }

    private void moveScoreUpdate(){
        switch(difficulty) {
            case FACILE: score -= 3; break;
            case DIFFICILE: score -= 2; break;
            case EXTREME: score -= 1; break;
        }
        if(score < 0)
            score = 0;
    }

    private void answerScoreUpdate(boolean validAnswer){
        if(validAnswer)
            switch(difficulty){
                case FACILE: score += 15; break;
                case DIFFICILE: score += 20; break;
                case EXTREME: score += 30; break;
            }
        if(score > 100)
            score = 100;
    }

    //Méthode qui permet de  vérifier si la salle actuelle est une salle "d'arrivée", pour l'instant détecte un cas de victoire quand le joueur arrive en 2,6
    public boolean checkWin(){
        if(player.getCurrentRoom().getRoomState() == RoomState.END) {
            if (player.isContaintInventaire(NameOfItem.CORNE))
                return true;
            else {
                alertLabel.setText("<html><center>Vous ne pouvez pas sortir sans la <br/>CORNE D'ABONDANCE !</center></html>");
                if (!corne) {
                    this.getSIVOX().playText("Vous ne pouvez pas sortir sans la CORNE D'ABONDANCE !", getSyntheseNiveau());
                    corne = true;
                }
                return false;
            }
        } else {corne = false;}
        return false;
    }

    public boolean isWin(){
        return (player.getCurrentRoom().getRoomState() == RoomState.END) && (player.isContaintInventaire(NameOfItem.CORNE));
    }

    public void quitter() {
        quitter = true;
    }

    public void ouvrirInventaire() {
        this.openInventaire = true;
    }

    public void ouvrirMap() {
        this.openMap = true;
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

    private void writeJSONFile(String path, String file) throws IOException {
        try{
            BufferedWriter Write = new BufferedWriter(new FileWriter(path,false));
            Write.write(file);
            Write.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return;
        } catch(IOException e){
            e.printStackTrace();
            return;
        }
    }


}