package main.page_objects.ios;


import io.appium.java_client.ios.IOSDriver;
import main.infrastructure.Currencies;
import main.infrastructure.Elements;
import main.page_objects.interfaces.MobileCurrencyConverter;
import main.page_objects.interfaces.MobileMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import java.util.List;


public class IOSCurrencyConverter implements MobileCurrencyConverter {
    private IOSDriver<?> driver;

    public IOSCurrencyConverter(IOSDriver<?> driver) { this.driver = driver; }

    @Override
    public void goTo() {
        IOSMenu sideMenu = new IOSMenu(driver);
        sideMenu.clickOnItem(MobileMenu.MenuItems.CURRENCY_CONVERTER);
    }

    @Override
    public void changeCurrency(Currencies.Currency currency, CurrencyField currencyField) {
        WebDriverWait waitForCurrenciesDropdowns = new WebDriverWait(driver, 10);
        waitForCurrenciesDropdowns.until(
                ExpectedConditions.elementToBeClickable(Elements.IOS_CURRENCY_CONVERTER_CURRENCIES_DROPDOWNS));

        driver.findElements(Elements.IOS_CURRENCY_CONVERTER_CURRENCIES_DROPDOWNS)
                .get(currencyField == CurrencyField.TOP ? 0 : 1).click();

        WebDriverWait waitForCurrencySearchPopUp = new WebDriverWait(driver, 10);
        waitForCurrencySearchPopUp.until(
                ExpectedConditions.presenceOfElementLocated(Elements.IOS_CURRENCY_CONVERTER_SEARCH_BOX));

        driver.findElement(Elements.IOS_CURRENCY_CONVERTER_SEARCH_BOX).sendKeys(currency.abbreviation);

        driver.findElement(By.id(currency.name)).click();
    }

    @Override
    public void setCurrency(double amount, CurrencyField currencyField) {
        WebElement currencyTextField = driver.findElements(Elements.IOS_CURRENCY_CONVERTER_TEXT_FIELDS)
                .get(currencyField == CurrencyField.TOP ? 0 : 1);

        currencyTextField.clear();
        currencyTextField.sendKeys(Double.toString(amount));
    }

    @Override @SuppressWarnings("unchecked")
    public void flipCurrencies(boolean test) {
        WebDriverWait waitForButton = new WebDriverWait(driver, 10);
        waitForButton.until(ExpectedConditions.elementToBeClickable(Elements.IOS_CURRENCY_CONVERTER_BUTTONS));

        if (test) {
            List<WebElement> currencyValues =
                    (List<WebElement>) driver.findElements(Elements.IOS_CURRENCY_CONVERTER_TEXT_FIELDS);

            String topCurrencyValueBeforeFlip = currencyValues.get(0).getText();
            String bottomCurrencyValueBeforeFlip = currencyValues.get(1).getText();

            driver.findElements(Elements.IOS_CURRENCY_CONVERTER_BUTTONS).get(1).click();

            new Assertion().assertSame(
                    currencyValues.get(0).getText().replaceAll("[^\\d.]", ""),
                    topCurrencyValueBeforeFlip.replaceAll("[^\\d.]", ""));
            
            new Assertion().assertSame(
                    currencyValues.get(1).getText().replaceAll("[^\\d.]", ""),
                    bottomCurrencyValueBeforeFlip.replaceAll("[^\\d.]", ""));
        }
        else {
            driver.findElements(Elements.IOS_CURRENCY_CONVERTER_BUTTONS).get(1).click();
        }
    }

    @Override
    public double getCurrencyValue(CurrencyField currencyField) {
        return Double.parseDouble(driver.findElements(Elements.IOS_CURRENCY_CONVERTER_TEXT_FIELDS)
                .get(currencyField == CurrencyField.TOP ? 0 : 1).getText());
    }
}
