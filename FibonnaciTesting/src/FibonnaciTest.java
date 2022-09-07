import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


public class FibonacciTest extends TestCase {
	/*
	 * Initialization of Test Class -> setUp -> try{ testMethod} catch{...} -> tearDown->
	 * tearDown: only when there is external dependency (ex: database,..)
	 * 
	 * Tips:
	 *  Bottom-up ( if obj.m1 calls obj.m2 -> test obj.m2 first)
	 *  Externalize Data
	 *  Use development database
	 *  Do enough logging in the code during testing
	 *  Testing for exceptions 
	 *  
	 *  @Test(expected=FileNotFoundException.class)
	 *  public testException(){
	 *  	do("non existing path"); 
	 *  }
	 *  
	 *  public testException(){
	 *  	try{
	 *  		open("non existing path"); fail();
	 *  	} catch(FileNotFoundException e){}
	 *  }
	 *  // more flexible, more specific with the message of catch
	 *  
	 *  Writing Assertions
	 *  
	 *  public void testCapacity(){
	 *  assertTrue(fFull.size()==100+size)'
	 *  }
	 *  
	 *  Tear Down
	 *  void setUp(){
	 *  	File f=open('foo');
	 *  	File b=open('bar');
	 *  
	 *  }
	 *  
	 */
	public static void main(String args[]) {
		protected Fibonacci fTest;
	    protected static int groudtruth[] = {0, 1,...,12586269025};
	    public void setUp(){
	        fTest = new Fibonacci();
	    }
	    @Test
	    public testInitialize(){
	        assertEquals(“Length of fTest after initialization”, 0, fTest.getLength());
	    }
	    @Test
	    public testExtend(){
	        fTest.extend(50);
	        assertEquals(“…”, 50, fTest.getLength());
	    }
	    @Test
	    public testGet(){
	        fTest.extend(50);
	        assertEquals(“…”, groudtruth[49], fTest.get(50));
	    }

}
