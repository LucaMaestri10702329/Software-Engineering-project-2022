package it.polimi.ingsw.model;

import it.polimi.ingsw.exception.EndGameException;
import it.polimi.ingsw.exception.EndRoundException;
import it.polimi.ingsw.exception.WinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests the methods of the class Game.
 */
public class GameTest {
    private ArrayList<Player> players;
    private HashMap<Color, Integer> bag;
    private Player currentPlayer;
    private int motherNaturePos;
    private ArrayList<CharacterCard> characterCards;

    private int currentPlayerIndex;

    private Island activeIsland;
    private ArrayList<AssistantCard>cards;


    Game game;

    /**
     * Method setUp creates some instances of objects and sets the attributes of the class Game with the default
     * values, that will be used for the tests of the methods of the class Game.
     * @throws EndRoundException if the game is not create correctly.
     */
    @BeforeEach
    public void setUp() throws EndRoundException {
        players = new ArrayList<>();
        bag = new HashMap<>();
        ArrayList<Island> islands = new ArrayList<>();
        characterCards = new ArrayList<>();
        HashMap<Color, Integer> students = new HashMap<>();
        cards = new ArrayList<>();


        Player p1 = new Player("Sergio",PlayerColor.WHITE);
        Player p2 = new Player("Luca",PlayerColor.BLACK);
        Player p3 = new Player("Luca",PlayerColor.GRAY);
        players.add(0,p1);
        players.add(1,p2);
        players.add(2,p3);
        game = new Game(true,players);
        game.init();


        Island i1 = new Island(0);
        Island i2 = new Island(1);
        Island i3 = new Island(2);
        Island i4 = new Island(3);
        Island i5 = new Island(4);
        Island i6 = new Island(5);
        Island i7 = new Island(6);
        Island i8 = new Island(7);
        Island i9 = new Island(8);
        Island i10 = new Island(9);
        Island i11 = new Island(10);
        Island i12 = new Island(11);

        islands.add(0,i1);
        islands.add(1,i2);
        islands.add(2,i3);
        islands.add(3,i4);
        islands.add(4,i5);
        islands.add(5,i6);
        islands.add(6,i7);
        islands.add(7,i8);
        islands.add(8,i9);
        islands.add(9,i10);
        islands.add(10,i11);
        islands.add(11,i12);

        motherNaturePos = 1;
        game.setMotherNaturePos(1);

        activeIsland = islands.get(motherNaturePos);
        i2.insertStudent(Color.RED);
        i3.insertStudent(Color.BLUE);
        i4.insertStudent(Color.YELLOW);
        i5.insertStudent(Color.PURPLE);
        i6.insertStudent(Color.GREEN);
        i8.insertStudent(Color.RED);
        i9.insertStudent(Color.BLUE);
        i10.insertStudent(Color.PURPLE);
        i11.insertStudent(Color.GREEN);
        i12.insertStudent(Color.YELLOW);

        p1.setPortfolio(1);
        p2.setPortfolio(1);

        CharacterCard centaur = new Centaur();
        CharacterCard florist = new Florist();
        CharacterCard friar = new Friar();
        CharacterCard innkeeper = new Innkeeper();
        CharacterCard jolly = new Jolly ();
        CharacterCard knight = new Knight ();
        CharacterCard messenger = new Messenger();
        CharacterCard mushroomsMan = new MushroomsMan ();
        CharacterCard pirate = new Pirate ();
        CharacterCard princess = new Princess ();
        CharacterCard thief = new Thief ();
        CharacterCard violinist = new Violinist();

        characterCards.add(0,centaur);
        characterCards.add(1,florist);
        characterCards.add(2,friar);
        characterCards.add(3,innkeeper);
        characterCards.add(4,jolly);
        characterCards.add(5,knight);
        characterCards.add(6,messenger);
        characterCards.add(7,mushroomsMan);
        characterCards.add(8,pirate);
        characterCards.add(9,princess);
        characterCards.add(10,thief);
        characterCards.add(11,violinist);

        students.put(Color.RED,3);
        students.put(Color.BLUE,2);
        students.put(Color.YELLOW,1);
        jolly.setStudents(students);





        int i;
        for(i=0;i<3;i++)
        {
            p1.getBoardPlayer().insertStudentEntrance(Color.PURPLE);
        }
        for(i=0;i<3;i++)
        {
            p1.getBoardPlayer().insertStudentEntrance(Color.GREEN);
        }

        for(i=0;i<2;i++)
        {
            p2.getBoardPlayer().insertStudentEntrance(Color.BLUE);

        }
        for(i=0;i<2;i++)
        {
            p2.getBoardPlayer().insertStudentEntrance(Color.RED);

        }
        for(i=0;i<3;i++)
        {
            p2.getBoardPlayer().insertStudentEntrance(Color.YELLOW);

        }




        bag.put(Color.RED,18);
        bag.put(Color.BLUE,19);
        bag.put(Color.GREEN,18);
        bag.put(Color.PURPLE,19);
        bag.put(Color.YELLOW,20);

        cards = new ArrayList<>();

        int j=1;
        for(i=1;i<=10;i++)
        {
            if(i%2 == 0)
                j++;
            cards.add(i-1,new AssistantCard(i,j));
        }
        AssistantDeck deck1 = new AssistantDeck(cards,AssistantName.KING);
        p1.setCards(deck1);

        AssistantDeck deck2 = new AssistantDeck(cards,AssistantName.WITCH);
        p2.setCards(deck2);

        currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
        game.getActivePlayer().setLastCardUsed(cards.get(9));
        activeIsland = islands.get(motherNaturePos);

        game.getActivePlayer().getBoardPlayer().setHall(Color.PURPLE,3);


        game.setBag(bag);
        game.setSizeEntrance(7);
        game.setNumberClouds(2);

    }

