package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class ControllerTest tests Controller class.
 *
 * @see Controller
 */
public class ControllerTest {

    private Controller controller;
    private ArrayList<Player> players;

    /**
     * Method setUp initialize values.
     * */
    @BeforeEach
    public void setUp(){
        controller = new Controller();
        players = new ArrayList<>();
        players.add(0,new Player("Luca", PlayerColor.BLACK));
        players.add(1,new Player("Sergio", PlayerColor.WHITE));
        controller.addNewPlayer(PlayerColor.BLACK,"Luca");
        controller.addNewPlayer(PlayerColor.WHITE,"Sergio");
        controller.setExpertMode(true);
        controller.createGame();
    }

    /**
     * Method getGame tests the value of the game is not null.
     * */
    @Test
    public void getGame() {
        assertNotNull(controller.getGame());
    }

    /**
     * Method init check if some initial components present in the game has assumed the correct value.
     * */
    @Test
    public void init() {
        assertEquals(players.get(0).getNickname(),controller.getGame().getPlayers().get(0).getNickname());
        assertEquals(players.get(1).getNickname(),controller.getGame().getPlayers().get(1).getNickname());
        assertEquals(players.get(0).getColor(),controller.getGame().getPlayers().get(0).getColor());
        assertEquals(players.get(1).getColor(),controller.getGame().getPlayers().get(1).getColor());

        assertEquals(20,controller.getGame().getCoins());

        int size = 0;
        assertEquals(controller.getGame().getBag().size(),5);


        for(Player p:controller.getGame().getPlayers()){
            for (Color c: Color.values()) {
                if(p.getBoardPlayer().getEntrance().containsKey(c)){
                    size += p.getBoardPlayer().getEntrance().get(c);
                }
            }
            assertEquals(size,controller.getGame().getSizeEntrance());
            size = 0;
        }



        assertEquals(3,controller.getGame().getCharacterCards().size());
        for (CharacterCard c: controller.getGame().getCharacterCards()) {
            assertNotNull(c);
        }
    }

    /**
     * Method setAssistantCard tests the function present in the controller with the same name
     * with different values of the wizard names.
     * */
    @Test
    public void setAssistantCard() {

        if(!controller.setAssistantCard(players.get(0).getNickname(),AssistantName.MAGICIAN))
            fail();
        if (controller.setAssistantCard(players.get(1).getNickname(),AssistantName.MAGICIAN))
            fail();
        if (!controller.setAssistantCard(players.get(1).getNickname(),AssistantName.VETERAN))
            fail();

        assertTrue(controller.isStarted());
        assertEquals(controller.getGame().getClouds().size(),2);

        int size = 0;
        for(HashMap<Color,Integer> cloud:controller.getGame().getClouds()){
            for (Color c: Color.values()) {
                if(cloud.containsKey(c)){
                    size += cloud.get(c);
                }
            }
            assertEquals(size,3);
            size = 0;
        }
    }

    /**
     * Method isStarted tests that the game has not started yet.
     * */
    @Test
    public void isStarted() {
        assertFalse(controller.isStarted());
    }
    /**
     * Method discardCard tests the discard of a card.
     * */
    @Test
    public void discardCard() {
        controller.setAssistantCard(players.get(0).getNickname(),AssistantName.WITCH);
        controller.setAssistantCard(players.get(1).getNickname(),AssistantName.KING);
        controller.discardCard(2);
        assertEquals(controller.getGame().getActivePlayer().getLastCardUsed().getPlayerValue(),2);
    }

    /**
     * Method shiftMotherNature check the movement of the mother nature.
     * */
    @Test
    public void shiftMotherNature() {
        controller.setAssistantCard(players.get(0).getNickname(),AssistantName.KING);
        controller.setAssistantCard(players.get(1).getNickname(),AssistantName.WITCH);
        controller.discardCard(3);
        int pre = controller.getGame().getMotherNaturePos();
        controller.shiftMotherNature(2);
        int next = controller.getGame().getMotherNaturePos();
        assertEquals((pre+2)%12,next);
    }

    /**
     * Method shiftMotherNature tests that when a player choose a cloud
     * the students present on that cloud will be put in entrances of the player and the cloud selected is empty.
     * */
    @Test
    public void chooseCloud() {
        ArrayList<Integer> sizeColorsCloud = new ArrayList<>();
        ArrayList<Integer> sizeColorsEntrances = new ArrayList<>();
        int i = 0;
        for (Color c: Color.values()) {
            sizeColorsCloud.add(i,controller.getGame().getClouds().get(1).getOrDefault(c, 0));
            sizeColorsEntrances.add(i,controller.getGame().getActivePlayer().getBoardPlayer().getEntrance().getOrDefault(c, 0));
            i++;
        }

        controller.chooseCloud(1);
        int size = 0;
        i = 0;
        for (Color c: Color.values()) {
            if(controller.getGame().getClouds().get(1).containsKey(c)){
                size += controller.getGame().getClouds().get(1).get(c);
            }
            assertEquals(sizeColorsEntrances.get(i)+sizeColorsCloud.get(i), controller.getGame().getActivePlayer().getBoardPlayer().getEntrance().get(c));
            i++;
        }
        assertEquals(size,0);
    }

    /**
     * Method setNextPlayer check if at the end of the turn of the active player
     * the next player is correct.
     * */
    @Test
    public void setNextPlayer() {
        controller.setAssistantCard(players.get(0).getNickname(), AssistantName.KING);
        controller.setAssistantCard(players.get(1).getNickname(), AssistantName.WITCH);

        assertEquals(controller.getGame().getActivePlayer().getNickname(), "Luca");

        assertTrue(controller.discardCard(1));

        controller.setNextPlayer();
        assertEquals(controller.getGame().getActivePlayer().getNickname(), "Sergio");

        assertTrue(controller.discardCard(2));
        controller.setNextPlayer();
        assertEquals(controller.getGame().getActivePlayer().getNickname(), "Luca");

    }
}