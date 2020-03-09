package main.page_objects.ios;


import main.infrastructure.Elements;
import main.infrastructure.Logger;
import main.infrastructure.MobileGestures;

import io.appium.java_client.ios.IOSDriver;
import main.page_objects.interfaces.MobileMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;


public class IOSMenu implements MobileMenu {
    private IOSDriver<?> driver;

    public IOSMenu(IOSDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void open() {
        try {
            driver.findElement(Elements.IOS_SIDEMENU_OPEN_BUTTON).click();

            MobileGestures.scrollTo(
                    driver, Elements.IOS_SIDEMENU_SIGNUP_BUTTON, MobileGestures.ScrollingDirection.UP);
        }
        catch (NoSuchElementException exception) {
            Logger.reportStep(exception);
        }
    }

    @Override
    public void clickOnItem(MenuItems sideMenuItem) {
        try {
            open();

            WebElement itemCell = Objects.requireNonNull(getItemCell((sideMenuItem)));

            if (itemCell.isDisplayed()) {
                itemCell.click();
            }
            else {
                MobileGestures.scrollTo(
                        driver, getFormattedIconId(sideMenuItem), MobileGestures.ScrollingDirection.DOWN);

                itemCell.click();
            }

        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.reportStep(noSuchElementException);
        }
        catch (NullPointerException nullPointerException) {
            Logger.reportStep(nullPointerException);
            throw nullPointerException;
        }
    }

    @Override
    public void clickOnEditions() {
        try {
            open();
            driver.findElements(Elements.IOS_SIDEMENU_ICONS).get(0).click();
        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.reportStep(noSuchElementException);
        }
    }

    private By getFormattedIconId(MenuItems sideMenuItem) {
        String menuPrefix = "";

        if (sideMenuItem == MenuItems.PORTFOLIO
                || sideMenuItem == MenuItems.SETTINGS
                || sideMenuItem == MenuItems.SIGNOUT)
            menuPrefix = "_menu";

        return By.id(String.format("icn%s_%s", menuPrefix, sideMenuItem.name().toLowerCase()));
    }

    @SuppressWarnings("unchecked")
    private WebElement getItemCell(MenuItems sideMenuItem) {
        List<WebElement> sideMenuItems =
                (List<WebElement>) driver.findElements(Elements.IOS_SIDEMENU_ITEMS);

        for (WebElement item : sideMenuItems) {
            if (!item.findElements(getFormattedIconId(sideMenuItem)).isEmpty()) {
                return item;
            }
        }

        return null;
    }
}
