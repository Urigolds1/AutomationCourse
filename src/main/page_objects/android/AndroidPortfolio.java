package main.page_objects.android;

import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Elements;
import main.infrastructure.Instruments;
import main.infrastructure.Logger;
import main.page_objects.interfaces.MobileMenu;
import main.page_objects.interfaces.MobilePortfolio;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.Objects;


@SuppressWarnings("unchecked")
public class AndroidPortfolio implements MobilePortfolio {
    public AndroidDriver<?> driver;

    public AndroidPortfolio(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void goTo() {
        AndroidMenu menu = new AndroidMenu(driver);
        menu.clickOnItem(MobileMenu.MenuItems.PORTFOLIO);
    }

    @Override
    public void addWatchlistInstrument(Instruments.InstrumentItem instrument, boolean test) {
        clickOnAdditionButton(false);

        driver.findElement(Elements.ANDROID_INSTRUMENT_SEARCH_BOX).sendKeys(instrument.symbol);

        // Waiting for the results to appear.
        WebDriverWait waitForSearchResults = new WebDriverWait(driver, 30);
        waitForSearchResults.until(
                ExpectedConditions.presenceOfElementLocated(
                        Elements.ANDROID_INSTRUMENT_FIRST_SEARCH_RESULT));

        driver.findElement(Elements.ANDROID_INSTRUMENT_FIRST_SEARCH_RESULT).click();
        driver.findElement(Elements.ANDROID_INSTRUMENT_ADDITION_BACK_BUTTON).click();

        if (test) {
            new Assertion().assertFalse(
                getInstrumentItem(instrument) == null,
                String.format("%s failed to be added to the watchlist.", instrument.name));
        }
    }

    @Override
    public void removeWatchlistInstrument(Instruments.InstrumentItem instrument, boolean test) {
        driver.findElement(Elements.ANDROID_PORTFOLIO_WATCHLIST_EDIT_BUTTON).click();

        WebElement instrumentItem = getInstrumentItem(instrument);
        Objects.requireNonNull(instrumentItem).findElement(Elements.ANDROID_INSTRUMENT_ITEM_REMOVE_BUTTON).click();

        driver.findElement(Elements.ANDROID_PORTFOLIO_WATCHLIST_EDIT_SAVE_BUTTON).click();

        if (test) {
            new Assertion().assertTrue(
                getInstrumentItem(instrument) == null,
                String.format("%s failed to be removed from the watchlist.", instrument.name));
        }
    }

    @Override
    public void addWatchlistInstruments(Instruments.InstrumentItem[] instruments, boolean test) {
        clickOnAdditionButton(false);

        WebElement instrumentSearchBox = driver.findElement(Elements.ANDROID_INSTRUMENT_SEARCH_BOX);

        for (Instruments.InstrumentItem instrument : instruments) {
            instrumentSearchBox.clear();
            instrumentSearchBox.sendKeys(instrument.symbol);

            WebDriverWait waitForSearchResults = new WebDriverWait(driver, 20);
            waitForSearchResults.until(
                    ExpectedConditions.elementToBeClickable(Elements.ANDROID_INSTRUMENT_FIRST_SEARCH_RESULT));

            List<WebElement> searchResults =
                    (List<WebElement>) driver.findElements(Elements.ANDROID_INSTRUMENT_FIRST_SEARCH_RESULT);

            searchResults.get(0).click();
        }

        driver.findElement(Elements.ANDROID_GENERAL_BACK_BUTTON).click();

        /*
        if (test) {
            for (Instruments.InstrumentItem instrument : instruments) {
                // Implement assertion.
            }
        }
        */
    }

    @Override
    public void removeWatchlistInstruments(Instruments.InstrumentItem[] instruments, boolean test) {
        driver.findElement(Elements.ANDROID_PORTFOLIO_WATCHLIST_EDIT_BUTTON).click();

        for (Instruments.InstrumentItem instrument : instruments) {
            Objects.requireNonNull(getInstrumentItem(instrument))
                    .findElement(Elements.ANDROID_INSTRUMENT_ITEM_REMOVE_BUTTON).click();
        }

        driver.findElement(Elements.ANDROID_PORTFOLIO_WATCHLIST_EDIT_SAVE_BUTTON).click();

        /*
        if (test) {
            for (Instruments.InstrumentItem instrument : instruments) {
                // Implement assertion.
            }
        }
        */
    }


    private WebElement getInstrumentItem(Instruments.InstrumentItem instrumentItem) {
        try {
            List<WebElement> instruments =
                    (List<WebElement>) driver.findElements(Elements.ANDROID_INSTRUMENT_ITEM);

            for (WebElement instrument : instruments) {
                if (instrument.findElement(Elements.ANDROID_INSTRUMENT_ITEM_NAME).getText().equals(instrumentItem.name)) {
                    return instrument;
                }
            }
            return null;
        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.reportStep(noSuchElementException);
            return null;
        }
    }

    private void clickOnAdditionButton(boolean isSignedIn) {
        /* Inconsistency in the action items appearances requires this fallback strategy
         * in order to retrieve and click on the right button for addition. */
        try {
            if (isSignedIn) {
                try {
                    Logger.write("Trying to click on the 5th action item");
                    driver.findElement(Elements.ANDROID_ACTION_ITEM_5).click();
                    return;
                }
                catch (NoSuchElementException noSuchElementException) {
                    Logger.write("Clicking on the 3th action item instead");
                    driver.findElement(Elements.ANDROID_ACTION_ITEM_3).click();
                    return;
                }
            }

            Logger.write("Trying to click on the 4th action item");
            driver.findElement(Elements.ANDROID_ACTION_ITEM_4).click();
        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.write("Clicking on the 3rd action item instead");
            driver.findElement(Elements.ANDROID_ACTION_ITEM_3).click();
        }
    }
}
