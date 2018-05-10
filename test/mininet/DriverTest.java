/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mininet;

import gui.MiniNet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xinyuye
 */
public class DriverTest {
    MiniNet miniNet = null;
    Driver driver = null;
    
    public DriverTest() {
    }
    
    
    @Before
    public void setUp() 
    {
        miniNet = new MiniNet();
        
        try
        {
            driver = new Driver();
        }
        catch (Exception ex) 
        {
            Logger.getLogger(DriverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MiniNet.driver = driver;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRelations method, of class Driver.
     */
    @Test
    public void testGetRelations() {
        System.out.println("getRelations");
        
        List<Relation> expResult = null;
        List<Relation> result = driver.getRelations();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setRelations method, of class Driver.
     */
    @Test
    public void testSetRelations() {
        System.out.println("setRelations");
        List<Relation> relations = null;
 
        driver.setRelations(relations);
    }

    /**
     * Test of addRelations method, of class Driver.
     */
    @Test
    public void testAddRelations() {
        System.out.println("addRelations");
        Relation r = new Relation();
        r.setName1("Alex Smith");
        r.setName2("Adam");
        r.setRelationType("Friend");
        String expResult = "Successfully make them friends !";
        String result = driver.addRelations(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTheMiniNet method, of class Driver.
     */
    @Test
    public void testGetTheMiniNet() 
    {
        System.out.println("getTheMiniNet");
        List list = new ArrayList();
        driver.setTheMiniNet(list);       
        List<Person> expResult = list;      
        List<Person> result = driver.getTheMiniNet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTheMiniNet method, of class Driver.
     */
    @Test
    public void testSetTheMiniNet() {
        System.out.println("setTheMiniNet");
        List<Person> theMiniNet = new ArrayList<Person>();
        driver.setTheMiniNet(theMiniNet);
    }

    /**
     * Test of makeFriends method, of class Driver.
     */
    @Test
    public void testMakeFriends() throws Exception 
    {
        System.out.println("makeFriends");
        String name1 = "James";
        String name2 = "Angelo";    
        driver.addAdult(name1, "j.jpg", "working", 'M', 27, "VIC");
        driver.addAdult(name2, "j.jpg", "working", 'M', 27, "VIC");     
        String expResult = "Successfully make them friends !";
        String result = driver.makeFriends(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of setClassmate method, of class Driver.
     */
    @Test
    public void testSetClassmate() throws Exception 
    {
        System.out.println("setClassmate");
        String name1 = "Alice";
        String name2 = "Alex Smith";
        String expResult = "Successfully set each other as classmate !";
        String result = driver.setClassmate(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of setColleague method, of class Driver.
     */
    @Test
    public void testSetColleague() throws Exception 
    {
        System.out.println("setColleague");
        String name1 = "Alex Smith";
        String name2 = "Adam";
        String expResult = "Successfully set each other as colleague !";
        String result = driver.setColleague(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpouse method, of class Driver.
     */
    @Test
    public void testSetSpouse()  
    {
        try 
        {
            System.out.println("setSpouse");
            String name1 = "Sakura Gaimu";
            String name2 = "Carl Wong";
            String expResult = "Successfully set each other as spouse";
            
            String result = driver.setSpouse(name1, name2);
            assertSame(expResult, result);
        } 
        catch (NoAvailableException ex) 
        {
            Logger.getLogger(DriverTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (NotToBeCoupledException ex) 
        {
            Logger.getLogger(DriverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setParents method, of class Driver.
     */
    @Test
    public void testSetParents() throws Exception {
        System.out.println("setParents");
        String name1 = "Alice";
        String name2 = "Luke";
        String expResult = "";
        String result = driver.setParents(name1, name2);
        assertNotSame(expResult, result);
    }

    /**
     * Test of isClassmate method, of class Driver.
     */
    @Test
    public void testIsClassmate() 
    {
        System.out.println("isClassmate");
        String name1 = "Bob";
        String name2 = "Alice";
        boolean expResult = false;
        boolean result = driver.isClassmate(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isColleague method, of class Driver.
     */
    @Test
    public void testIsColleague() {
        System.out.println("isColleague");
        String name1 = "Adam";
        String name2 = "Alex Smith";
        boolean expResult = false;
        boolean result = driver.isColleague(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isFriend method, of class Driver.
     */
    @Test
    public void testIsFriend()
    {
        System.out.println("isFriend");
        String name1 = "Kevin YE";
        String name2 = "Yifan ZHANG";
        boolean expResult = false;
        boolean result = driver.isFriend(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParent method, of class Driver.
     */
    @Test
    public void testIsParent() 
    {
        System.out.println("isParent");
        String name1 = "Mark Turner";
        String name2 = "Ben Turner";
        boolean expResult = false;
        boolean result = driver.isParent(name1, name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of findSpouse method, of class Driver.
     */
    @Test
    public void testFindSpouse() {
        System.out.println("findSpouse");
        String name = "Alice";
        String expResult = "Bob";
        String result = driver.findSpouse(name);
    }

    /**
     * Test of addDependent method, of class Driver.
     */
    @Test
    public void testAddDependent() throws Exception {
        System.out.println("addDependent");
        String name = "Lam";
        String photoPath = "l.jpg";
        String status = "swimming";
        char gender = 'M';
        int age = 6;
        String state = "VIC";
        String fatherName = "Bob";
        String motherName = "Alice";
        String expResult = "This dependent is successfully added !";
        String result = driver.addDependent(name, photoPath, status, gender, age, state, fatherName, motherName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAdult method, of class Driver.
     */
    @Test
    public void testAddAdult() {
        System.out.println("addAdult");
        String name = "Michael Kim";
        String photoPath = "mk.jpg";
        String status = "working";
        char gender = 'M';
        int age = 27;
        String state = "VIC";
        String expResult = "This adult is successfully added !";
        String result = driver.addAdult(name, photoPath, status, gender, age, state);
        assertEquals(expResult, result);

    }

    /**
     * Test of isPersonExisted method, of class Driver.
     */
    @Test
    public void testIsPersonExisted() {
        System.out.println("isPersonExisted");
        String name = "Jay";
        boolean expResult = false;
        boolean result = driver.isPersonExisted(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersonByName method, of class Driver.
     */
    @Test
    public void testGetPersonByName() {
        System.out.println("getPersonByName");
        String name = "Jay";
        Person expResult = null;
        Person result = driver.getPersonByName(name);
        assertEquals(expResult, result);
    }
    
}