    /**
     * Method testGetSizeEntrance tests the method getSizeEntrance.
     */
    @Test
    public void testGetSizeEntrance(){
        assertEquals(game.getSizeEntrance(),7);
    }

    /**
     * Method testGetCharacterCards sets the method getCharacterCards.
     */
    @Test
    public void testGetCharacterCards() {
        assertEquals(game.getCharacterCards().size(),3);
    }

    /**
     * Method testGetIslands tests the method getIslands.
     */
    @Test
    public void testGetIslands() {
        for(int i=0;i<12;i++ )
        {
            assertNotNull(game.getIslands().get(i));
        }
    }

    /**
     * Method testSetMotherNaturePos tests the method setMotherNaturePos.
     */
    @Test
    public void testSetMotherNaturePos() {
        game.setMotherNaturePos(2);
        assertNotEquals(game.getMotherNaturePos(),motherNaturePos);
    }

    /**
     * Method testGetMotherNaturePos tests the method getMotherNaturePos.
     */
    @Test
    public void testGetMotherNaturePos() {
        assertEquals(game.getMotherNaturePos(),motherNaturePos);
    }

    /**
     * Method testGetPlayers tests the method getPlayers.
     */
    @Test
    public void testGetPlayers() {
        for (int i=0;i<2;i++) {
            assertEquals(players.get(i).getNickname(),game.getPlayers().get(i).getNickname());
            assertEquals(players.get(i).getColor(),game.getPlayers().get(i).getColor());
        }
    }

    /**
     * Method testSetPlayers tests the method setPlayers.
     */
    @Test
    public void testSetPlayers() {
        game.setPlayers(players);
        assertNotNull(game.getPlayers());
    }

    /**
     * Method testCreateNewPlayer tests the method createNewPlayer.
     */
    @Test
    public void testCreateNewPlayer() {
       assertTrue( game.createNewPlayer(new Player("Luca",PlayerColor.GRAY)));
    }

    /**
     * Method testSetNextPlayer tests the method setNextPlayer.
     */
    @Test
    public void testSetNextPlayer() {
        game.setNextPlayer();
        assertNotEquals(currentPlayerIndex,game.getCurrentPlayerIndex());
    }

    /**
     * Method testGetActivePlayer tests the method getActivePlayer.
     */
    @Test
    public void testGetActivePlayer() {
        assertEquals(currentPlayer.getNickname(),game.getActivePlayer().getNickname());
        assertEquals(currentPlayer.getColor(),game.getActivePlayer().getColor());
    }

