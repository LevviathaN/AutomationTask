import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class DriverProvider {

    public static final String OS_EXTENTION = (System.getProperty("os.name").toLowerCase().contains("win")) ? ".exe" : "_mac";
    static String FIREFOX_PATH = "drivers/geckodriver" + OS_EXTENTION;
    static String CHROME_PATH = "drivers/chromedriver" + OS_EXTENTION;
    public static WebDriver instance;

    static public ChromeDriver getChrome() {
        try {
            System.setProperty("webdriver.chrome.driver", CHROME_PATH);
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--test-type");
            options.addArguments("--start-maximized");

            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new WebDriverException("Unable to launch the browser", e);
        }
    }

    static public FirefoxDriver getFirefox() {
        try {
            System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);
            FirefoxProfile profile = new FirefoxProfile();

            FirefoxOptions options = new FirefoxOptions();
            options.setCapability(FirefoxDriver.PROFILE, profile);

            return new FirefoxDriver(options);
        } catch (Exception e) {
            throw new WebDriverException("Unable to launch the browser", e);
        }
    }

    public static WebDriver getDriver(String browser) {

        if (instance == null)
            if (browser.equals("firefox")) {
                instance = getFirefox();
            } else if (browser.equals("chrome")) {
                instance = getChrome();
            }
        return instance;

    }

    public static void closeDriver() {
        instance.quit();
        instance = null;
    }

}
