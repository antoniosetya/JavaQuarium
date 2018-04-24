package arkavQuarium.lib;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
	@Test
	public void getFirst() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		assertEquals(l1.getFirst(), (Integer) 1);
	}

	@Test
	public void getLast() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		assertEquals(l1.getLast(), (Integer) 1);
	}

	@Test
	public void getSize() {
		List<Integer> l1 = new List<Integer>();
		assertEquals(l1.getSize(), 0);
	}

	@Test
	public void find() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		l1.append(2);
		l1.append(3);
		assertEquals(l1.find(2), 1);
	}

	@Test
	public void isEmpty() {
		List<Integer> l1 = new List<Integer>();
		assertTrue(l1.isEmpty());
	}

	@Test
	public void append() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		l1.append(2);
		l1.append(3);
		assertEquals(l1.getSize(), 3);
	}

	@Test
	public void remove() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		l1.append(2);
		l1.append(3);
		l1.remove(2);
		assertEquals(l1.find(2), -1);
	}

	@Test
	public void removeAt() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		l1.append(2);
		l1.append(3);
		l1.removeAt(0);
		assertEquals(l1.get(0), (Integer) 2);
	}

	@Test
	public void get() {
		List<Integer> l1 = new List<Integer>();
		l1.append(1);
		l1.append(2);
		l1.append(3);
		l1.append(4);
		l1.remove(4);
		assertEquals(l1.get(2), (Integer) 3);
	}
}