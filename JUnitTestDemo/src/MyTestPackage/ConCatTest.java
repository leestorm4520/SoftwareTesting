package MyTestPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConCatTest {

	@Test
	public void concatTest() {
		MyClassToTest junitObject=new MyClassToTest();
		String result=junitObject.concat("Pandemic", "2020!");
		assertEquals("Pandemic 2020!", result);
	}

}
