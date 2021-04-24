import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {

    }

    @AfterMethod
    public void endTest() {
        DriverProvider.closeDriver();
    }
}
