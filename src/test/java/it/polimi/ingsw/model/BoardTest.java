package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class BoardTest tests the methods of the class Board.
 */
public class BoardTest {
    private HashMap<Color,Integer> halls;
    private  HashMap<Color,Integer> entrance;
    private ArrayList<Color> professors;
    private  int towers;

    private Color prof;
    private int sizeProfessors;
    private int sizeEntrance;

    private Board board;

    /**
     * Method setUp sets the attributes of the class Board with a certain values, that will be used for the tests of the
     * methods of the class Board.
     */
    @BeforeEach
    public void setUp(){
        halls = new HashMap<>();
        entrance = new HashMap<>();
        professors = new ArrayList<>();
        towers = 2;
        board = new Board(halls,entrance,professors,towers);

        prof = Color.RED;
        professors.add(prof);
        sizeProfessors = professors.size();
        board.setProfessors(professors);
        for(int i = 0;i<5;i++)
        {
            board.insertStudentEntrance(Color.PURPLE);
        }

        sizeEntrance = entrance.size();
        halls.put(Color.YELLOW,2);
        board.setHall(Color.YELLOW,2);
        halls.put(Color.RED,5);
        board.setHall(Color.RED,5);
        board.setTowers(towers);
    }


    /**
     * Method testGetProfessors tests the method getProfessors.
     */
    @Test
    public void testGetProfessors() {

        try{
            assertEquals(professors,board.getProfessors());
        }catch (NullPointerException e){
            fail();
        }
    }

    /**
     * Method testSetProfessors tests the method setProfessors.
     */
    @Test
    public void testSetProfessors() {
        prof = Color.BLUE;
        professors.add(prof);
        board.setProfessors(professors);
        assertNotNull(board.getProfessors());
    }

    /**
     * Method testInsertProf tests the method insertProf.
     */
    @Test
    public void testInsertProf(){
        prof = Color.GREEN;
        board.insertProf(prof);
        assertNotNull(board.getProfessors());
        assertEquals(sizeProfessors+1,board.getSizeProfessors());

    }

    /**
     * Method testRemoveProf tests the method removeProf.
     */
    @Test
    public void testRemoveProf(){
        board.removeProf(prof);
        assertEquals(sizeProfessors-1,board.getSizeProfessors());
    }

    /**
     * Method testGetSizeProfessors tests the method getSizeProfessors.
     */
    @Test
    public void testGetSizeProfessors(){
        assertEquals(sizeProfessors,board.getSizeProfessors());
    }

    /**
     * Method testCheckProfessorTableIsEmpty tests the method checkProfessorTableIsEmpty.
     */
    @Test
    public void testCheckProfessorTableIsEmpty(){
        assertFalse(board.checkProfessorTableIsEmpty());
    }

    /**
     * Method testGetEntrance tests the method getEntrance.
     */
    @Test
    public void testGetEntrance() {
        assertEquals(entrance,board.getEntrance());
    }

    /**
     * Method testInsertStudentEntrance tests the method insertStudentEntrance.
     */
    @Test
    public void testInsertStudentEntrance() {
        if(board.getEntrance().size() <= 6)
        {
            board.insertStudentEntrance(Color.YELLOW);
            assertEquals(sizeEntrance+1,board.getEntrance().size());
            assertNotNull(board.getEntrance());
        } else
            fail();
    }

    /**
     * Method testRemoveStudentEntrance tests the method removeStudentEntrance in the case with the entrance
     * in which there are some students and the case with the empty entrance.
     */
    @Test
    public void testRemoveStudentEntrance() {
        int oldSize = board.getEntrance().get(Color.PURPLE);
        if(oldSize >= 0)
        {
            board.removeStudentEntrance(Color.PURPLE);
            assertEquals(oldSize-1,board.getEntrance().get(Color.PURPLE));
        } else {
            fail();
        }
        oldSize = board.getEntrance().get(Color.PURPLE)+1;
        for(int i = 0;i<oldSize;i++)
            board.removeStudentEntrance(Color.PURPLE);
        assertEquals(board.getEntrance().get(Color.PURPLE),0);
    }

    /**
     * Method testGetHall tests the method getHall.
     */
    @Test
    public void testGetHall() {
        assertEquals(halls.get(Color.YELLOW),board.getHall(Color.YELLOW));
    }

    /**
     * Method testSetHall tests the method setHall.
     */
    @Test
    public void testSetHall() {
        board.setHall(Color.RED,3);
        if(board.getHall(Color.RED)<=10 && board.getHall(Color.RED) >= 0)
        {
            assertEquals(halls.get(Color.RED),board.getHall(Color.RED));
        } else
            fail();

    }

    /**
     * Method testGetTowers tests the method getTowers.
     */
    @Test
    public void testGetTowers() {
        assertEquals(towers,board.getTowers());
    }

    /**
     * Method testSetTowers tests the method setTowers.
     */
    @Test
    public void testSetTowers() {
        board.setTowers(8);
        if(board.getTowers() <= 8 && board.getTowers() >= 0)
        {
            assertNotEquals(towers,board.getTowers());
            assertEquals(8,board.getTowers());
        } else
            fail();
    }

    /**
     * Method testAddTowers tests the method addTowers.
     */
    @Test
    public void testAddTowers() {
        board.addTowers(6);
        if(board.getTowers() <= 8 && board.getTowers() >= 0)
        {
            assertNotEquals(towers,board.getTowers());
            assertEquals(towers + 6,board.getTowers());
        } else
            fail();

    }

    /**
     * Method testRemoveTowers tests the method removeTowers.
     */
    @Test
    public void testRemoveTowers() {
        try {
            board.removeTowers(2);
            if(board.getTowers() <= 8 && board.getTowers() >= 0)
            {
                assertEquals(0,board.getTowers());
            }else
                fail();
        }catch(Exception e) {
            fail();
        }
        assertFalse(board.removeTowers(board.getTowers()+1));

    }

    /**
     * Method testCheckTowerSpaceIsEmpty tests the method checkTowerSpaceIsEmpty.
     */
    @Test
    public void testCheckTowerSpaceIsEmpty() {
        assertFalse(board.checkTowerSpaceIsEmpty());
    }






}