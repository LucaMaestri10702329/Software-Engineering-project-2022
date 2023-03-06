package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class PlayerTest tests the methods of the class Player.
 */
public class PlayerTest {
    private final String nickname = "Sergio";
    private final PlayerColor color = PlayerColor.BLACK;
    private Board boardPlayer;
    private AssistantDeck deck;
    private ArrayList<AssistantCard> cards;
    private AssistantCard lastCardUsed;
    private int portfolio;
    private Player player;
    /**
     * Method setUp sets the attributes of the class Player with a certain values, that will be used for the tests of the
     * methods of the class Player.
     */
    @BeforeEach
    public void setUp(){
        player = new Player(nickname,color);

        boardPlayer=player.getBoardPlayer();
        int i;
        cards = new ArrayList<>();
        int j=1;
        for(i=1;i<=10;i++)
        {
            AssistantCard card = new AssistantCard(i, j);
            if(i%2 == 0)
                j++;
            cards.add(card);
        }
        deck = new AssistantDeck(cards,AssistantName.KING);
        player.setCards(deck);
        lastCardUsed = cards.get(1);
        player.setLastCardUsed(lastCardUsed);
        portfolio = 1;
    }

    /**
     * Method testSetCards tests the method setCards.
     */
    @Test
    public void testSetCards() {

        assertNotNull(player.getCards());
        assertEquals(player.getCards().getCards().size(),cards.size());
    }

    /**
     * Method testGetCards tests the method getCards.
     */
    @Test
    public void testGetCards() {

        assertEquals(player.getCards().getWizardName(),AssistantName.KING);
        assertEquals(player.getCards(),deck);
    }

    /**
     * Method testGetColor tests the method getColor.
     */
    @Test
    public void testGetColor() {
        assertEquals(player.getColor(),color);
    }

    /**
     * Method testGetNickname tests the method getNickname.
     */
    @Test
    public void testGetNickname() {
        assertEquals(player.getNickname(),nickname);
    }

    /**
     * Method testGetLastCardUsed tests the method getLastCardUsed.
     */
    @Test
    public void testGetLastCardUsed() {
        assertEquals(player.getLastCardUsed(),lastCardUsed);
        assertEquals(player.getLastCardUsed(),lastCardUsed);
    }

    /**
     * Method testSetLastCardUsed tests the method setLastCardUsed.
     */
    @Test
    public void testSetLastCardUsed() {
        player.setLastCardUsed(cards.get(2));
        assertNotEquals(player.getLastCardUsed(),lastCardUsed);
        assertEquals(cards.get(2).getPlayerValue(),3);
    }

    /**
     * Method testGetPortfolio tests the method getPortfolio.
     */
    @Test
    public void testGetPortfolio() {
        assertEquals(player.getPortfolio(),portfolio);
    }

    /**
     * Method testSetPortfolio tests the method setPortfolio.
     */
    @Test
    public void testSetPortfolio() {
        player.setPortfolio(2);
        assertNotEquals(player.getPortfolio(),portfolio);
    }

    /**
     * Method testGetBoardPlayer tests the method getBoardPlayer.
     */
    @Test
    public void testGetBoardPlayer() {
        assertEquals(player.getBoardPlayer(),boardPlayer);
    }

    /**
     * Method testRemoveCard tests the method removeCard with all the possible numbers of cards that the player
     * could have before the remove of the card.
     */
    @Test
    public void testRemoveCard() {
        int oldSize=player.getCards().getCards().size();
        if(oldSize >= 1)
        {
            try{
                player.removeCard(player.getCards().getCards().get(0));

            } catch (Exception e){
                fail();
            }
            assertEquals(player.getCards().getCards().size(),oldSize-1);

            try{
                for(int i =0 ;i<oldSize-2;i++)
                    player.removeCard(player.getCards().getCards().get(0));

            } catch (Exception e){
                fail();
            }

            try{
                player.removeCard(player.getCards().getCards().get(0));
                fail();
            } catch (Exception e){
                assertEquals(player.getCards().getCards().size(),0);
            }
        }
        else
            fail();
        try{
            cards.add(0,null);
            deck=new AssistantDeck(cards,AssistantName.KING);
            assertFalse(deck.remove(cards.get(0)));
        }catch(Exception e){
            fail();
        }
    }

    /**
     * Method testCalculateInfluence tests the method calculateInfluence in two particular situations.
     */
    @Test
    public void testCalculateInfluence() {
        Color studentColor = Color.RED;
        Island island = new Island(2);
        island.insertStudent(studentColor);
        Color prof = Color.RED;
        boardPlayer.insertProf(prof);

        player.calculateInfluence(island);
        assertEquals(player.getInfluence(),1);

        Island island1 = new Island(4);
        island1.insertStudent(studentColor);
        island1.setNumberOfLink(2);
        island1.setTowers();
        island1.setOwner(player.getColor());
        player.calculateInfluence(island1);
        assertEquals(player.getInfluence(),4);

    }

    /**
     * Method testAddCoin tests the method addCoin.
     */
    @Test
    public void testAddCoin() {
        player.addCoin();
        assertEquals(portfolio+1,player.getPortfolio());
    }

    /**
     * Method testRemoveCoin tests the method removeCoin.
     */
    @Test
    public void testRemoveCoin() {
        CharacterCard card = new Jolly();
        player.setPortfolio(1);
        player.removeCoin(card);
        assertEquals(0,player.getPortfolio());
    }



}