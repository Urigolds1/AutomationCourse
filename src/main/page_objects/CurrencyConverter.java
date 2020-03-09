package main.page_objects;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.page_objects.android.AndroidCurrencyConverter;
import main.page_objects.interfaces.MobileCurrencyConverter;
import main.page_objects.ios.IOSCurrencyConverter;


public class CurrencyConverter {
    public MobileCurrencyConverter getPageObject(MobileDriver<?> driver) {
        if (driver == null) {
            return null;
        }
        if (driver instanceof IOSDriver<?>) {
            return new IOSCurrencyConverter((IOSDriver<?>) driver);
        }
        if (driver instanceof AndroidDriver<?>) {
            return new AndroidCurrencyConverter((AndroidDriver<?>) driver);
        }

        return null;
    }
}