    /**
     * Method testShiftMotherNature tests the method shiftMotherNature in different ways:
     * -tests if a ban tile is in island where it will go mother nature;
     * -tests if a student is present on the island and a player has that professor.
     */
    @Test
    public void testShiftMotherNature() {
        int oldInfluence,newInfluence;
        try{
            game.getCharacterCards().add(characterCards.get(1));
            characterCards.get(1).setNumOfBanTile(1);
            int oldBanTile = characterCards.get(1).getNumOfBanTile();
            game.getIslands().get(4).setBanTile(3);
            game.shiftMotherNature(3);
            assertEquals(oldBanTile+1,characterCards.get(1).getNumOfBanTile());
            assertEquals(2,game.getIslands().get(4).getBanTile());

        }catch (Exception e){
          fail();
        }
        assertEquals(game.getMotherNaturePos(),4);

        try{

            game.getCharacterCards().clear();
            game.getCharacterCards().add(characterCards.get(0));

            game.getActivePlayer().getBoardPlayer().insertProf(Color.RED);

            for(Color c : Color.values())
            {
                game.getIslands().get(5).getStudents().put(c,0);
            }
            game.getActivePlayer().getBoardPlayer().setHall(Color.RED,4);
            game.getIslands().get(5).insertStudent(Color.RED);
            game.getIslands().get(5).setTowers();
            assertEquals(game.getIslands().get(5).getTowers(),1);
            game.getIslands().get(5).setOwner(game.getActivePlayer().getColor());
            game.getActivePlayer().calculateInfluence(game.getIslands().get(5));
            oldInfluence = game.getActivePlayer().getInfluence();
            assertEquals(oldInfluence,2);
            game.setMotherNaturePos(4);
            characterCards.get(0).doAction(game);
            assertEquals(game.getCharacterCards().get(0).getName(),CharacterName.CENTAUR);
            game.shiftMotherNature(1);
            newInfluence = game.getActivePlayer().getInfluence();
            assertEquals(oldInfluence-1,newInfluence);

        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Method testDecideInfluence tests the method decideInfluence.
     */
    @Test
    public void testDecideInfluence() {


        game.getPlayers().get(0).setInfluence(0);
        game.getPlayers().get(1).setInfluence(2);

        try {
            game.decideInfluence();
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(game.getIslands().get(game.getMotherNaturePos()).getOwner(),game.getPlayers().get(1).getColor());

    }

    /**
     * Method testGetActiveIsland tests the method getActiveIsland.
     */
    @Test
    public void testGetActiveIsland() {
        game.setMotherNaturePos(7);
        assertNotEquals(activeIsland,game.getActiveIsland());
    }

    /**
     * Method testMerge tests the method merge in different ways:
     * -a normal merge between two islands;
     * -when three islands are the only islands remaining on the game.
     */
    @Test
    public void testMerge() {
        try{
            int oldSize = game.getIslands().size(),oldLink=game.getIslands().get(7).getNumberOfLink(),oldMerge=game.getNumOfMerge();
            game.merge(7,8);
            assertEquals(oldMerge+1,game.getNumOfMerge());
            assertEquals(oldSize-1,game.getIslands().size());
            assertEquals(oldLink+1,game.getIslands().get(7).getNumberOfLink());
        }catch (Exception e){
            fail();
        }
        try{
            game.merge(0,1);
            game.merge(0,1);
            game.merge(0,1);
            game.merge(0,1);
            game.merge(0,1);
            game.merge(0,1);
            game.merge(0,1);
        }
        catch(Exception e)
        {
            fail();
        }
        try{
            game.merge(0,1);
            fail();
        }
        catch(Exception e)
        {
            assertEquals(game.getIslands().size(),3);

        }

    }

    /**
     * Method testGetProfessors tests the method getProfessors.
     */
    @Test
    public void testGetProfessors(){
        assertNotNull(game.getProfessors());
        assertEquals(game.getProfessors().size(),5);
    }

    /**
     * Method testDrawFromBag tests the methods drawFromBag both the method that
     * receives the parameter in input and the other that haven't any parameters.
     */
    @Test
    public void testDrawFromBag() {
        int oldBag = 0, newBag=0,size = 0;
        try {

            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    oldBag += bag.get(c);
            }
            HashMap<Color,Integer> map = game.drawFromBag(5);
            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    newBag += bag.get(c);
            }
            for(Color c : Color.values()) {
                if(map.containsKey(c))
                    size += map.get(c);
            }
            assertEquals(5,size);
            assertEquals(oldBag-5,newBag);


            oldBag = newBag;
            game.drawFromBag();
            newBag = 0;
            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    newBag += bag.get(c);
            }

            assertEquals(oldBag-1,newBag);

        }
        catch(Exception e)
        {
            fail();
        }

        try{
            game.drawFromBag(88);
        }catch(Exception e){
            newBag = 0;
            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    newBag += bag.get(c);
            }
        }
        assertEquals(newBag,0);

    }


    /**
     * Method testGetBag tests the method getBag.
     */
    @Test
    public void testGetBag(){
        assertEquals(bag,game.getBag());
    }

    /**
     * Method testSetBag tests the method setBag.
     */
    @Test
    public void testSetBag(){
        HashMap<Color,Integer> newBag = new HashMap<>();
        for (Color c:Color.values()) {
            newBag.put(c,3);
        }
        game.setBag(newBag);
        assertNotEquals(bag,game.getBag());
    }

    /**
     * Method testMoveStudentToCloud tests the method moveStudentToCloud checking if
     * after the movement of three students from the bag to a cloud,
     * the size of the bag decrements of 3 and the size of the cloud increments of 3.
     */
    @Test
    public void testMoveStudentToCloud() {
        try{
            int oldSize = 0,oldBag =0 ;

            for(Color c : Color.values()) {
                if(game.getClouds().get(1).containsKey(c))
                    oldSize += game.getClouds().get(1).get(c);
            }
            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    oldBag += bag.get(c);
            }

            game.moveStudentToCloud(1,3);

            int newSize= 0;
            int newBag=0;
            for(Color c : Color.values()) {
                if(game.getClouds().get(1).containsKey(c))
                    newSize += game.getClouds().get(1).get(c);
            }
            for(Color c : Color.values()) {
                if(bag.containsKey(c))
                    newBag += bag.get(c);
            }

            assertEquals(oldSize+3,newSize);
            assertEquals(oldBag - 3,newBag);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Method testMoveTowerToIsland tests the method moveTowerToIsland.
     * @throws WinException if the towers on the board of the active player are finished.
     * @throws EndGameException if the game end because the islands remaining are only three.
     */
    @Test
    public void testMoveTowerToIsland() throws WinException, EndGameException {
        players.get(1).getBoardPlayer().setTowers(8);
        try {
            game.setMotherNaturePos(11);
            game.moveTowerToIsland(players.get(1));
        } catch(Exception e){
            fail();
        }
        assertEquals(game.getIslands().get(11).getTowers(),1);
        assertEquals(game.getIslands().get(11).getOwner(),players.get(1).getColor());


        try {
            game.setMotherNaturePos(10);
            game.moveTowerToIsland(players.get(1));
        } catch(Exception e){
            fail();
        }

        assertEquals(game.getIslands().get(10).getTowers(),2);
        assertEquals(game.getIslands().get(10).getOwner(),players.get(1).getColor());


        try {
            game.setMotherNaturePos(9);
            game.moveTowerToIsland(players.get(1));
        } catch(Exception e){
            fail();
        }
        assertEquals(game.getIslands().get(9).getTowers(),3);
        assertEquals(game.getIslands().get(9).getNumberOfLink(),2);
        assertEquals(game.getIslands().get(9).getOwner(),players.get(1).getColor());
        try{
            game.getPlayers().get(1).getBoardPlayer().setTowers(0);
            game.setMotherNaturePos(0);
            game.moveTowerToIsland(players.get(1));

        }catch(Exception e){
            assertFalse(players.get(1).getBoardPlayer().removeTowers(1));
        }

        game.getIslands().get(5).resetTowers();
        game.getIslands().get(5).setNumberOfLink(0);
        game.getIslands().get(4).setTowers();
        int oldTower = game.getIslands().get(4).getTowers();
        game.getIslands().get(4).setOwner(game.getActivePlayer().getColor());
        game.setMotherNaturePos(5);
        game.moveTowerToIsland(game.getActivePlayer());
        assertEquals(4,game.getMotherNaturePos());
        assertEquals(oldTower + 1,game.getIslands().get(4).getTowers());
        assertEquals(1,game.getIslands().get(4).getNumberOfLink());

        game.getIslands().removeAll(game.getIslands());
        for(int i=0;i<=11;i++)
        {
            game.getIslands().add(new Island(i));
        }
        game.setNumOfMerge(0);
        game.getIslands().get(0).resetTowers();
        game.getIslands().get(0).setNumberOfLink(0);
        game.getIslands().get(11).resetTowers();
        game.getIslands().get(11).setTowers();
        game.getIslands().get(11).setOwner(game.getActivePlayer().getColor());
        game.setMotherNaturePos(0);
        game.moveTowerToIsland(game.getActivePlayer());
        assertEquals(0,game.getMotherNaturePos());
        assertEquals(2,game.getIslands().get(0).getTowers());

        game.getIslands().removeAll(game.getIslands());
        for(int i=0;i<=11;i++)
        {
            game.getIslands().add(new Island(i));
        }
        game.setNumOfMerge(0);
        game.getIslands().get(11).resetTowers();
        game.getIslands().get(11).setNumberOfLink(0);
        game.getIslands().get(0).resetTowers();
        game.getIslands().get(0).setTowers();
        game.getIslands().get(0).setOwner(game.getActivePlayer().getColor());
        game.setMotherNaturePos(11);
        game.moveTowerToIsland(game.getActivePlayer());
        assertEquals(0,game.getMotherNaturePos());
        assertEquals(2,game.getIslands().get(0).getTowers());

        game.getActivePlayer().getBoardPlayer().setTowers(0);
        try{
            game.moveTowerToIsland(game.getActivePlayer());

        }catch(WinException e){
            assertEquals(game.getActivePlayer().getBoardPlayer().getTowers(),0);

        }

    }

    /**
     * Method testMoveStudentToEntrance tests the method moveStudentToEntrance.
     */
    @Test
    public void testMoveStudentToEntrance() {
        Color colorStudent = Color.PURPLE;


        int old = game.getActivePlayer().getBoardPlayer().getEntrance().get(colorStudent);

        assertTrue(game.moveStudentToEntrance(colorStudent));
        assertEquals(game.getActivePlayer().getBoardPlayer().getEntrance().get(colorStudent),old+1);

        if(game.moveStudentToEntrance(colorStudent))
            fail();

        int cont = 0;
        for(Color c:Color.values()) {
            if(game.getActivePlayer().getBoardPlayer().getEntrance().containsKey(c))
                cont += game.getActivePlayer().getBoardPlayer().getEntrance().get(c);
        }
        assertEquals(cont,7);


    }

    /**
     * Method testMoveStudentToHall tests the method moveStudentToHall.
     * @throws EndRoundException if the card ended.
     * @throws WinException if the character skill made the player won.
     * @throws EndGameException if the game end.
     */
    @Test
    public void testMoveStudentToHall() throws EndRoundException, EndGameException, WinException {
        game.setCoins(20);
        Color colorStudent = Color.PURPLE;
        int old = game.getActivePlayer().getBoardPlayer().getHall(colorStudent);
        int old2 = game.getActivePlayer().getBoardPlayer().getEntrance().get(colorStudent);

        game.moveStudentToHall(colorStudent);
        assertEquals(game.getActivePlayer().getBoardPlayer().getProfessors().get(0),Color.PURPLE);
        assertEquals(game.getActivePlayer().getBoardPlayer().getHall(colorStudent),old+1);
        assertNotEquals(0,game.getActivePlayer().getBoardPlayer().getHall(colorStudent));
        assertEquals(game.getActivePlayer().getBoardPlayer().getEntrance().get(colorStudent),old2-1);
        game.moveStudentToHall(colorStudent);
        game.moveStudentToHall(colorStudent);
        assertEquals(game.getCoins(),19);
        assertEquals(game.getActivePlayer().getPortfolio(),2);
        game.moveStudentToHall(colorStudent);


        assertEquals(game.getActivePlayer().getBoardPlayer().getProfessors().size(),1);

        game.getPlayers().get(1).getBoardPlayer().insertProf(Color.YELLOW);
        game.getPlayers().get(1).getBoardPlayer().setHall(Color.YELLOW,1);

        game.getPlayers().get(0).getBoardPlayer().insertStudentEntrance(Color.YELLOW);
        game.getPlayers().get(0).getBoardPlayer().insertStudentEntrance(Color.YELLOW);
        game.getPlayers().get(0).getBoardPlayer().insertStudentEntrance(Color.YELLOW);
        game.getPlayers().get(0).getBoardPlayer().insertStudentEntrance(Color.YELLOW);

        game.moveStudentToHall(Color.YELLOW);
        game.moveStudentToHall(Color.YELLOW);
        game.moveStudentToHall(Color.YELLOW);
        game.moveStudentToHall(Color.YELLOW);



        assertEquals(game.getActivePlayer().getBoardPlayer().getHall(Color.YELLOW),4);
        assertEquals(game.getPlayers().get(1).getBoardPlayer().getHall(Color.YELLOW),1);


        assertTrue(game.getActivePlayer().getBoardPlayer().getProfessors().contains(Color.YELLOW));

        assertEquals(game.getActivePlayer().getBoardPlayer().getProfessors().size(),2);
        assertEquals(game.getActivePlayer().getBoardPlayer().getProfessors().get(1),Color.YELLOW);


        game.moveStudentToHall(colorStudent);
        game.moveStudentToHall(colorStudent);
        if(!game.moveStudentToHall(colorStudent))
            assertEquals(game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.PURPLE),0);
        else
            fail();

        game.getPlayers().get(1).getBoardPlayer().setHall(Color.RED,1);
        game.getPlayers().get(1).getBoardPlayer().insertProf(Color.RED);
        characterCards.get(3).doAction(game);
        game.getCharacterCards().remove(game.getCharacterCards().get(0));
        game.getCharacterCards().add(characterCards.get(3));
        assertTrue(characterCards.get(3).isEffect());
        assertEquals(characterCards.get(3).getName(), CharacterName.INNKEEPER);
        game.getActivePlayer().getBoardPlayer().insertStudentEntrance(Color.RED);
        game.moveStudentToHall(Color.RED);
        assertTrue(game.getActivePlayer().getBoardPlayer().getProfessors().contains(Color.RED));
    }


    /**
     * Method testMoveStudentToIsland tests the method moveStudentToIsland in different ways.
     */
    @Test
    public void testMoveStudentToIsland() {
        int oldSize = game.getIslands().get(6).getNumStudents();
        int num = game.getPlayers().get(0).getBoardPlayer().getEntrance().get(Color.PURPLE);
        if(game.moveStudentToIsland(Color.PURPLE,6)) {
            assertEquals(game.getPlayers().get(0).getBoardPlayer().getEntrance().get(Color.PURPLE), num- 1);
            assertNotEquals(0, game.getIslands().get(6).getStudents().size());
            assertEquals(oldSize + 1, game.getIslands().get(6).getNumStudents());
        }
        else {
            fail();
        }
        if(!game.moveStudentToIsland(Color.PURPLE,6))
            fail();
        if(!game.moveStudentToIsland(Color.PURPLE,6))
            fail();
        if(!game.moveStudentToIsland(Color.PURPLE,6))
            assertEquals(game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.PURPLE),0);


    }

    /**
     * Method testStudentsFromCloudToEntrance tests the method studentsFromCloudToEntrance.
     */
    @Test
    public void testStudentsFromCloudToEntrance(){

        game.getActivePlayer().getBoardPlayer().getEntrance().clear();

        try {
            game.moveStudentToCloud(1, 3);
        }
        catch(Exception e)
        {
            fail();
        }
        int oldSizeCloud = 0;
        for(Color c : Color.values()) {
            if(game.getClouds().get(1).containsKey(c))
                oldSizeCloud += game.getClouds().get(1).get(c);
        }
        int oldSizeEntrance = 0;
        for(Color c : Color.values()) {
            if(game.getActivePlayer().getBoardPlayer().getEntrance().containsKey(c))
                oldSizeEntrance += game.getActivePlayer().getBoardPlayer().getEntrance().get(c);
        }


        game.studentsFromCloudToEntrance(1);

        assertEquals(game.getClouds().get(1).size(), 0);

        int newSizeEntrance = 0;
        for(Color c : Color.values()) {
            if(game.getActivePlayer().getBoardPlayer().getEntrance().containsKey(c))
                newSizeEntrance += game.getActivePlayer().getBoardPlayer().getEntrance().get(c);
        }

        assertEquals(newSizeEntrance, oldSizeEntrance+oldSizeCloud);
    }

    /**
     * Method testCoins tests both methods getCoins and setCoins.
     */
    @Test
    public void testCoins(){
        game.setCoins(20);
        assertEquals(game.getCoins(),20);
    }

    /**
     * Method testGameNoExpert tests the creation of a new game in which the game
     * is not in expert mode.
     * @throws EndRoundException if the creation of the game is not correct.
     */
    @Test
    public void testGameNoExpert() throws EndRoundException {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Marco",PlayerColor.BLACK));
        players.add(new Player("Giovanni",PlayerColor.WHITE));
        Game game = new Game(false,players);
        game.setNumberClouds(2);
        game.init();
        assertEquals(game.getCoins(),0);
    }

    /**
     * Method CentaurTest tests both methods doEffect and doAction of the class Centaur.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void CentaurTest() throws EndRoundException, WinException, EndGameException {
        assertEquals(0,characterCards.get(0).getName().compareTo(CharacterName.CENTAUR));
        int oldInfluence,newInfluence,oldPrice,newPrice;
        oldPrice = characterCards.get(0).getPrice();
        game.getIslands().get(7).insertStudent(Color.PURPLE);
        game.getIslands().get(7).setTowers();
        players.get(1).getBoardPlayer().insertProf(Color.PURPLE);
        game.setMotherNaturePos(7);
        game.getIslands().get(7).setOwner(game.getPlayers().get(1).getColor());
        players.get(1).calculateInfluence(game.getIslands().get(7));
        oldInfluence = players.get(1).getInfluence();
        characterCards.get(0).doAction(game);
        characterCards.get(0).doEffect(game);
        newPrice = characterCards.get(0).getPrice();
        newInfluence =  players.get(1).getInfluence();
        assertEquals(oldInfluence-1,newInfluence);
        assertEquals(oldPrice + 1,newPrice);
    }

    /**
     * Method FloristTest tests the method doAction of the class Florist.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void FloristTest() throws EndRoundException, WinException, EndGameException {
        int oldBanTile,newBanTile,oldPrice,newPrice;
        oldBanTile = characterCards.get(1).getNumOfBanTile();
        oldPrice = characterCards.get(1).getPrice();
        characterCards.get(1).setIslandChosen(7);
        characterCards.get(1).doAction(game);
        newBanTile = characterCards.get(1).getNumOfBanTile();
        newPrice = characterCards.get(1).getPrice();
        assertEquals(oldBanTile - 1,newBanTile);
        assertEquals(oldPrice +1, newPrice);
    }

    /**
     * Method ViolinistTest tests the method doAction of the class Violinist.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void ViolinistTest() throws EndRoundException, WinException, EndGameException {
        int oldEntrance1,oldEntrance2,oldPrice,oldHall,newPrice;
        ArrayList<Color> source = new ArrayList<>();
        ArrayList<Color> destination = new ArrayList<>();

        oldPrice = characterCards.get(11).getPrice();
        game.getActivePlayer().getBoardPlayer().setHall(Color.PURPLE,2);
        game.getActivePlayer().getBoardPlayer().insertProf(Color.PURPLE);
        oldHall = game.getActivePlayer().getBoardPlayer().getHall(Color.PURPLE);
        game.getActivePlayer().getBoardPlayer().insertStudentEntrance(Color.BLUE);
        game.getActivePlayer().getBoardPlayer().insertStudentEntrance(Color.GREEN);
        oldEntrance1 = game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.BLUE);
        oldEntrance2 = game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.GREEN);

        source.add(Color.BLUE);
        source.add(Color.GREEN);
        destination.add(Color.PURPLE);
        destination.add(Color.PURPLE);


        characterCards.get(11).setSource(source);
        characterCards.get(11).setDestination(destination);
        characterCards.get(11).doAction(game);
        newPrice = characterCards.get(11).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(oldHall-2,game.getActivePlayer().getBoardPlayer().getHall(Color.PURPLE));
        assertEquals(oldEntrance1-1,game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.BLUE));
        assertEquals(oldEntrance2-1,game.getActivePlayer().getBoardPlayer().getEntrance().get(Color.GREEN));

        game.getPlayers().get(1).getBoardPlayer().setHall(Color.PURPLE,2);
        source.clear();
        destination.clear();
        source.add(Color.BLUE);
        source.add(Color.GREEN);
        destination.add(Color.PURPLE);
        destination.add(Color.PURPLE);

        characterCards.get(11).doAction(game);

        assertTrue(game.getPlayers().get(1).getBoardPlayer().getProfessors().contains(Color.PURPLE));
        assertFalse(game.getPlayers().get(0).getBoardPlayer().getProfessors().contains(Color.PURPLE));

        game.getPlayers().get(0).getBoardPlayer().setHall(Color.YELLOW,2);
        game.getPlayers().get(0).getBoardPlayer().insertProf(Color.YELLOW);
        source.clear();
        destination.clear();
        source.add(Color.PURPLE);
        source.add(Color.PURPLE);
        destination.add(Color.YELLOW);
        destination.add(Color.YELLOW);

        characterCards.get(11).doAction(game);
        assertFalse(game.getPlayers().get(0).getBoardPlayer().getProfessors().contains(Color.YELLOW));

    }

    /**
     * Method ThiefTest tests the method doAction of the class Thief.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void ThiefTest() throws EndRoundException, WinException, EndGameException {
        int oldPrice,newPrice;
        oldPrice = characterCards.get(10).getPrice();
        characterCards.get(10).setStudentColorForEffects(Color.YELLOW);
        players.get(0).getBoardPlayer().setHall(Color.YELLOW,3);

        players.get(1).getBoardPlayer().setHall(Color.YELLOW,-8);
        players.get(1).getBoardPlayer().setHall(Color.PURPLE,-8);
        players.get(1).getBoardPlayer().setHall(Color.RED,-2);
        players.get(1).getBoardPlayer().setHall(Color.GREEN,-8);
        players.get(1).getBoardPlayer().setHall(Color.BLUE,-8);

        players.get(1).getBoardPlayer().setHall(Color.YELLOW,1);
        characterCards.get(10).doAction(game);
        newPrice = characterCards.get(10).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(0,players.get(0).getBoardPlayer().getHall(Color.YELLOW));
        assertEquals(0,players.get(1).getBoardPlayer().getHall(Color.YELLOW));
        players.get(0).getBoardPlayer().setHall(Color.YELLOW,2);
        characterCards.get(10).doAction(game);
        assertEquals(0,players.get(0).getBoardPlayer().getHall(Color.YELLOW));


        game.getPlayers().get(0).getBoardPlayer().setHall(Color.RED,1);
        characterCards.get(10).setStudentColorForEffects(Color.RED);
        game.getPlayers().get(0).getBoardPlayer().insertProf(Color.RED);
        characterCards.get(10).doAction(game);
        assertFalse(game.getPlayers().get(0).getBoardPlayer().getProfessors().contains(Color.RED));
    }

    /**
     * Method PrincessTest tests the method doAction of the class Princess.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void PrincessTest() throws EndRoundException, WinException, EndGameException {
        int oldPrice,newPrice;
        int oldHall,oldStudPrincess;
        HashMap<Color,Integer> map = new HashMap<>();
        oldPrice = characterCards.get(9).getPrice();
        oldHall= game.getActivePlayer().getBoardPlayer().getHall(Color.PURPLE);
        //map.clear();
        map.put(Color.BLUE,3);
        map.put(Color.PURPLE,1);

        characterCards.get(9).setStudents(map);
        oldStudPrincess = characterCards.get(9).getNumOfStudents();
        characterCards.get(9).setStudentColorForEffects(Color.PURPLE);
        characterCards.get(9).doAction(game);
        newPrice = characterCards.get(9).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(oldHall+1,game.getActivePlayer().getBoardPlayer().getHall(Color.PURPLE));
        assertEquals(oldStudPrincess,characterCards.get(9).getNumOfStudents());
        game.getPlayers().get(1).getBoardPlayer().insertProf(Color.BLUE);
        characterCards.get(9).setStudentColorForEffects(Color.BLUE);
        characterCards.get(9).doAction(game);
        assertTrue(game.getActivePlayer().getBoardPlayer().getProfessors().contains(Color.BLUE));
        assertFalse(game.getPlayers().get(1).getBoardPlayer().getProfessors().contains(Color.BLUE));
    }

    /**
     * Method PirateTest tests the method doAction of the class Pirate.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void PirateTest() throws EndRoundException, WinException, EndGameException {
        int oldPrice,newPrice;
        oldPrice = characterCards.get(8).getPrice();
        int oldTower = game.getIslands().get(5).getTowers();
        for(Color c:Color.values())
        {
            players.get(0).getBoardPlayer().removeProf(c);
        }
        game.setMotherNaturePos(3);
        for(Color c: Color.values())
        {
            game.getIslands().get(3).getStudents().put(c,0);
            game.getIslands().get(5).getStudents().put(c,0);
        }
        game.getIslands().get(3).insertStudent(Color.GREEN);
        game.getIslands().get(5).insertStudent(Color.GREEN);
        game.getIslands().get(5).insertStudent(Color.GREEN);
        game.getPlayers().get(0).getBoardPlayer().insertProf(Color.GREEN);
        characterCards.get(8).setIslandChosen(5);
        characterCards.get(8).doAction(game);
        newPrice = characterCards.get(8).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(3,game.getMotherNaturePos());
        assertEquals(2,game.getPlayers().get(0).getInfluence());
        assertEquals(oldTower+1,game.getIslands().get(5).getTowers());
    }

    /**
     * Method MushroomsManTest tests both methods doAction and doEffect of the class MushroomsMan.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void MushroomsManTest() throws EndRoundException, WinException, EndGameException {
        int oldPrice,oldInfluence,newPrice;
        oldPrice = characterCards.get(7).getPrice();
        game.setMotherNaturePos(10);
        for(Color c:Color.values())
        {
            players.get(1).getBoardPlayer().removeProf(c);
        }
        for(Color c: Color.values())
        {
            game.getIslands().get(10).getStudents().put(c,0);
        }


        players.get(1).getBoardPlayer().insertProf(Color.RED);
        players.get(1).getBoardPlayer().insertProf(Color.YELLOW);
        game.getIslands().get(10).insertStudent(Color.RED);
        game.getIslands().get(10).insertStudent(Color.RED);
        game.getIslands().get(10).insertStudent(Color.YELLOW);
        players.get(1).calculateInfluence(game.getIslands().get(10));
        oldInfluence = players.get(1).getInfluence();
        game.setMotherNaturePos(10);
        characterCards.get(7).setStudentColorForEffects(Color.RED);
        characterCards.get(7).doAction(game);
        characterCards.get(7).doEffect(game);
        newPrice = characterCards.get(7).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertFalse(characterCards.get(7).isEffect());
        assertEquals(oldInfluence-2,players.get(1).getInfluence());

        game.setMotherNaturePos(7);
        for(Color c:Color.values())
        {
            players.get(0).getBoardPlayer().removeProf(c);
        }
        for(Color c: Color.values())
        {
            game.getIslands().get(7).getStudents().put(c,0);
        }
        game.getIslands().get(7).insertStudent(Color.YELLOW);
        game.getIslands().get(7).insertStudent(Color.BLUE);
        players.get(0).getBoardPlayer().insertProf(Color.YELLOW);
        players.get(0).getBoardPlayer().insertProf(Color.BLUE);
        game.getIslands().get(7).setTowers();
        game.getIslands().get(7).setOwner(players.get(0).getColor());
        players.get(0).calculateInfluence(game.getIslands().get(7));
        oldInfluence = players.get(0).getInfluence();
        characterCards.get(7).setStudentColorForEffects(Color.BLUE);
        characterCards.get(7).doAction(game);
        characterCards.get(7).doEffect(game);
        assertEquals(oldInfluence-1,players.get(0).getInfluence());
    }

    /**
     * Method MessengerTest tests the method doAction of the class Messenger.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void MessengerTest() throws EndRoundException, WinException, EndGameException {
        int oldStep,oldPrice,newPrice;
        oldPrice = characterCards.get(6).getPrice();
        game.getActivePlayer().setLastCardUsed(cards.get(0));
        oldStep = game.getActivePlayer().getLastCardUsed().getNumberStep();
        characterCards.get(6).doAction(game);
        newPrice = characterCards.get(6).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(oldStep+2,game.getActivePlayer().getLastCardUsed().getNumberStep());
    }

    /**
     * Method KnightTest tests both methods doAction and doEffect of the class Knight.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void KnightTest() throws EndRoundException, WinException, EndGameException {
        int oldPrice,oldInfluence,newPrice,newInfluence;
        oldPrice = characterCards.get(5).getPrice();
        game.getIslands().get(5).insertStudent(Color.RED);
        game.setMotherNaturePos(5);
        game.getPlayers().get(0).getBoardPlayer().insertProf(Color.RED);
        game.getPlayers().get(0).calculateInfluence(game.getIslands().get(5));
        oldInfluence = game.getPlayers().get(0).getInfluence();
        characterCards.get(5).doAction(game);
        characterCards.get(5).doEffect(game);
        newInfluence = game.getPlayers().get(0).getInfluence();
        newPrice = characterCards.get(5).getPrice();
        assertEquals(oldPrice +1, newPrice);
        assertEquals(oldInfluence+2,newInfluence);
    }

    /**
     * Method JollyTest tests the method doAction of the class Jolly.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void JollyTest() throws EndRoundException, WinException, EndGameException {
        HashMap<Color,Integer> map = new HashMap<>();
        int oldStudJolly,oldStudEntrance,oldPrice,newPrice;
        ArrayList<Color> source = new ArrayList<>();
        ArrayList<Color> destination = new ArrayList<>();
        destination.add(Color.RED);
        destination.add(Color.RED);
        destination.add(Color.RED);
        source.add(Color.BLUE);
        source.add(Color.BLUE);
        source.add(Color.BLUE);
        //map.clear();
        map.put(Color.GREEN,3);
        map.put(Color.BLUE,3);
        characterCards.get(4).setSource(source);
        characterCards.get(4).setDestination(destination);
        characterCards.get(4).setStudents(map);
        players.get(0).getBoardPlayer().insertStudentEntrance(Color.RED);
        players.get(0).getBoardPlayer().insertStudentEntrance(Color.RED);
        players.get(0).getBoardPlayer().insertStudentEntrance(Color.RED);
        oldPrice = characterCards.get(4).getPrice();
        oldStudJolly = characterCards.get(4).getNumOfStudents();
        oldStudEntrance = players.get(0).getBoardPlayer().getEntrance().get(Color.RED);
        characterCards.get(4).doAction(game);
        newPrice = characterCards.get(4).getPrice();
        assertEquals(oldStudJolly,characterCards.get(4).getNumOfStudents());
        assertEquals(oldStudEntrance,players.get(0).getBoardPlayer().getEntrance().get(Color.BLUE));
        assertEquals(oldPrice +1, newPrice);
    }

    /**
     * Method InnkeeperTest tests the method doAction of the class Innkeeper.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void InnkeeperTest() throws EndRoundException, WinException, EndGameException {
        int oldProf0,newProf0,oldProf1,newProf1,oldPrice,newPrice;
        for(Color c:Color.values())
        {
            players.get(1).getBoardPlayer().setHall(c,8);
        }
        players.get(0).getBoardPlayer().setHall(Color.PURPLE,-6);
        oldPrice = characterCards.get(3).getPrice();
        players.get(0).getBoardPlayer().setHall(Color.RED,2);
        players.get(1).getBoardPlayer().setHall(Color.RED,-6);
        players.get(1).getBoardPlayer().insertProf(Color.RED);
        oldProf0 = players.get(0).getBoardPlayer().getSizeProfessors();
        oldProf1 = players.get(1).getBoardPlayer().getSizeProfessors();
        characterCards.get(3).doAction(game);
        newProf0 = players.get(0).getBoardPlayer().getSizeProfessors();
        newProf1 = players.get(1).getBoardPlayer().getSizeProfessors();
        newPrice = characterCards.get(3).getPrice();
        assertEquals(oldProf0+1,newProf0);
        assertEquals(oldProf1-1,newProf1);
        assertEquals(oldPrice +1, newPrice);
    }

    /**
     * Method FriarTest tests the method doAction of the class Friar.
     * @throws EndRoundException if the cards ended or the students ended.
     * @throws WinException if the towers of the active players ended.
     * @throws EndGameException if the islands remaining are only three.
     */
    @Test
    public void FriarTest() throws EndRoundException, WinException, EndGameException {
        int oldStudents,newStudents,oldStudIsland,newStudIsland,oldPrice,newPrice;
        game.getIslands().get(4).insertStudent(Color.YELLOW);
        oldStudIsland = game.getIslands().get(4).getStudents().get(Color.YELLOW);
        oldPrice = characterCards.get(2).getPrice();
        HashMap<Color,Integer> map = new HashMap<>();
        map.put(Color.PURPLE,2);
        map.put(Color.YELLOW,2);
        characterCards.get(2).setStudentColorForEffects(Color.YELLOW);
        characterCards.get(2).setIslandChosen(4);
        characterCards.get(2).setStudents(map);
        oldStudents = characterCards.get(2).getNumOfStudents();
        characterCards.get(2).doAction(game);
        newStudents = characterCards.get(2).getNumOfStudents();
        newPrice = characterCards.get(2).getPrice();
        newStudIsland = game.getIslands().get(4).getStudents().get(Color.YELLOW);
        assertEquals(oldStudents,newStudents);
        assertEquals(oldPrice +1, newPrice);
        assertEquals(oldStudIsland+1,newStudIsland);
    }




}