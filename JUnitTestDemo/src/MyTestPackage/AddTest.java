package MyTestPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddTest {

	@Test
	public void addTest() {
		MyClassToTest junitObject=new MyClassToTest();
		int result=junitObject.add(100, 400);
		assertEquals(500, result);
	}
}