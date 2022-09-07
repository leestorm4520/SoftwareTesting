package MyTestPackage;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AddTest.class, ConCatTest.class})
public class AllTests {

}
