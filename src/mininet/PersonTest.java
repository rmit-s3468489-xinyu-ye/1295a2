package mininet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class PersonTest
{
	Person p;
	

	@Before
	public void setUp() throws Exception 
	{
		p = new Adult("Prado Landcruiser", "lc120.png", "just got serviced", 'M', 27, "VIC");
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPhotoPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPhotoPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

}
