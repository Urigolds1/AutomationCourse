package main.page_objects.ios;


import io.appium.java_client.ios.IOSDriver;
import main.infrastructure.Elements;
import main.page_objects.interfaces.MobileSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


@SuppressWarnings("unchecked")
public class IOSSettings implements MobileSettings {
    public IOSDriver<?> driver;

    public IOSSettings(IOSDriver<?> driver) {
        this.driver = driver;
    }

    public void clickOnLanguage() {
        List<WebElement> settings = (List<WebElement>) driver.findElements(Elements.IOS_SETTINGS_ITEMS);
        settings.get(0).click(); // TODO: can make it more robust by taking the given language from inside the cell.
    }

    // TODO: need to use the Translator class here.
    @Override
    public void clickOnItem(String name) {
        List<WebElement> settings = (List<WebElement>) driver.findElements(Elements.IOS_SETTINGS_ITEMS);

        for (WebElement settingItem : settings) {
            if (!settingItem.findElements(By.id(name)).isEmpty()) {
                settingItem.click();
                return;
            }
        }
    }
}
