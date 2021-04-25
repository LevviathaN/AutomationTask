import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;


public class DriverProvider {

    public static final String OS_EXTENTION = (System.getProperty("os.name").toLowerCase().contains("win")) ? ".exe" : "_mac";
    static String FIREFOX_PATH = "drivers/geckodriver" + OS_EXTENTION;
    static String CHROME_PATH = "drivers/chromedriver" + OS_EXTENTION;
    public static List<WebDriver> instances = new ArrayList<>();

    static public ChromeDriver getChrome() {
        try {
            System.setProperty("webdriver.chrome.driver", CHROME_PATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new WebDriverException("Unable to launch the browser", e);
        }
    }

    static public FirefoxDriver getFirefox() {
        try {
            System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            return new FirefoxDriver(options);
        } catch (Exception e) {
            throw new WebDriverException("Unable to launch the browser", e);
        }
    }

    public static WebDriver getNewDriver(String browser) {
        WebDriver driver;
        if (browser.equals("firefox")) {
            driver = getFirefox();
            instances.add(driver);
            return driver;
        } else if (browser.equals("chrome")) {
            driver = getChrome();
            instances.add(driver);
            return driver;
        }
        return null;
    }

    public static void closeAllDrivers() {
        for (WebDriver driver : instances) {
            driver.quit();
        }
    }

}
