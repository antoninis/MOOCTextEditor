/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	@Test
	public void testGet()
	{
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}

		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
	}
	
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			list1.remove(-1);
			fail("testSet: Check out of bounds");
			}
		catch (IndexOutOfBoundsException e) {}
		try {
			list1.remove(list1.size());
			fail("testSet: Check out of bounds");
			}
		catch (IndexOutOfBoundsException e) {}
	}
	@Test
	public void testAddEnd()
	{
		assertTrue(list1.add(10));
		assertTrue(list1.add(50));
		assertTrue(list1.add(100));
		assertTrue(list1.add(150));
		
		try {
			list1.add(null);
		}
		catch (NullPointerException e) {}
	}

	@Test
	public void testSize()
	{
		assertEquals(shortList.size, 2);
		assertEquals(emptyList.size, 0);
		assertEquals(longerList.size, 10);
		assertEquals(list1.size, 3);
		}

	@Test
	public void testAddAtIndex()
	{
		try {
			list1.add(-1, 10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		try {
			list1.add(list1.size()+1, 10);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		try {
			list1.add(null);
		}
		catch (NullPointerException e) {}
		

		list1.add(2, 10);
		assertEquals("check testAddAtIndex", (Integer) 10, list1.get(2));
		assertEquals("check testAddAtIndex", (Integer) 65, list1.get(0));
		assertEquals("check testAddAtIndex", (Integer) 21, list1.get(1));
		assertEquals("check testAddAtIndex", (Integer) 42, list1.get(3));
		assertEquals(4, list1.size());
		}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		list1.set(2, 2000);
		assertEquals("testSet: wrong element at index ", (Integer)2000, list1.get(2));		   
		try {
			list1.set(-1, 15);
			fail("testSet: Check out of bounds");
			}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			list1.set(list1.size()+1, 15);
			fail("testSet: Check out of bounds");
			}
		catch (IndexOutOfBoundsException e) {}
		
		
		 
	
	}
}
