package main.page_objects.android;


import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Elements;
import main.infrastructure.Logger;
import main.infrastructure.MobileGestures;
import main.page_objects.interfaces.MobileMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class AndroidMenu implements MobileMenu {
    public AndroidDriver<?> driver;

    public AndroidMenu(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void open() {
        try {
            driver.findElement(Elements.ANDROID_MAIN_TABS_CONTAINER)
                    .findElements(Elements.ANDROID_MAIN_TABS_ITEMS)
                    .get(Elements.ANDROID_MAIN_TABS_MORE).click();
        }
        catch (NoSuchElementException exception) {
            Logger.reportStep(exception);
        }
    }

    @Override
    public void clickOnItem(MenuItems sideMenuItem) {
        open();

        switch (sideMenuItem) {
            case PORTFOLIO:
                driver.findElement(Elements.ANDROID_MAIN_TABS_CONTAINER)
                        .findElements(Elements.ANDROID_MAIN_TABS_ITEMS)
                        .get(Elements.ANDROID_MAIN_TABS_PORTFOLIO).click();
                break;
            case SETTINGS:
                scrollToItem(Elements.ANDROID_MORE_MENU_SETTINGS_BUTTON);
                break;
            case SIGNOUT:
                scrollToItem(Elements.ANDROID_MORE_MENU_SIGNOUT_BUTTON);
                break;
        }
    }

    @Override
    public void clickOnEditions() {
        open();

        driver.findElement(Elements.ANDROID_MORE_MENU_LANGUAGES).click();

        AndroidSettings settings = new AndroidSettings(driver);
        settings.clickOnLanguage();
    }

    private void scrollToItem(By locator) {
        boolean foundItem = false;
        while (!foundItem) {
            try {
                driver.findElement(locator).click();
                foundItem = true;
            }
            catch (NoSuchElementException noSuchElementException) {
                MobileGestures.scrollTo(driver, 1000,
                        MobileGestures.ScrollingDirection.DOWN, MobileGestures.ScrollingIntensity.AGGRESIVE);
            }
        }
    }
}
