import org.testng.annotations.Test;

public class MainTest extends BaseTest {

    @Test
    public void PrivatCurrencyCrossBrowserTest() {
        SeleniumHelper chrome = new SeleniumHelper("chrome");

        chrome.goToUrl("https://privatbank.ua/");
        chrome.rememberElementText("Privat USD Buy Rate","EC_PRVT_USD_BUY");
        chrome.rememberElementText("Privat USD Sell Rate","EC_PRVT_USD_SELL");

        SeleniumHelper firefox = new SeleniumHelper("firefox");

        firefox.goToUrl("https://finance.i.ua/");
        firefox.rememberElementText("FinanceUA USD Buy Rate","EC_FIN_USD_BUY");
        firefox.rememberElementText("FinanceUA USD Sell Rate","EC_FIN_USD_SELL");

        firefox.assertEquals("EC_PRVT_USD_BUY","EC_FIN_USD_BUY","float");
        firefox.assertEquals("EC_PRVT_USD_SELL","EC_FIN_USD_SELL","float");
    }
}
