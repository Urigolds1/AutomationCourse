package main.infrastructure;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public abstract class MobileGestures {
    public enum ScrollingDirection { DOWN, UP }
    public enum ScrollingIntensity { AGGRESIVE, GENTLE }

    public static void scrollTo(MobileDriver<?> driver, By elementLocator, ScrollingDirection direction) {
        Dimension screenSize = driver.manage().window().getSize();

        int startX = (int) (screenSize.width * 0.5);
        int startY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? 0.4 : 0.1));
        int endY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? 0.1 : 0.4));

        TouchAction scrollAction = new TouchAction(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
            .moveTo(PointOption.point(startX, endY))
            .release();

        while (!driver.findElement(elementLocator).isDisplayed()) {
            try {
                scrollAction.perform();
            }
            catch (WebDriverException emptyActionListException) {
                return;
            }
        }
    }

    public static void scrollTo(MobileDriver<?> driver, WebElement element, ScrollingDirection direction) {
        Dimension screenSize = driver.manage().window().getSize();

        int startX = (int) (screenSize.width * 0.5);
        int startY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? 0.4 : 0.1));
        int endY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? 0.1 : 0.4));

        TouchAction scrollAction = new TouchAction(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
            .moveTo(PointOption.point(startX, endY))
            .release();

        while (!element.isDisplayed()) {
            try {
                scrollAction.perform();
            }
            catch (WebDriverException emptyActionListException) {
                return;
            }
        }
    }

    public static void scrollTo(
            MobileDriver<?> driver, int durationInMillis, ScrollingDirection direction, ScrollingIntensity intensity) {

        Dimension screenSize = driver.manage().window().getSize();

        double topAnchor = intensity == ScrollingIntensity.AGGRESIVE ? 0.9 : 0.2;

        int startX = (int) (screenSize.width * 0.5);
        int startY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? topAnchor : 0.1));
        int endY = (int) (screenSize.height * (direction == ScrollingDirection.DOWN ? 0.1 : topAnchor));

        new TouchAction(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMillis)))
            .moveTo(PointOption.point(startX, endY))
            .release()
            .perform();
    }
}