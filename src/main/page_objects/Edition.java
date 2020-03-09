package main.page_objects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.page_objects.android.AndroidEdition;
import main.page_objects.interfaces.MobileEdition;
import main.page_objects.ios.IOSEdition;


public class Edition {
    public MobileEdition getPageObject(MobileDriver<?> driver) {
        if (driver == null) {
            return null;
        }
        if (driver instanceof IOSDriver<?>) {
            return new IOSEdition((IOSDriver<?>) driver);
        }
        if (driver instanceof AndroidDriver<?>) {
            return new AndroidEdition((AndroidDriver<?>) driver);
        }

        return null;
    }
}
