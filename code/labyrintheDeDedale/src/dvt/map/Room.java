package dvt.map;

import dvt.inventaire.NameOfItem;
import dvt.question.TypeOfQuestion;

import javax.swing.*;
import java.awt.*;


/**
 * @author Thomas MORAS
 */
public class Room {

    private RoomState roomState;
    private Coordinate c;
    private ImageIcon roomImage;
    private NameOfItem perdu;
    private NameOfItem item;
    private TypeOfQuestion question;
    private boolean questionResolue, visite;
    private String goodDoor;
    private Room left,right,front,behind;
    double longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    double largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private boolean bloquee;

    // A RAJOUTER DANS CHAQUE SALLE
    // LE NOMBRE DE SALLE ACCESSIBLE
    // UN METHODE QUI RENVOIE LA BONNE SALLE
    // UN METHODE QUI RENVOIE UNE DES DEUX MAUVAISES SALLES
    // UNE ENIGME SI LA SALLE A TROIS PORTES
    // UN ITEM OU 0 ITEMS
    public Room(RoomState roomState, Coordinate c) {
        this.visite = false;
        this.roomState = roomState;
        this.c = c;
        this.item = null;
        this.item = null;
        ImageIcon imageIcon = null;
        bloquee = false;
        switch (roomState) {
            case CURRENT_POS:
                if((longueurEcran < 800) && (largeurEcran < 1400)) {
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                } else {
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
                }
                break;
            case BLOCKED:
                bloquee = true;
                if(longueurEcran < 800 && largeurEcran < 1400)
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/blockedCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                else
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/blockedCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
                break;
            case END:
                visite = true;
                if(longueurEcran < 800 && largeurEcran < 1400)
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/endCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                else
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/endCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
                break;
            case START:
                visite = true;
                if(longueurEcran < 800 && largeurEcran < 1400)
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/startCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                else
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/startCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
                break;
            case STANDARD:
                if(longueurEcran < 800 && largeurEcran < 1400)
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/standardCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                else
                    imageIcon = new ImageIcon(new ImageIcon("../ressources/images/standardCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));                break;
        }
        this.roomImage = imageIcon;
        this.left = null;
        this.right = null;
        this.front = null;
        this.behind = null;
        question = null;
        goodDoor = null;
        questionResolue = false;
    }

    public NameOfItem getPerdu() {
        return perdu;
    }

    public void setPerdu(NameOfItem perdu) {
        this.perdu = perdu;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }

    public void setC(Coordinate c) {
        this.c = c;
    }

    public Room getBehind() {
        return behind;
    }

    public Room getFront() {
        return front;
    }

    public Room getRight() {
        return right;
    }

    public Room getLeft() {
        return left;
    }

    public NameOfItem getItem() {
        return item;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public boolean isStartRoom() {
        return (roomState == RoomState.START);
    }

    public Coordinate getC() {
        return c;
    }

    public ImageIcon getRoomImage() {
        return roomImage;
    }

    public void setRoom(ImageIcon r) {roomImage = r;}

    public TypeOfQuestion getQuestion() {
        return question;
    }

    public boolean isQuestionResolue() {
        return questionResolue;
    }

    public void setQuestionResolue(boolean questionResolue) {
        this.questionResolue = questionResolue;
    }

    public void setQuestion(TypeOfQuestion question) {
        this.question = question;
    }

    public void setItem(NameOfItem item) {
        this.item = item;
    }

    public String getGoodDoor() {
        return goodDoor;
    }

    public void setGoodDoor(String goodDoor) {
        this.goodDoor = goodDoor;
    }

    public void setLeftRoom(Room leftRoom) {
        this.left = leftRoom;
    }

    public void setRight(Room rightRoom) {
        this.right = rightRoom;
    }

    public void setFrontRoom(Room frontRoom) {
        this.front = frontRoom;
    }

    public void setBehindRoom(Room behindRoom) {
        this.behind = behindRoom;
    }

    public void setRightLeft(Room rightRoom) {
        rightRoom.setLeftRoom(this);
        this.right = rightRoom;
    }

    public void setFrontBehind(Room frontRoom) {
        frontRoom.setBehindRoom(this);
        this.front = frontRoom;
    }

    public void setLeftRight(Room leftRoom) {
        leftRoom.setRight(this);
        this.left = leftRoom;
    }

    public void setBehindFront(Room behindRoom) {
        behindRoom.setFrontRoom(this);
        this.behind = behindRoom;
    }

    public boolean isBloquee() {
        return bloquee;
    }

    public void setBloquee(boolean bloquee) {
        this.bloquee = bloquee;
    }

    public boolean isVisite() {
        return visite;
    }

    public void setVisite(boolean visite) {
        this.visite = visite;
    }

}
