package main.page_objects.ios;


import main.infrastructure.Elements;
import main.infrastructure.Logger;
import main.infrastructure.MobileGestures;

import io.appium.java_client.ios.IOSDriver;
import main.page_objects.interfaces.MobileEdition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;


public class IOSEdition implements MobileEdition {
    private IOSDriver<?> driver;

    public IOSEdition(IOSDriver<?> driver) {
        this.driver = driver;
    }

    @SuppressWarnings("unchecked")
    private WebElement getEditionCell(String language) {
        List<WebElement> editions =
                (List<WebElement>) driver.findElements(Elements.IOS_EDITION_ITEMS);

        for (WebElement edition : editions) {
            if (!edition.findElements(By.id(language)).isEmpty()) {
                return edition;
            }
        }

        return null;
    }

    @Override
    public void changeEdition(String language) {
        try {
            IOSSettings settings = new IOSSettings(driver);
            settings.clickOnLanguage();

            WebElement editionCell = getEditionCell(language);

            if (Objects.requireNonNull(editionCell).isDisplayed()) {
                editionCell.click();
                driver.findElement(Elements.IOS_GENERAL_BACK_BUTTON).click();
            }
            else {
                MobileGestures.scrollTo(driver, By.id(language), MobileGestures.ScrollingDirection.DOWN);
                editionCell.click();
            }

        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.reportStep(noSuchElementException);
        }

        WebDriverWait waitForAppToReload = new WebDriverWait(driver, 120);
        waitForAppToReload.until(ExpectedConditions.elementToBeClickable(Elements.IOS_SIDEMENU_OPEN_BUTTON));
    }
}
