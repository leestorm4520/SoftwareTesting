package edu.utsa.example.junit.test;

import org.junit.Test;

import edu.utsa.example.junit.Fibonacci;
import junit.framework.TestCase;

public class FibonacciTest extends TestCase {
	protected Fibonacci fTest;
    protected static int groudtruth[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
    public void setUp(){
        fTest = new Fibonacci();
    }
    @Test
    public void testInitialize(){
        assertEquals("Length of fTest after initialization", fTest.getLength(), 0);
    }
    @Test
    public void testExtend(){
        fTest.extend(10);
        assertEquals("Length of extended array", fTest.getLength(), 10);
    }
    @Test
    public void testGet(){
        fTest.extend(10);
        assertEquals("Element at index ", groudtruth[8], fTest.get(8));
    }

}
