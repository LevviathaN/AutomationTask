import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends BaseTest {

    @Test
    public void PrivatCurrencyTest() {
        SeleniumHelper selenium = new SeleniumHelper();

        selenium.goToUrl("https://privatbank.ua/");
        selenium.rememberElementText("//td[@id='USD_buy']","EC_PRVT_USD_BUY");
        selenium.goToUrl("https://finance.i.ua/");
        selenium.rememberElementText("//tbody[@class='bank_rates_usd']//a[text()='ПриватБанк']/../following-sibling::td[@class='buy_rate']/span/span","EC_FIN_USD_BUY");
        Assert.assertEquals(selenium.executionContext.get("EC_PRVT_USD_BUY"),selenium.executionContext.get("EC_FIN_USD_BUY").substring(0,5));
    }
}
