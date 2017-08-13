package dvt.map;

import dvt.inventaire.NameOfItem;
import dvt.option.TypeOfDifficulty;
import dvt.question.TypeOfQuestion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Thomas Moras
 */
public class Map {
    private HashMap<Integer, Room> roomList;
    private Room roomStart;
    private TypeOfDifficulty difficulty;

    public Map(TypeOfDifficulty difficulty) {
        this.difficulty = difficulty;
        roomList = new HashMap<>();
        this.initRoom(roomList);
    }

    private void initRoom(HashMap<Integer, Room> roomList) {

        this.roomList = roomList;

        Random r = new Random();

        // On récupère la map que l'on souhaite ouvrir
        JSONArray rooms;
        if (difficulty == TypeOfDifficulty.FACILE) {
            int rand = r.nextInt(15);
            if(rand < 5)
                rooms = openJSONFile("../ressources/maps/map_petite.json");
            else if(rand < 10)
                rooms = openJSONFile("../ressources/maps/map_petite_2.json");
            else
                rooms = openJSONFile("../ressources/maps/map_petite_3.json");
        } else if (difficulty == TypeOfDifficulty.DIFFICILE) {
            int rand = r.nextInt(15);
            if(rand < 5)
                rooms = openJSONFile("../ressources/maps/map_moyenne.json");
            else if(rand <10)
                rooms = openJSONFile("../ressources/maps/map_moyenne_2.json");
            else
                rooms = openJSONFile("../ressources/maps/map_moyenne_3.json");
        } else {
            int rand = r.nextInt(15);
            if(rand < 5)
                rooms = openJSONFile("../ressources/maps/map_grande.json");
            else if(rand <10)
                rooms = openJSONFile("../ressources/maps/map_grande_2.json");
            else
                rooms = openJSONFile("../ressources/maps/map_grande_3.json");
        }

        int ind = 0; // numéro de salle
        ArrayList<JSONArray> linksList = new ArrayList<>(); // Permet de lister les liens entre les salles

        /** Pour chaque salle dans le fichier json **/
        if (rooms != null) {
            for (Object o : rooms) {
                JSONObject room = (JSONObject) o;

                /** On récupère son RoomState **/
                String state;
                try {
                    state = room.get("state").toString();
                } catch (JSONException e) {
                    state = null;
                }

                /** On récupère ses coordonnées **/
                JSONArray coordinate = new JSONArray(room.get("coordinate").toString());

                /** On récupère les liens de cette salle avec les autres **/
                JSONArray links;
                try {
                    links = new JSONArray(room.get("links").toString());
                } catch (JSONException e) {
                    links = null;
                }
                linksList.add(links); // On ajoute les liens à la liste

                /** On récupère l'item présent dans la salle s'il existe **/
                String item;
                try {
                    item = room.get("item").toString();
                } catch (JSONException e) {
                    item = null;
                }

                /** Pour une salle bloquee, l'item qu'on doit retrouver **/
                String itemNeeded;
                try {
                    itemNeeded = room.get("itemNeeded").toString();
                } catch (JSONException e) {
                    itemNeeded = null;
                }

                /** On récupère le type de question dans la salle **/
                String god;
                try {
                    god = room.get("god").toString();
                } catch (JSONException e) {
                    god = null;
                }

                /** S'il y a une question, on récupère la couleur de la bonne porte **/
                String goodDoor;
                try {
                    goodDoor = room.get("goodDoor").toString();
                } catch (JSONException e) {
                    goodDoor = null;
                }

                /** On crée la salle à partir de son RoomState et de ses coordonnées **/
                Room salle = new Room(RoomState.valueOf(state), new Coordinate(coordinate.getInt(0), coordinate.getInt(1)));
                if (salle.getRoomState().equals(RoomState.START))
                    roomStart = salle; // On stocke la salle de départ

                /** On rajoute l'item dans la salle s'il y en a **/
                if (item != null)
                    salle.setItem(NameOfItem.valueOf(item));

                /** On rajoute l'item à trouver pour débloquer la salle **/
                //salle.setItemNeeded(NameOfItem.valueOf(itemNeeded));

                /** On rajoute l'énigme dans la salle **/
                if (god != null)
                    salle.setQuestion(TypeOfQuestion.valueOf(god));

                /** On rajoute la bonen porte dans la salle **/
                if (goodDoor != null)
                    salle.setGoodDoor(goodDoor);

                if (itemNeeded != null)
                    salle.setPerdu(NameOfItem.valueOf(itemNeeded));

                /** On ajoute la salle dans la list de toutes les salles **/
                this.roomList.put(ind, salle);

                /** On incrémente le compteur de numéro de salle **/
                ind++;
            }
        }

        /** On crée les liens entre les salles **/
        this.predefineLink(linksList);
    }

    private void predefineLink(ArrayList<JSONArray> map) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).length() != 0) {
                for (Object o : map.get(i)) {
                    JSONObject link = (JSONObject) o;
                    setLinktoRoom(i, link);
                }
            }
        }


    }

    /**
     * Méthode qui crée un lien d'une salle
     * @param i    index de la salle
     * @param link un lien de la salle
     */
    private void setLinktoRoom(int i, JSONObject link) {
        switch (link.get("direction").toString()) {
            case "BehindFront":
                roomList.get(i).setBehindFront(roomList.get(link.getInt("idRoom")));
                break;
            case "LeftRight":
                roomList.get(i).setLeftRight(roomList.get(link.getInt("idRoom")));
                break;
            case "FrontBehind":
                roomList.get(i).setFrontBehind(roomList.get(link.getInt("idRoom")));
                break;
            case "RightLeft":
                roomList.get(i).setRightLeft(roomList.get(link.getInt("idRoom")));
                break;
        }
    }

    public HashMap<Integer, Room> getRoomList() {
        return roomList;
    }

    public Room getStartRoom() {

        return roomStart;
    }

    /**
     * Méthode pour ouvrir un fichier JSON dans un JSONArray
     * @param path chemin du fichier
     * @return le JSONArray correspondant au fichier json
     */
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
