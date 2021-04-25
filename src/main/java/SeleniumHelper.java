import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class SeleniumHelper {

    public SeleniumHelper() {
        driver = DriverProvider.getNewDriver("chrome");
    }
    public SeleniumHelper(String brwsr) {
        driver = DriverProvider.getNewDriver(brwsr);
    }

    WebDriver driver;
    private static final Logger logger = Logger.getLogger(SeleniumHelper.class);
    public static Map<String,String> executionContext = new HashMap<>();
    public static Map<String,String> locatorsMap = FileReader.toMapFromFile(ReportHelper.root + "/src/main/resources/Locators.json");

    public void clickOnElement(String elementLocator) {
        logger.info("Clicking on '" + elementLocator + "' element");
        driver.findElement(By.xpath(locatorsMap.getOrDefault(elementLocator, elementLocator))).click();
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

    public void assertEquals(String arg1, String arg2) {
        logger.info("Checking equivalence between '" + arg1 + "' and '" + arg2 + "' values");
        ReportHelper.test.log(LogStatus.INFO,"Checking equivalence between '" + arg1 + "' and '" + arg2 + "' values");
        String value1 = locatorsMap.containsKey(arg1) ? driver.findElement(By.xpath(locatorsMap.get(arg1))).getText() : executionContext.getOrDefault(arg1,arg1);
        String value2 = locatorsMap.containsKey(arg2) ? driver.findElement(By.xpath(locatorsMap.get(arg2))).getText() : executionContext.getOrDefault(arg2,arg2);
        Assert.assertEquals(value1,value2);
    }

    public void assertEquals(String arg1, String arg2, String type) {
        logger.info("Checking equivalence between '" + arg1 + "' and '" + arg2 + "' values");
        ReportHelper.test.log(LogStatus.INFO,"Checking equivalence between '" + arg1 + "' and '" + arg2 + "' values");
        String value1 = locatorsMap.containsKey(arg1) ? driver.findElement(By.xpath(locatorsMap.get(arg1))).getText() : executionContext.getOrDefault(arg1,arg1);
        String value2 = locatorsMap.containsKey(arg2) ? driver.findElement(By.xpath(locatorsMap.get(arg2))).getText() : executionContext.getOrDefault(arg2,arg2);
        switch(type) {
            case "float":
                Assert.assertEquals(Float.parseFloat(value1),Float.parseFloat(value2));
                break;
            case "int":
                Assert.assertEquals(Integer.parseInt(value1),Integer.parseInt(value2));
                break;
            default:
                Assert.assertEquals(value1,value2);
        }
    }

}
