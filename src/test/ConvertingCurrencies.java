package test;


import io.appium.java_client.MobileDriver;
import main.infrastructure.Context;
import main.infrastructure.Currencies;
import main.page_objects.CurrencyConverter;
import main.page_objects.interfaces.MobileCurrencyConverter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ConvertingCurrencies {
    private MobileDriver<?> driver;

    private MobileCurrencyConverter currencyConverter;

    @BeforeClass
    public void ConvertingCurrencySetup() {
        driver = Context.getIPhoneDriver("iPhone XR", "00008020-000560A201E8003A");

        currencyConverter = new CurrencyConverter().getPageObject(driver);
        currencyConverter.goTo();
    }

    @Test(priority = 0)
    public void ConvertCurrencies() {
        currencyConverter.changeCurrency(Currencies.ILS, MobileCurrencyConverter.CurrencyField.TOP);
        currencyConverter.changeCurrency(Currencies.USD, MobileCurrencyConverter.CurrencyField.BOTTOM);

        currencyConverter.setCurrency(1.0, MobileCurrencyConverter.CurrencyField.TOP);
        currencyConverter.setCurrency(1.0, MobileCurrencyConverter.CurrencyField.BOTTOM);
    }

    @Test(priority = 1)
    public void FlipCurrencies() {
        currencyConverter.flipCurrencies(true);
    }

    @AfterClass
    public void Teardown() {
        driver.quit();
    }
}
