package main.page_objects;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.page_objects.android.AndroidSignIn;
import main.page_objects.interfaces.MobileSignIn;
import main.page_objects.ios.IOSSignIn;


public class SignIn {
    public MobileSignIn getPageObject(MobileDriver<?> driver) {
        if (driver == null) {
            return null;
        }
        if (driver instanceof IOSDriver<?>) {
            return new IOSSignIn((IOSDriver<?>) driver);
        }
        if (driver instanceof AndroidDriver<?>) {
            return new AndroidSignIn((AndroidDriver<?>) driver);
        }

        return null;
    }
}
