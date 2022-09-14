package edu.utsa.example.junit.test;

import org.junit.Test;

import edu.utsa.example.junit.NullWordException;
import edu.utsa.example.junit.WordTree;
import junit.framework.TestCase;

public class WordTreeTest extends TestCase{
	WordTree testTree;
	public void setUp(){
		try {
			this.testTree = new WordTree("root");
		} catch (NullWordException e) {
			e.printStackTrace();
		}
	}
	public void tearDown(){
		
	}

	@Test
	public void testRoot(){
		assertNull("Left child of root", this.testTree.getLeftChild());
		assertNull("Right child of root", this.testTree.getRightChild());
		assertEquals("Data on root node", "root", this.testTree.getData());
	}

}
