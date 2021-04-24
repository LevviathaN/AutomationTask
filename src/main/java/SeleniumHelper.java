import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SeleniumHelper {

    public Map<String,String> executionContext = new HashMap<>();
    WebDriver driver = DriverProvider.getDriver("chrome");
    private final Logger logger = Logger.getLogger(SeleniumHelper.class);
    private Map<String, String> locatorsMap = FileReader.toMapFromFile(ReportHelper.root + "/src/main/resources/Locators.json");

    public void clickOnElement(String elementLocator) {
        driver.findElement(By.xpath(locatorsMap.getOrDefault(elementLocator, elementLocator))).click();
        logger.info("Clicking on '" + elementLocator + "' element");
    }

    public void setText(String elementLocator, String text) {
        logger.info("Clicking on '" + elementLocator + "' element");
        driver.findElement(By.xpath(locatorsMap.getOrDefault(elementLocator, elementLocator))).sendKeys(text);
    }

    public void rememberElementText(String elementLocator, String variableName) {
        logger.info("Remember text value of '" + elementLocator + "' element in '" + variableName + "' variable");
        ReportHelper.test.log(LogStatus.INFO,"Remember text value of '" + elementLocator + "' element in '" + variableName + "' variable");
        executionContext.put(variableName,driver.findElement(By.xpath(locatorsMap.getOrDefault(elementLocator, elementLocator))).getText());
    }

    public void goToUrl(String url) {
        logger.info("Navigating to '" + url + "' url");
        ReportHelper.test.log(LogStatus.INFO,"Navigating to '" + url + "' url");
        driver.get(url);
    }

    public void sleepFor(int msTimeout) {
        logger.info("Sleep for '" + msTimeout + "' milliseconds");
        try {
            Thread.sleep(msTimeout);
        } catch (InterruptedException e) {
        }
    }


}
