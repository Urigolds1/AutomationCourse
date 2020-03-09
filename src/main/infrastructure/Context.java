package main.infrastructure;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class Context {
    public static IOSDriver<?> getIPhoneDriver(String version, String udid) {
        // Configuring the context's capabilities.
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, version);
        capabilities.setCapability(MobileCapabilityType.APP ,"/Applications/Investing.com.ipa");
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("simpleIsVisibleCheck", true);

        try {
            IOSDriver<?> driver = new IOSDriver<>(new URL("http://127.0.0.1:5000/wd/hub"), capabilities);

            try {
                WebDriverWait waitForAppToLoad = new WebDriverWait(driver, 60);
                waitForAppToLoad.until(ExpectedConditions.elementToBeClickable(Elements.IOS_INIT_SIGN_UP_BUTTON));

                driver.findElement(Elements.IOS_INIT_SIGN_UP_BUTTON).click();
            }
            catch (NoSuchElementException noSuchElementException) {
                Logger.write("Already signed in, no need to handle initiation sign in button");
            }

            return driver;
        }
        catch (MalformedURLException exception) {
            System.out.println(String.format("Couldn't create iPhone driver, failed at: %s", exception.getMessage()));
            return null;
        }
    }

    public static AndroidDriver<?> getOnePlusDriver(String device, String udid) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "net.oneplus.launcher");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "net.oneplus.launcher.Launcher");
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");

        try {
            AndroidDriver<?> driver = new AndroidDriver<>(new URL("http://127.0.0.1:5000/wd/hub"), capabilities);

            WebDriverWait waitForLauncherOpenButton = new WebDriverWait(driver, 10);
            waitForLauncherOpenButton.until(
                    ExpectedConditions.elementToBeClickable(Elements.ANDROID_OPEN_LAUNCHER_BUTTON)).click();

            WebElement element = driver.findElementById("net.oneplus.launcher:id/hidden_apps_title");
            //element.click();
            element.sendKeys("Investing");

            WebElement investingAppIcon = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Investing']")));
            investingAppIcon.click();

            try {
                WebDriverWait waitForAppToLoad = new WebDriverWait(driver, 60);
                waitForAppToLoad.until(ExpectedConditions.presenceOfElementLocated(Elements.ANDROID_MAIN_LAYOUT));

                driver.findElement(Elements.ANDROID_INIT_SIGN_UP_BUTTON).click();
            }
            catch (NoSuchElementException noSuchElementException) {
                Logger.write("Already signed in, no need to handle initiation sign in button");
            }

            return driver;
        }
        catch (MalformedURLException exception) {
            System.out.println(String.format("Couldn't create OnePlus driver, failed at: %s", exception.getMessage()));
            return null;
        }
    }
}
