package pobj.multiset.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.MultiSet;
import pobj.multiset.MultiSetDecorator;

public class MultiSetDecoratorTest {
	@Test
	public void testAdd1() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a");
		m.add("a",5);
		assertEquals(m.count("a"), 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2() throws InvalidCountException {
		MultiSet<String> m =new MultiSetDecorator<>( new HashMultiSet<>());
		m.add("a");
		m.add("a",-1);
	}
	
	@Test
	public void testRemove() throws InvalidCountException{
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",8);
		m.remove("a",2);
		assertEquals(m.count("a"), 6);		
	}
	
	@Test
	public void testSize() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",7);
		m.add("b",3);
		assertEquals(m.size(),10);
	}
	
	@Test
	public void testToString() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",7);
		m.add("b",3);
		System.out.println(m.toString());
		assertEquals(m.toString(),"[a:7; b:3]");
	}
	
	@Test
	public void testClear() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",7);
		m.add("b",3);
		m.clear();
		assertEquals(m.count("a"), 0);
		assertEquals(m.count("b"), 0);
		m.add("a");
		assertEquals(m.count("a"), 1);
		assertEquals(m.size(),1);
	}
	
	@Test
	public void testInexistant() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",7);
		m.add("b",3);
		assertEquals(m.count("c"), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCountAZero() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveCountAZero() throws InvalidCountException {
		MultiSet<String> m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a",1);
		m.remove("a",0);
	}
}
