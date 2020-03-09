package main.page_objects.android;


import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Elements;
import main.infrastructure.Logger;
import main.infrastructure.MobileGestures;
import main.page_objects.interfaces.MobileEdition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;


public class AndroidEdition implements MobileEdition {
    public AndroidDriver<?> driver;

    public AndroidEdition(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void changeEdition(String language) {
        try {
            AndroidSettings settings = new AndroidSettings(driver);
            settings.clickOnLanguage();

            clickOnEditionItem(language);

            WebDriverWait waitForAppToReload = new WebDriverWait(driver, 120);
            waitForAppToReload.until(
                    ExpectedConditions.elementToBeClickable(Elements.ANDROID_INIT_SIGN_UP_BUTTON)).click();
        }
        catch (NoSuchElementException noSuchElementException) {
            Logger.reportStep(noSuchElementException);
        }
    }

    private void clickOnEditionItem(String language) {
        WebElement editionList = driver.findElement(Elements.ANDROID_EDITIONS_LIST);

        List<WebElement> editions = editionList.findElements(Elements.ANDROID_EDITION_ITEM);

        for (WebElement edition : editions) {
            if (edition.findElement(Elements.ANDROID_EDITION_ITEM_TITLE).getText().equals(language)) {
                edition.click();
                return;
            }
            else {
                MobileGestures.scrollTo(driver, 10000,
                        MobileGestures.ScrollingDirection.DOWN, MobileGestures.ScrollingIntensity.GENTLE);
            }
        }
    }
}
