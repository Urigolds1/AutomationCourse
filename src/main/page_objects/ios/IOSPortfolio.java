package main.page_objects.ios;


import main.infrastructure.Elements;
import main.infrastructure.Instruments;
import main.infrastructure.Instruments.InstrumentItem;

import io.appium.java_client.ios.IOSDriver;
import main.page_objects.interfaces.MobileMenu;
import main.page_objects.interfaces.MobilePortfolio;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import java.util.List;


@SuppressWarnings("unchecked")
public class IOSPortfolio implements MobilePortfolio {
    public enum PortfolioType { Watchlist, Holdings }

    private IOSDriver<?> driver;

    public IOSPortfolio(IOSDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void goTo() {
        IOSMenu sideMenu = new IOSMenu(driver);
        sideMenu.clickOnItem(MobileMenu.MenuItems.PORTFOLIO);
    }

    @Override
    public void addWatchlistInstrument(InstrumentItem instrument, boolean test) {
        driver.findElement(Elements.IOS_PORTFOLIO_INSTRUMENT_ADD_BUTTON).click();

        driver.findElement(Elements.IOS_INSTRUMENT_SEARCH_BOX).sendKeys(instrument.symbol);

        WebDriverWait waitForSearchResults = new WebDriverWait(driver, 20);
        waitForSearchResults.until(ExpectedConditions.elementToBeClickable(Elements.IOS_INSTRUMENT_SEARCH_RESULT));

        List<WebElement> searchResults =
                (List<WebElement>) driver.findElements(Elements.IOS_INSTRUMENT_SEARCH_RESULT);

        searchResults.get(0).click();

        driver.findElement(Elements.IOS_GENERAL_BACK_BUTTON).click();

        if (test) assertInstrumentExistance(instrument, true);
    }

    @Override
    public void addWatchlistInstruments(InstrumentItem[] instruments, boolean test) {
        driver.findElement(Elements.IOS_PORTFOLIO_INSTRUMENT_ADD_BUTTON).click();

        WebElement instrumentSearchBox = driver.findElement(Elements.IOS_INSTRUMENT_SEARCH_BOX);

        for (InstrumentItem instrument : instruments) {
            instrumentSearchBox.clear();
            instrumentSearchBox.sendKeys(instrument.symbol);

            WebDriverWait waitForSearchResults = new WebDriverWait(driver, 20);
            waitForSearchResults.until(ExpectedConditions.elementToBeClickable(Elements.IOS_INSTRUMENT_SEARCH_RESULT));

            List<WebElement> searchResults =
                    (List<WebElement>) driver.findElements(Elements.IOS_INSTRUMENT_SEARCH_RESULT);

            searchResults.get(0).click();
        }

        driver.findElement(Elements.IOS_GENERAL_BACK_BUTTON).click();

        if (test) {
            for (InstrumentItem instrument : instruments) {
                assertInstrumentExistance(instrument, true);
            }
        }
    }

    @Override
    public void removeWatchlistInstrument(InstrumentItem instrument, boolean test) {
        driver.findElement(Elements.IOS_PORTFOLIO_EDIT_BUTTON).click();

        List<WebElement> instruments =
                (List<WebElement>) driver.findElements(Elements.IOS_INSTRUMENT_ITEMS);

        instruments.get(0).findElement(Instruments.getIOSFormattedId("Delete", instrument)).click();
        instruments.get(0).findElement(Elements.IOS_INSTRUMENT_CONFIRM_DELETE_BUTTON).click();

        driver.findElement(Elements.IOS_INSTRUMENT_APPLY_EDIT_BUTTON).click();

        if (test) assertInstrumentExistance(instrument, false);
    }

    @Override
    public void removeWatchlistInstruments(InstrumentItem[] instruments, boolean test) {
        driver.findElement(Elements.IOS_PORTFOLIO_EDIT_BUTTON).click();

        WebElement instrumentsContainer = driver.findElement(Elements.IOS_PORTFOLIO_INSTRUMENTS_CONTAINER);

        for (InstrumentItem instrument : instruments) {
            instrumentsContainer.findElement(Instruments.getIOSFormattedId("Delete", instrument)).click();
            instrumentsContainer.findElement(Elements.IOS_INSTRUMENT_CONFIRM_DELETE_BUTTON).click();
        }

        driver.findElement(Elements.IOS_INSTRUMENT_APPLY_EDIT_BUTTON).click();

        if (test) {
            for (InstrumentItem instrument : instruments) {
                assertInstrumentExistance(instrument, false);
            }
        }
    }

    private void assertInstrumentExistance(InstrumentItem instrument, boolean shouldExist) {
        String shouldExistString = shouldExist ? "should" : "should not";
        new Assertion().assertTrue(
        !driver.findElements(By.id(instrument.name)).isEmpty() == shouldExist,
            String.format("%s %s exist.", instrument.name, shouldExistString));
    }
}
