/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mininet.Relation;

import static org.junit.Assert.*;

/**
 *
 * @author xinyuye
 */
public class RelationTest
{
    /**
     * Test of getName1 method, of class Relation.
     */
    @Test
    public void testGetName1() 
    {
        System.out.println("getName1");
        Relation instance = new Relation();
        instance.setName1("Tom");
        String expResult = "Jack";
        String result = instance.getName1();
        assertNotSame(expResult, result);
    }

    /**
     * Test of setName1 method, of class Relation.
     */
    @Test
    public void testSetName1() {
        System.out.println("setName1");
        String name1 = "Tom";
        Relation instance = new Relation();
        instance.setName1(name1);
    }

    /**
     * Test of getName2 method, of class Relation.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName2");
        Relation instance = new Relation();
        instance.setName2("Rose");
        String expResult = "Rose";
        String result = instance.getName2();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName2 method, of class Relation.
     */
    @Test
    public void testSetName2() {
        System.out.println("setName2");
        String name2 = "Alice";
        Relation instance = new Relation();
        instance.setName2(name2);
    }

    /**
     * Test of getRelationType method, of class Relation.
     */
    @Test
    public void testGetRelationType() {
        System.out.println("getRelationType");
        Relation instance = new Relation();
        instance.setName1("Jack");
        instance.setName2("Rose");
        instance.setRelationType("Couple");
        String expResult = "Couple";
        String result = instance.getRelationType();
        assertSame(expResult, result);
    }

    /**
     * Test of setRelationType method, of class Relation.
     */
    @Test
    public void testSetRelationType() 
    {
        System.out.println("setRelationType");
        String relationType = "Couple";
        Relation instance = new Relation();   
        instance.setRelationType(relationType);
    }  
}
