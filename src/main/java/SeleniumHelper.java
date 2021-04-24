import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SeleniumHelper {

    public Map<String,String> executionContext = new HashMap<>();
    WebDriver driver = DriverProvider.getDriver("chrome");
    Logger logger = Logger.getLogger(SeleniumHelper.class);

    public void clickOnElement(String elementXpath) {
        driver.findElement(By.xpath(elementXpath)).click();
        logger.info("Clicking on '" + elementXpath + "' element");
    }

    public void setText(String elementXpath, String text) {
        logger.info("Clicking on '" + elementXpath + "' element");
        driver.findElement(By.xpath(elementXpath)).sendKeys(text);
    }

    public void rememberElementText(String elementXpath, String variableName) {
        logger.info("Remember text value of '" + elementXpath + "' element in '" + variableName + "' variable");
        executionContext.put(variableName,driver.findElement(By.xpath(elementXpath)).getText());
    }

    public void goToUrl(String url) {
        logger.info("Navigating to '" + url + "' url");
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
