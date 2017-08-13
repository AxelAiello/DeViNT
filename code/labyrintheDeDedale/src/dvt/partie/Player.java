package dvt.partie;

import dvt.inventaire.*;
import dvt.map.Room;
import dvt.map.RoomState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private Room currentRoom;
    private ArrayList<NameOfItem> inventaire;
    private int score;

    double longueurEcran = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    double largeurEcran = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    public Player(String name, Room currentRoom) {
        this.score = 0;
        this.name = name;
        this.currentRoom = currentRoom;
        inventaire = new ArrayList<>();
    }

    //TODO méthode pour connaitre l'orientation du mouvement du joueur et agir en fonction

    public Room isBloquee(String direction, Direction dir) {
        switch(dir) {
            case UP:
                if (direction.equals("forward") && this.getCurrentRoom().getFront() != null) {
                    if (currentRoom.getFront().isBloquee()) {
                        currentRoom.getFront().setVisite(true);
                        return currentRoom.getFront();
                    } else {
                        return null;
                    }
                } else if (direction.equals("right") && this.getCurrentRoom().getRight() != null) {
                    if (currentRoom.getRight().isBloquee()) {
                        currentRoom.getRight().setVisite(true);
                        return currentRoom.getRight();
                    } else {
                        return null;
                    }
                } else if (direction.equals("backwards") && this.getCurrentRoom().getBehind() != null) {
                    if (currentRoom.getBehind().isBloquee()) {
                        currentRoom.getBehind().setVisite(true);
                        return currentRoom.getBehind();
                    } else {
                        return null;
                    }
                } else if (direction.equals("left") && this.getCurrentRoom().getLeft() != null) {
                    if (currentRoom.getLeft().isBloquee()) {
                        currentRoom.getLeft().setVisite(true);
                        return currentRoom.getLeft();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            case RIGHT:
                if (direction.equals("forward") && this.getCurrentRoom().getRight() != null) {
                    if (currentRoom.getRight().isBloquee()) {
                        currentRoom.getRight().setVisite(true);
                        return currentRoom.getRight();
                    } else {
                        return null;
                    }
                } else if (direction.equals("right") && this.getCurrentRoom().getBehind() != null) {
                    if (currentRoom.getBehind().isBloquee()) {
                        currentRoom.getBehind().setVisite(true);
                        return currentRoom.getBehind();
                    } else {
                        return null;
                    }
                } else if (direction.equals("backwards") && this.getCurrentRoom().getLeft() != null) {
                    if (currentRoom.getLeft().isBloquee()) {
                        currentRoom.getLeft().setVisite(true);
                        return currentRoom.getLeft();
                    } else {
                        return null;
                    }
                } else if (direction.equals("left") && this.getCurrentRoom().getFront() != null) {
                    if (currentRoom.getFront().isBloquee()) {
                        currentRoom.getFront().setVisite(true);
                        return currentRoom.getFront();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            case DOWN:
                if (direction.equals("forward") && this.getCurrentRoom().getBehind() != null) {
                    if (currentRoom.getBehind().isBloquee()) {
                        currentRoom.getBehind().setVisite(true);
                        return currentRoom.getBehind();
                    } else {
                        return null;
                    }
                } else if (direction.equals("right") && this.getCurrentRoom().getLeft() != null) {
                    if (currentRoom.getLeft().isBloquee()) {
                        currentRoom.getLeft().setVisite(true);
                        return currentRoom.getLeft();
                    } else {
                        return null;
                    }
                } else if (direction.equals("backwards") && this.getCurrentRoom().getFront() != null) {
                    if (currentRoom.getFront().isBloquee()) {
                        currentRoom.getFront().setVisite(true);
                        return currentRoom.getFront();
                    } else {
                        return null;
                    }
                } else if (direction.equals("left") && this.getCurrentRoom().getRight() != null) {
                    if (currentRoom.getRight().isBloquee()) {
                        currentRoom.getRight().setVisite(true);
                        return currentRoom.getRight();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            case LEFT:
                if (direction.equals("forward") && this.getCurrentRoom().getLeft() != null) {
                    if (currentRoom.getLeft().isBloquee()) {
                        currentRoom.getLeft().setVisite(true);
                        return currentRoom.getLeft();
                    } else {
                        return null;
                    }
                } else if (direction.equals("right") && this.getCurrentRoom().getFront() != null) {
                    if (currentRoom.getFront().isBloquee()) {
                        currentRoom.getFront().setVisite(true);
                        return currentRoom.getFront();
                    } else {
                        return null;
                    }
                } else if (direction.equals("backwards") && this.getCurrentRoom().getRight() != null) {
                    if (currentRoom.getRight().isBloquee()) {
                        currentRoom.getRight().setVisite(true);
                        return currentRoom.getRight();
                    } else {
                        return null;
                    }
                } else if (direction.equals("left") && this.getCurrentRoom().getBehind() != null) {
                    if (currentRoom.getBehind().isBloquee()) {
                        currentRoom.getBehind().setVisite(true);
                        return currentRoom.getBehind();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
        }
        return null;
    }


    public boolean move(String direction, Direction dir){
        this.score++;

        // Suppression de l'ancienne position
        if (currentRoom.getRoomState() == RoomState.CURRENT_POS) {
            currentRoom.setRoomState(RoomState.STANDARD);
            if(longueurEcran < 800 && largeurEcran < 1400) {
                currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/standardCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
            }
            else {
                currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/standardCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
            }
        }
        // Changement de salle

        //TODO appliquer la rotation avant de se déplacer / à améliorer vite en créant fonction Room.getRoom(Direction)
        switch(dir) {
            case UP:
                if (direction.equals("forward") && this.getCurrentRoom().getFront() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getFront());
                } else if (direction.equals("right") && this.getCurrentRoom().getRight() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getRight());
                } else if (direction.equals("backwards") && this.getCurrentRoom().getBehind() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getBehind());
                } else if (direction.equals("left") && this.getCurrentRoom().getLeft() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getLeft());
                } else {
                    if (currentRoom.getRoomState() == RoomState.STANDARD) {
                        currentRoom.setRoomState(RoomState.CURRENT_POS);
                        if(longueurEcran < 800 && largeurEcran < 1400) {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                        }
                        else {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                        }
                    }
                    return false;
                }
                break;
            case RIGHT:
                if (direction.equals("forward") && this.getCurrentRoom().getRight() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getRight());
                } else if (direction.equals("right") && this.getCurrentRoom().getBehind() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getBehind());
                } else if (direction.equals("backwards") && this.getCurrentRoom().getLeft() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getLeft());
                } else if (direction.equals("left") && this.getCurrentRoom().getFront() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getFront());
                } else {
                    if (currentRoom.getRoomState() == RoomState.STANDARD) {
                        currentRoom.setRoomState(RoomState.CURRENT_POS);
                        if(longueurEcran < 800 && largeurEcran < 1400) {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                        }
                        else {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                        }
                    }
                    return false;
                }
                break;
            case DOWN:
                if (direction.equals("forward") && this.getCurrentRoom().getBehind() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getBehind());
                } else if (direction.equals("right") && this.getCurrentRoom().getLeft() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getLeft());
                } else if (direction.equals("backwards") && this.getCurrentRoom().getFront() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getFront());
                } else if (direction.equals("left") && this.getCurrentRoom().getRight() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getRight());
                } else {
                    if (currentRoom.getRoomState() == RoomState.STANDARD) {
                        currentRoom.setRoomState(RoomState.CURRENT_POS);
                        if(longueurEcran < 800 && largeurEcran < 1400) {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                        }
                        else {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                        }                    }
                    return false;
                }
                break;
            case LEFT:
                if (direction.equals("forward") && this.getCurrentRoom().getLeft() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getLeft());
                } else if (direction.equals("right") && this.getCurrentRoom().getFront() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getFront());
                } else if (direction.equals("backwards") && this.getCurrentRoom().getRight() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getRight());
                } else if (direction.equals("left") && this.getCurrentRoom().getBehind() != null) {
                    this.setCurrentRoom(this.getCurrentRoom().getBehind());
                } else {
                    if (currentRoom.getRoomState() == RoomState.STANDARD) {
                        currentRoom.setRoomState(RoomState.CURRENT_POS);
                        if(longueurEcran < 800 && largeurEcran < 1400) {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                        }
                        else {
                            currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                        }
                    }
                    return false;
                }
                break;
        }
        currentRoom.setVisite(true);
        // Mise en place de la position actuel
        if (currentRoom.getRoomState() == RoomState.STANDARD || currentRoom.getRoomState() == RoomState.BLOCKED) {
            currentRoom.setRoomState(RoomState.CURRENT_POS);
            if(longueurEcran < 800 && largeurEcran < 1400) {
                currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCase.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
            }
            else {
                currentRoom.setRoom(new ImageIcon(new ImageIcon("../ressources/images/currentCaseV2.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
            }
        }
        return true;
    }

    public void setCurrentRoom(Room room){ this.currentRoom = room; }

    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    public ArrayList<NameOfItem> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<NameOfItem> inventaire) {
        this.inventaire = inventaire;
    }

    public boolean isContaintInventaire(NameOfItem name) {
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i) == name) {
                return true;
            }
        }
        return false;
    }
}
