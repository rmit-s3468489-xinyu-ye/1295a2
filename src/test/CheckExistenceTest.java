package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mininet.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckExistenceTest
{
	static Driver driver;
	static List<Person> people;
	static Person Jack;
	static Person Rose;
	
	@BeforeClass
	public static void setUp() 
	{
            
		try 
		{
			driver=new Driver();
			people = driver.getTheMiniNet();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		Jack = new Adult("Jack", "Jack.jpg", "working for Bosch", 'M', 30, "VIC");
		Rose = new Adult("Rose","Rose.jpg","working for VanGuard",'F',32,"VIC");
	}
	
	@Test
	public void test1CheckExistenceFail() 
	{
		assertFalse(driver.isPersonExisted("Jack"));
		assertFalse(driver.isPersonExisted("Rose"));
	}
	
	@Test
	public void test2CheckExistence() 
	{
		people.add(Jack);
		people.add(Rose);
		assertTrue(driver.isPersonExisted("Jack"));
		assertTrue(driver.isPersonExisted("Rose"));
	}
	
	@AfterClass
	public static void tearDown()
	{	
		people.remove(Jack);
		people.remove(Rose);
	}
}
