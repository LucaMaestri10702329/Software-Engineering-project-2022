package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class IslandTest tests the methods of the class Island.
 */
public class IslandTest {
    private HashMap<Color,Integer> students;
    private int towers;
    private int numOfLink;
    private PlayerColor owner;
    private Island island ;

    /**
     * Method setUp sets the attributes of the class Island with a certain values, that will be used for the tests of the
     * methods of the class Island.
     */
    @BeforeEach
    public void setUp(){
        island = new Island(0);
        students = new HashMap<>();
        numOfLink = 0;
        island.setBanTile(1);
        owner = PlayerColor.BLACK;
        island.setOwner(owner);
        students.put(Color.YELLOW,3);
        for(int i = 0; i<3; i++)
        {
            island.insertStudent(Color.YELLOW);
        }
        students.put(Color.RED,2);
        for(int i = 0; i<2; i++)
        {
            island.insertStudent(Color.RED);
        }
        students.put(Color.PURPLE,0);
        students.put(Color.GREEN,0);
        students.put(Color.BLUE,0);


        towers = 1;
        island.setNumberOfLink(numOfLink);
        island.setTowers();
    }

    /**
     * Method testGetStudents tests the method getStudents.
     */
    @Test
    public void testGetStudents() {
        assertEquals(students,island.getStudents());
    }


    /**
     * Method testGetNumberOfLink tests the method getNumberOfLink.
     */
    @Test
    public void testGetNumberOfLink() {
        assertEquals(numOfLink,island.getNumberOfLink());
    }

    /**
     * Method testSetNumberOfLink tests the method setNumberOfLink.
     */
    @Test
    public void testSetNumberOfLink() {
        island.setNumberOfLink(2);
        assertNotEquals(numOfLink,island.getNumberOfLink());
    }

    /**
     * Method testGetTowers tests the method getTowers.
     */
    @Test
    public void testGetTowers() {
        assertEquals(towers,island.getTowers());
    }

    /**
     * Method testSetTowers tests the method setTowers.
     */
    @Test
    public void testSetTowers() {
        island.setNumberOfLink(1);
        island.setTowers();
        assertEquals(towers+1,island.getTowers());
    }

    /**
     * Method testGetOwner tests the method getOwner.
     */
    @Test
    public void testGetOwner() {
        assertEquals(owner,island.getOwner());
    }

    /**
     * Method testSetOwner tests the method setOwner.
     */
    @Test
    public void testSetOwner() {
        PlayerColor c = PlayerColor.WHITE;
        island.setOwner(c);
        assertNotEquals(owner,island.getOwner());
        assertEquals(c,island.getOwner());
    }

    /**
     * Method testInsertStudent tests the method insertStudent.
     */
    @Test
    public void testInsertStudent() {
        island.insertStudent(Color.PURPLE);
        assertEquals(students.size()+1,island.getNumStudents());
        assertFalse(island.getStudents().isEmpty());
    }


    /**
     * Method testRemoveTower tests the method removeTower.
     */
    @Test
    public void testRemoveTower() {
        island.resetTowers();
        assertEquals(towers-1,island.getTowers());
    }

    /**
     * Method testGetBanTile tests the method getBanTile.
     */
    @Test
    public void testGetBanTile() {
        assertEquals(1,island.getBanTile());
    }

    /**
     * Method testSetBanTile tests the method setBanTile.
     */
    @Test
    public void testSetBanTile() {
        island.setBanTile(0);
        assertEquals(island.getBanTile(),0);
    }

    /**
     * Method testGetId tests the method getId.
     */
    @Test
    public void testGetId(){
        assertEquals(island.getId(),0);
    }

}