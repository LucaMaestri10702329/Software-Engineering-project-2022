package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.AssistantName;
import it.polimi.ingsw.model.PlayerColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.controller.GameState.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class HandlerTest tests Handler class.
 *
 * @see Handler
 */
public class HandlerTest {

    Handler handler = new Handler();


    /**
     * Method setUp initialize values.
     * */
    @BeforeEach
    public void setUp(){
        handler.getController().setNumberOfPlayers(2);
        handler.getController().setExpertMode(true);
        handler.setControllerState(GameState.SETUP);
        handler.getController().addNewPlayer(PlayerColor.BLACK,"Luca");
        handler.getController().addNewPlayer(PlayerColor.WHITE,"Sergio");
    }

    /**
     * Method controllerState controls the set and the get methods of the controller state.
     * */
    @Test
    public void controllerState() {
        handler.setControllerState(INIT);
        assertEquals(handler.getControllerState(),INIT);
        handler.setControllerState(DECK);
        assertEquals(handler.getControllerState(),DECK);
        handler.setControllerState(STARTGAME);
        assertEquals(handler.getControllerState(),STARTGAME);
        handler.setControllerState(STARTROUND);
        assertEquals(handler.getControllerState(),STARTROUND);
        handler.setControllerState(SETUP);
        assertEquals(handler.getControllerState(),SETUP);
    }

    /**
     * Method getController check that handler has not a controller not null.
     * */
    @Test
    public void getController() {
        assertNotNull(handler.getController());
    }
    /**
     * Method getColors check that if some colors are chosen these colors are not available.
     * */
    @Test
    public void getColors() {
        handler.checkColor(PlayerColor.BLACK,"Luca");
        handler.checkColor(PlayerColor.WHITE,"Sergio");
        assertEquals(handler.getColors().size(),0);
        assertFalse(handler.getColors().contains(PlayerColor.BLACK));
        assertFalse(handler.getColors().contains(PlayerColor.WHITE));
    }
    /**
     * Method getAssistantNames tests that all wizards are available.
     * */
    @Test
    public void getAssistantNames() {
        for (AssistantName name:AssistantName.values()){
            assertTrue(handler.getAssistantNames().contains(name));
        }
    }
    /**
     * Method checkUsername tests that two players can not choose the same username,
     * but they can choose two different username.
     * */
    @Test
    public void checkUsername() {
        handler.checkUsername("Luca");
        assertFalse(handler.checkUsername("Luca"));
        assertTrue(handler.checkUsername("Mario"));
    }
    /**
     * Method checkColor tests that two players can not choose the same color,
     * but they can choose two different color.
     * */
    @Test
    public void checkColor() {
        handler.checkColor(PlayerColor.BLACK,"Mario");
        assertTrue(handler.checkColor(PlayerColor.GRAY,"Mario"));
        assertFalse(handler.checkColor(PlayerColor.BLACK,"Mario"));
    }

    /**
     * Method checkDeck tests that two players can not choose the same deck,
     * but they can choose two different deck.
     * */
    @Test
    public void checkDeck() {
        handler.checkDeck(AssistantName.KING,"Luca");
        assertFalse(handler.checkDeck(AssistantName.KING,"Luca"));
        assertTrue(handler.checkDeck(AssistantName.WITCH,"Sergio"));
    }
    /**
     * Method setDecks tests that the players, when the game is created,
     * have the deck they selected previously.
     * */
    @Test
    public void setDecks() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");
        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getController().getGame().getPlayers().get(0).getNickname(),"Luca");
        assertEquals(handler.getController().getGame().getPlayers().get(0).getCards().getWizardName(),AssistantName.KING);
        assertEquals(handler.getController().getGame().getPlayers().get(1).getCards().getWizardName(),AssistantName.WITCH);
    }

    /**
     * Method getClouds tests that at the start of the game the clouds are 2
     * because the game is for 2 players.
     * */
    @Test
    public void getClouds() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");
        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getClouds().size(),2);
    }


    /**
     * Method getEntrances tests that in the game the entrances are 2
     * because there are 2 players.
     * */
    @Test
    public void getEntrances() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");

        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getEntrances().size(),2);
    }

    /**
     * Method getCoins tests that at the start of the game each player has 1 coin.
     * */
    @Test
    public void getCoins() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");

        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getCoins().get(PlayerColor.WHITE),1);
        assertEquals(handler.getCoins().get(PlayerColor.BLACK),1);
    }

    /**
     * Method getAllProfessors tests that at the start of the game each player has
     * not anyone professors.
     * */
    @Test
    public void getAllProfessors() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");

        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getAllProfessors().get(PlayerColor.WHITE).size(),0);
        assertEquals(handler.getAllProfessors().get(PlayerColor.BLACK).size(),0);
    }

    /**
     * Method getHalls tests that in the game the halls are 5 for each player.
     * */
    @Test
    public void getHalls() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");

        handler.getController().createGame();
        handler.setDecks();
        assertEquals(handler.getHalls().get(PlayerColor.WHITE).size(),5);
        assertEquals(handler.getHalls().get(PlayerColor.BLACK).size(),5);

    }


    /**
     * Method getMapPlayers tests the choice before the game start of the PlayerColor
     * is saved after the game started.
     *  */
    @Test
    public void getMapPlayers() {
        handler.checkDeck(AssistantName.KING,"Luca");
        handler.checkDeck(AssistantName.WITCH,"Sergio");
        handler.getController().createGame();
        handler.setDecks();
        handler.checkColor(PlayerColor.BLACK,"Luca");
        handler.checkColor(PlayerColor.WHITE,"Sergio");
        assertEquals(handler.getMapPlayers().get(PlayerColor.WHITE),"Sergio");
        assertEquals(handler.getMapPlayers().get(PlayerColor.BLACK),"Luca");

    }
}