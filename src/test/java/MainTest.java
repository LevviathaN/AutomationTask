import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends BaseTest {

    @Test
    public void PrivatCurrencyTest() {
        SeleniumHelper selenium = new SeleniumHelper();

        selenium.goToUrl("https://privatbank.ua/");
        selenium.rememberElementText("Privat USD Buy Rate","EC_PRVT_USD_BUY");
        selenium.goToUrl("https://finance.i.ua/");
        selenium.rememberElementText("FinanceUA USD Buy Rate","EC_FIN_USD_BUY");
        Assert.assertEquals(selenium.executionContext.get("EC_PRVT_USD_BUY"),selenium.executionContext.get("EC_FIN_USD_BUY").substring(0,5));
    }
}
