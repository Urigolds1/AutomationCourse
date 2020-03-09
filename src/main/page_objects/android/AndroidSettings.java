package main.page_objects.android;


import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Elements;
import main.page_objects.interfaces.MobileSettings;


public class AndroidSettings implements MobileSettings {
    public AndroidDriver<?> driver;

    public AndroidSettings(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    @Override
    public void clickOnItem(String name) {
        // TODO
    }

    public void clickOnLanguage() {
        driver.findElement(Elements.ANDROID_SETTINGS_CONTAINER)
                .findElements(Elements.ANDROID_SETTINGS_ITEM).get(0).click();
    }
}
