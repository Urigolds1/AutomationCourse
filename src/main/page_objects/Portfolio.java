package main.page_objects;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.page_objects.android.AndroidPortfolio;
import main.page_objects.interfaces.MobilePortfolio;
import main.page_objects.ios.IOSPortfolio;

public class Portfolio {
    public MobilePortfolio getPageObject(MobileDriver<?> driver) {
        if (driver == null) {
            return null;
        }
        if (driver instanceof IOSDriver<?>) {
            return new IOSPortfolio((IOSDriver<?>) driver);
        }
        if (driver instanceof AndroidDriver<?>) {
            return new AndroidPortfolio((AndroidDriver<?>) driver);
        }

        return null;
    }
}
