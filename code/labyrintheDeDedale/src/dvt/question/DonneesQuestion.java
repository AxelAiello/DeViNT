package dvt.question;

import java.util.ArrayList;

/**
 * Permet de stocker les données sur la question posée
 * @author Axel Aiello
 * @Date 23/03/2016.
 */
public class DonneesQuestion {
    private String image;
    private String question;
    private ArrayList<String> reponses;
    private String questionSansHtml;
    private ArrayList<String> reponsesSansHtml;
    private TypeOfQuestion type;
    private int bonneReponse;

    /**
     * Constructeur
     * @param type; définie quel question est posée
     */
    public DonneesQuestion(TypeOfQuestion type) {
        this.type = type;
        reponsesSansHtml = new ArrayList<String>();
        reponses = new ArrayList<String>();
        insertDonnees();
    }

    /**
     * Met les données en fonction de la question choisie
     */
    public void insertDonnees() {

        /*****    Map Moyenne id°1 n°2   *****/
        /*****    Map Moyenne id°2 n°3   *****/
        if (type == TypeOfQuestion.ZEUS1) {
            image = "../ressources/images/zeusSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Zeus.<br/>"
                    + "Je suis le dieu de la foudre et du ciel !<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>De quoi Poséidon est-il le dieu ?</center></html>";
            reponses.add("<html>Du ciel</html>");
            reponses.add("<html>Des océans</html>");
            reponses.add("<html>De l'enfer</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Zeus."
                    + "Je suis le dieu de la foudre et du ciel !"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "De quoi Poséidon est-il le dieu ?";
            reponsesSansHtml.add("Du ciel");
            reponsesSansHtml.add("Des océans");
            reponsesSansHtml.add("De l'enfer");
            bonneReponse = 1;
        }

        /*****    Map Moyenne id°2 n°1   *****/
        /*****    Map Grande id°2 n°2   *****/
        else if (type == TypeOfQuestion.ZEUS2) {
            image = "../ressources/images/zeusSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Zeus.<br/>"
                    + "Je suis le dieu de la foudre et du ciel !<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>J'étais demain, je serai hier.<br/>"
                    + "Qui suis-je ?</center></html>";
            reponses.add("<html>Hier</html>");
            reponses.add("<html>Aujourd'hui</html>");
            reponses.add("<html>Demain</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Zeus."
                    + "Je suis le dieu de la foudre et du ciel !"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "J'étais demain, je serai hier."
                    + "Qui suis-je ?";
            reponsesSansHtml.add("Hier");
            reponsesSansHtml.add("Aujourd'hui");
            reponsesSansHtml.add("Demain");
            bonneReponse = 1;
        }

        /*****    Map Grande id°1 n°3   *****/
        else if (type == TypeOfQuestion.ZEUS3) {
            image = "../ressources/images/zeusSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Zeus.<br/>"
                    + "Je suis le dieu de la foudre et du ciel !<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Qui n'est pas mon frère ?</center></html>";
            reponses.add("<html>Hadès</html>");
            reponses.add("<html>Poséidon</html>");
            reponses.add("<html>Cupidon</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Zeus."
                    + "Je suis le dieu de la foudre et du ciel !"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "Qui n'est pas mon frère ?";
            reponsesSansHtml.add("Hadès");
            reponsesSansHtml.add("Poséidon");
            reponsesSansHtml.add("Cupidon");
            bonneReponse = 2;
        }

        /*****    Map Petite id°2 n°1   *****/
        else if (type == TypeOfQuestion.ZEUS4) {
            image = "../ressources/images/zeusSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Zeus.<br/>"
                    + "Je suis le dieu de la foudre et du ciel !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le résultat de 8 fois 5 fois 2 ?</center></html>";
            reponses.add("<html>15</html>");
            reponses.add("<html>852</html>");
            reponses.add("<html>80</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Zeus."
                    + "Je suis le dieu de la foudre et du ciel !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "Quel est le résultat de 8 fois 5 fois 2 ?";
            reponsesSansHtml.add("15");
            reponsesSansHtml.add("852");
            reponsesSansHtml.add("80");
            bonneReponse = 2;
        }

        /*****    Map Moyenne id°1 n°1   *****/
        else if (type == TypeOfQuestion.POSEIDON1) {
            image = "../ressources/images/poseidonSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Poséidon.<br/>"
                    + "Je suis le dieu des mers et des océans !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le résultat de 6 fois 7 ?</center></html>";
            reponses.add("<html>36</html>");
            reponses.add("<html>42</html>");
            reponses.add("<html>48</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Poséidon."
                    + "Je suis le dieu des mers et des océans !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "Quel est le résultat de 6 fois 7 ?";
            reponsesSansHtml.add("36");
            reponsesSansHtml.add("42");
            reponsesSansHtml.add("48");
            bonneReponse = 1;
        }

        /*****    Map Moyenne id°2 n°2   *****/
        else if (type == TypeOfQuestion.POSEIDON2) {
            image = "../ressources/images/poseidonSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Poséidon.<br/>"
                    + "Je suis le dieu des mers et des océans !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>De quoi Zeus est-il le dieu ?</center></html>";
            reponses.add("<html>Du ciel</html>");
            reponses.add("<html>Des océans</html>");
            reponses.add("<html>De l'enfer</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Poséidon."
                    + "Je suis le dieu des mers et des océans !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "De quoi Zeus est-il le dieu ?";
            reponsesSansHtml.add("Du ciel");
            reponsesSansHtml.add("Des océans");
            reponsesSansHtml.add("De l'enfer");
            bonneReponse = 0;
        }

        /*****    Map Moyenne id°1 n°3   *****/
        else if (type == TypeOfQuestion.POSEIDON3) {
            image = "../ressources/images/poseidonSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Poséidon.<br/>"
                    + "Je suis le dieu des mers et des océans !<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>J'étais demain, je serai hier.<br/>"
                    + "Qui suis-je ?</center></html>";
            reponses.add("<html>Hier</html>");
            reponses.add("<html>Aujourd'hui</html>");
            reponses.add("<html>Demain</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Poséidon."
                    + "Je suis le dieu des mers et des océans !"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "J'étais demain, je serai hier."
                    + "Qui suis-je ?";
            reponsesSansHtml.add("Hier");
            reponsesSansHtml.add("Aujourd'hui");
            reponsesSansHtml.add("Demain");
            bonneReponse = 1;
        }

        /*****    Map Grande id°3 n°2   *****/
        else if (type == TypeOfQuestion.POSEIDON4) {
            image = "../ressources/images/poseidonSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis Poséidon.<br/>"
                    + "Je suis le dieu des mers et des océans !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>De quoi Apollon est-il le dieu ?</center></html>";
            reponses.add("<html>De l'amour</html>");
            reponses.add("<html>De la lumière</html>");
            reponses.add("<html>De la chance</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis Poséidon."
                    + "Je suis le dieu des mers et des océans !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "De quoi Apollon, est-il le dieu ?";
            reponsesSansHtml.add("De l'amour");
            reponsesSansHtml.add("De la lumière");
            reponsesSansHtml.add("De la chance");
            bonneReponse = 1;
        }

        /*****    Map Grande id°3 n°4   *****/
        /*****    Map Grande id°2 n°4   *****/
        else if (type == TypeOfQuestion.POSEIDON5) {
            image = "../ressources/images/poseidonSurNuage.png";
            question = "<html>Te revoilà jeune héros.<br/>"
                    + "Je suis le dieu des mers et des océans !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>De quoi Aphrodite est-elle la déesse ?</center></html>";
            reponses.add("<html>De l'amour</html>");
            reponses.add("<html>De la lumière</html>");
            reponses.add("<html>De la chance</html>");
            questionSansHtml = "Te revoilà jeune héros."
                    + "Je suis le dieu des mers et des océans !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "De quoi Aphrodite est-elle la déesse ?";
            reponsesSansHtml.add("De l'amour");
            reponsesSansHtml.add("De la lumière");
            reponsesSansHtml.add("De la chance");
            bonneReponse = 0;
        }

        /*****    Map Petite id°1 n°2   *****/
        /*****    Map Grande id°1 n°5   *****/
        else if (type == TypeOfQuestion.SOLDIER1) {
            image = "../ressources/images/soldatSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis un spartiate.<br/>"
                    + "Je suis un guerrier fort et courageux !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Durant une bataille j'ai perdu 3 lances,<br/>"
                    + "j'en avais 12 avant.<br/>"
                    + "Combien de lances me reste-t-il?</center></html>";
            reponses.add("<html>6</html>");
            reponses.add("<html>12</html>");
            reponses.add("<html>9</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis un spartiate."
                    + "Je suis un guerrier fort et courageux !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "Durant une bataille j'ai perdu 3 lances,"
                    + "j'en avais 12 avant"
                    + "Combien de lances me reste-t-il ?";
            reponsesSansHtml.add("6");
            reponsesSansHtml.add("12");
            reponsesSansHtml.add("9");
            bonneReponse = 2;
        }

        /*****    Map Petite id°2 n°2   *****/
        else if (type == TypeOfQuestion.SOLDIER2) {
            image = "../ressources/images/soldatSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis un spartiate.<br/>"
                    + "Je suis un guerrier fort et courageux !<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le symbole de Zeus?</center></html>";
            reponses.add("<html>La foudre</html>");
            reponses.add("<html>L'amour</html>");
            reponses.add("<html>Le feu</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis un spartiate."
                    + "Je suis un guerrier fort et courageux !"
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "Quel est le symbole de Zeus?";
            reponsesSansHtml.add("La foudre");
            reponsesSansHtml.add("L'amour");
            reponsesSansHtml.add("Le feu");
            bonneReponse = 0;
        }

        /*****    Map Petite id°3 n°1   *****/
        else if (type == TypeOfQuestion.SOLDIER3) {
            image = "../ressources/images/soldatSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis un spartiate.<br/>"
                    + "Je suis un guerrier fort et courageux !<br/>"
                    + "Entre camarade il faut s'entraider.<br/>"
                    + "Pour cela réponds à ma question :<br/><br/>"
                    + "5 soldats peuvent gagner contre 5 ennemis<br/>"
                    + "en 5 minutes.<br/>"
                    + "Combien de soldats faut-il au minimum,<br/>"
                    + "pour vaincre 50 ennemis en 50 minutes ?</html>";
            reponses.add("<html>5</html>");
            reponses.add("<html>50</html>");
            reponses.add("<html>10</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis un spartiate."
                    + "Je suis un guerrier fort et courageux !"
                    + "Entre camarade il faut s'entraider. "
                    + "Pour cela réponds à ma question : "
                    + "5 soldats peuvent gagner contre 5 ennemis "
                    + "en 5 minutes. "
                    + "Combien de soldats faut-il au minimum, "
                    + "pour vaincre 50 ennemis en 50 minutes ?";
            reponsesSansHtml.add("5");
            reponsesSansHtml.add("50");
            reponsesSansHtml.add("10");
            bonneReponse = 0;
        }

        /*****    Map Grande id°2 n°1   *****/
        else if (type == TypeOfQuestion.SOLDIER4) {
            image = "../ressources/images/soldatSurNuage.png";
            question = "<html>Bonjour jeune Héros, je suis un spartiate.<br/>"
                    + "Je suis un guerrier fort et courageux !<br/>"
                    + "Entre camarade il faut s'entraider.<br/>"
                    + "Pour cela réponds à ma question :<br/><br/>"
                    + "5 soldats peuvent gagner contre 5 ennemis<br/>"
                    + "en 5 minutes.<br/>"
                    + "Combien d'ennemis peut on vaincre en<br/>"
                    + "50 minutes et avec 10 soldats ?</html>";
            reponses.add("<html>50</html>");
            reponses.add("<html>100</html>");
            reponses.add("<html>500</html>");
            questionSansHtml = "Bonjour jeune Héros, je suis un spartiate."
                    + "Je suis un guerrier fort et courageux !"
                    + "Entre camarade il faut s'entraider. "
                    + "Pour cela réponds à ma question : "
                    + "5 soldats peuvent gagner contre 5 ennemis "
                    + "en 5 minutes. "
                    + "Combien d'ennemis peut on vaincre, en"
                    + "50 minutes et avec 10 soldats ?";
            reponsesSansHtml.add("50");
            reponsesSansHtml.add("100");
            reponsesSansHtml.add("500");
            bonneReponse = 1;
        }

        /*****    Map Petite id°3 n°2   *****/
        /*****    Map Grande id°1 n°1   *****/
        else if(type == TypeOfQuestion.CUPIDON1) {
            image = "../ressources/images/cupidonSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Cupidon.<br/>"
                    + "Je suis l'ange de l'Amour.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Les dieux ne peuvent pas me contrôler.<br/>"
                    + "Qui suis-je ?</center></html>";
            reponses.add("<html>Le temps</html>");
            reponses.add("<html>Les éléments</html>");
            reponses.add("<html>L'amour</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Cupidon."
                    + "Je suis l'ange de l'Amour."
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "Les dieux ne peuvent pas me contrôler."
                    + "Qui suis-je ?";
            reponsesSansHtml.add("Le temps");
            reponsesSansHtml.add("Les éléments");
            reponsesSansHtml.add("L'amour");
            bonneReponse = 0;
        }

        /*****    Map Grande id°1 n°2   *****/
        else if(type == TypeOfQuestion.CUPIDON2) {
            image = "../ressources/images/cupidonSurNuage.png";
            question = "<html>Te revoilà jeune héros.<br/>"
                    + "Sais-tu que Zeus, Hadès et Poséidon sont <br/>"
                    + "frères ? Je vais t'aider à trouver le bon<br/>"
                    + "chemin, pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le résultat de 7 fois 8 ?</center></html>";
            reponses.add("<html>56</html>");
            reponses.add("<html>49</html>");
            reponses.add("<html>48</html>");
            questionSansHtml = "Te revoilà jeune héros."
                    + "Sais-tu que Zeus, Hadès et Poséidon sont frères ?"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "Quel est le résultat de 7 fois 8 ?";
            reponsesSansHtml.add("56");
            reponsesSansHtml.add("49");
            reponsesSansHtml.add("48");
            bonneReponse = 0;
        }

        /*****    Map Grande id°1 n°4   *****/
        else if(type == TypeOfQuestion.CUPIDON3) {
            image = "../ressources/images/cupidonSurNuage.png";
            question = "<html>Te revoilà jeune héros.<br/>"
                    + "Sais-tu que Zeus est le dieu de la foudre ?<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le résultat de 5 + 3 x 2 ?</center></html>";
            reponses.add("<html>16</html>");
            reponses.add("<html>13</html>");
            reponses.add("<html>11</html>");
            questionSansHtml = "Te revoilà jeune héros."
                    + "Sais-tu que Zeus est le dieu de la foudre ?"
                    + "Je vais t'aider à trouver le bon chemin,"
                    + "pour cela réponds à ma question :"
                    + "Quel est le résultat de 5 plus 3 fois 2 ?";
            reponsesSansHtml.add("16");
            reponsesSansHtml.add("13");
            reponsesSansHtml.add("11");
            bonneReponse = 2;
        }

        /*****    Map Moyenne id°3 n°1   *****/
        else if(type == TypeOfQuestion.APHRODITE1) {
            image = "../ressources/images/aphroditeSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Aphrodite.<br/>"
                    + "Je suis la déesse de l'Amour.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "Un chat peut avoir 3 chatons tous les 4<br/>"
                    + "mois et un chaton devient adulte en 4 mois.<br/>"
                    + "J'ai un chat, combien de chat<br/>"
                    + "j'aurai au bout de 8 mois ?</html>";
            reponses.add("<html>7</html>");
            reponses.add("<html>4</html>");
            reponses.add("<html>1</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Aphrodite. "
                    + "Je suis la déesse de l'Amour. "
                    + "Je vais t'aider à trouver le bon chemin, "
                    + "pour cela réponds à ma question : "
                    + "Un chat peut avoir 3 chatons tous les 4 mois, "
                    + "un chaton devient adulte en 4 mois. "
                    + "J'ai un chat, combien de chat "
                    + "j'aurai au bout de 8 mois ?";
            reponsesSansHtml.add("7");
            reponsesSansHtml.add("4");
            reponsesSansHtml.add("1");
            bonneReponse = 2;
        }

        /*****    Map Moyenne id°3 n°3   *****/
        /*****    Map Grande id°3 n°3   *****/
        else if(type == TypeOfQuestion.APHRODITE2) {
            image = "../ressources/images/aphroditeSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Aphrodite.<br/>"
                    + "Je suis la déesse de l'Amour.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "J'uilise une brosse Auréole pour me coiffer.<br/>"
                    + "Et j'aimerais savoir combien de personnes <br/>"
                    + "utilisent une brosse Auréole,<br/>"
                    + "sachant que dans mon temple, 2 fois 3<br/>"
                    + "personnes l'utilisent.</html>";
            reponses.add("<html>5</html>");
            reponses.add("<html>6</html>");
            reponses.add("<html>7</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Aphrodite. "
                    + "Je suis la déesse de l'Amour. "
                    + "Je vais t'aider à trouver le bon chemin, "
                    + "pour cela réponds à ma question : "
                    + "J'uilise une brosse Auréole pour me coiffer."
                    + "Et j'aimerais savoir combien de personnes "
                    + "utilisent une brosse Auréole,"
                    + "sachant que dans mon temple, 2 fois 3"
                    + "personnes l'utilisent.";
            reponsesSansHtml.add("5");
            reponsesSansHtml.add("6");
            reponsesSansHtml.add("7");
            bonneReponse = 2;
        }

        /*****    Map Grande id°2 n°3   *****/
        else if(type == TypeOfQuestion.APHRODITE3) {
            image = "../ressources/images/aphroditeSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Aphrodite.<br/>"
                    + "Je suis la déesse de l'Amour.<br/>"
                    + "Je vais t'aider à trouver le bon chemin.<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "<center>Quel est le symbole de Zeus?</center></html>";
            reponses.add("<html>La foudre</html>");
            reponses.add("<html>L'amour</html>");
            reponses.add("<html>Le feu</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Aphrodite. "
                    + "Je suis la déesse de l'Amour. "
                    + "Je vais t'aider à trouver le bon chemin."
                    + "pour cela réponds à ma question :"
                    + "Quel est le symbole de Zeus?";
            reponsesSansHtml.add("La foudre");
            reponsesSansHtml.add("L'amour");
            reponsesSansHtml.add("Le feu");
            bonneReponse = 0;
        }

        /*****    Map Petite id°1 n°1   *****/
        else if(type == TypeOfQuestion.APPOLON1) {
            image = "../ressources/images/appolonSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Apollon.<br/>"
                    + "Je suis le dieu de la lumière et des arts.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "Avec mon char je parcours la terre,<br/>"
                    + "je fais 10 kilomètres en 6 minutes.<br/>"
                    + "Combien de kilomètres puis je faire en <br/>"
                    + "une heure ?</html>";
            reponses.add("<html>60</html>");
            reponses.add("<html>100</html>");
            reponses.add("<html>42</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Apollon. "
                    + "Je suis le dieu de la lumière et des arts. "
                    + "Je vais t'aider à trouver le bon chemin, "
                    + "pour cela réponds à ma question : "
                    + "Avec mon char je parcours la terre, "
                    + "je fais 10 kilomètres en 6 minutes. "
                    + "Combien de kilomètres puis je faire en une heure ? ";
            reponsesSansHtml.add("60");
            reponsesSansHtml.add("100");
            reponsesSansHtml.add("42");
            bonneReponse = 1;
        }

        /*****    Map Moyenne id°3 n°2   *****/
        /*****    Map Grande id°3 n°1   *****/
        else if(type == TypeOfQuestion.APPOLON2) {
            image = "../ressources/images/appolonSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Apollon.<br/>"
                    + "Je suis le dieu de la lumière et des arts.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "Je dois emmener 10 soldats combattre,<br/>"
                    + "mais mon char ne peut contenir que 5 places.<br/>"
                    + "Combien d'aller retour dois-je faire ?</html>";
            reponses.add("<html>1</html>");
            reponses.add("<html>2</html>");
            reponses.add("<html>3</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Apollon. "
                    + "Je suis le dieu de la lumière et des arts. "
                    + "Je vais t'aider à trouver le bon chemin, "
                    + "pour cela réponds à ma question : "
                    + "Je dois emmener 10 soldats combattre,"
                    + "mais mon char ne peut contenir que 5 places."
                    + "Combien d'aller retour dois je faire ?";
            reponsesSansHtml.add("1");
            reponsesSansHtml.add("2");
            reponsesSansHtml.add("3");
            bonneReponse = 2;
        }

        /*****    Map Grande id°2 n°5   *****/
        else if(type == TypeOfQuestion.APPOLON3) {
            image = "../ressources/images/appolonSurNuage.png";
            question = "<html>Bonjour jeune héros, je suis Apollon.<br/>"
                    + "Je suis le dieu de la lumière et des arts.<br/>"
                    + "Je vais t'aider à trouver le bon chemin,<br/>"
                    + "pour cela réponds à ma question :<br/><br/>"
                    + "Avec mon char je parcours la terre,<br/>"
                    + "je fais 2 kilomètres en 3 minutes.<br/>"
                    + "Combien de kilomètres puis je faire en <br/>"
                    + "une heure ?</html>";
            reponses.add("<html>49,3</html>");
            reponses.add("<html>40</html>");
            reponses.add("<html>42</html>");
            questionSansHtml = "Bonjour jeune héros, je suis Apollon. "
                    + "Je suis le dieu de la lumière et des arts. "
                    + "Je vais t'aider à trouver le bon chemin, "
                    + "pour cela réponds à ma question : "
                    + "Avec mon char je parcours la terre, "
                    + "je fais 2 kilomètres en 3 minutes. "
                    + "Combien de kilomètres puis je faire en une heure ? ";
            reponsesSansHtml.add("49,3");
            reponsesSansHtml.add("40");
            reponsesSansHtml.add("42");
            bonneReponse = 1;
        }

    }

    public ArrayList<String> getReponsesSansHtml() {
        return reponsesSansHtml;
    }

    public String getQuestionSansHtml() {
        return questionSansHtml;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getReponses() {
        return reponses;
    }

    public String getImage() {
        return image;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

}
