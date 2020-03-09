package main.page_objects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.page_objects.android.AndroidMenu;
import main.page_objects.interfaces.MobileMenu;
import main.page_objects.ios.IOSMenu;


public class Menu {
    public MobileMenu getPageObject(MobileDriver<?> driver) {
        if (driver == null) {
            return null;
        }
        if (driver instanceof IOSDriver<?>) {
            return new IOSMenu((IOSDriver<?>) driver);
        }
        if (driver instanceof AndroidDriver<?>) {
            return new AndroidMenu((AndroidDriver<?>) driver);
        }

        return null;
    }
}
