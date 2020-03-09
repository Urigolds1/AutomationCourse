package main.page_objects.ios;


import io.appium.java_client.ios.IOSDriver;
import main.infrastructure.Elements;
import main.infrastructure.Users;
import main.page_objects.interfaces.MobileMenu;
import main.page_objects.interfaces.MobileSignIn;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class IOSSignIn implements MobileSignIn {
    private IOSDriver<?> driver;
    private IOSMenu sideMenu;

    public IOSSignIn(IOSDriver<?> driver) {
        this.driver = driver;
        this.sideMenu = new IOSMenu(driver);
    }

    @Override
    public void signIn(Users.User user) {
        sideMenu.open();
        driver.findElement(Elements.IOS_SIDEMENU_SIGNIN_BUTTON).click();

        driver.findElement(Elements.IOS_SIGN_IN_EMAIL_TEXT_FIELD).sendKeys(user.email);
        driver.findElement(Elements.IOS_SIGN_IN_PASSWORD_TEXT_FIELD).sendKeys(user.password);

        driver.findElement(Elements.IOS_SIGN_IN_BUTTON).click();

        WebDriverWait waitForSignIn = new WebDriverWait(driver, 10);
        waitForSignIn.until(ExpectedConditions.presenceOfElementLocated(Elements.IOS_SIGN_IN_VERIFICATION));

        driver.findElement(Elements.IOS_SIDEMENU_OPEN_BUTTON).click();
    }

    @Override
    public void signOut() {
        sideMenu.open();
        sideMenu.clickOnItem(MobileMenu.SideMenuItems.SIGNOUT);

        driver.findElement(Elements.IOS_SIGN_OUT_CONFIRM).click();
    }
}
