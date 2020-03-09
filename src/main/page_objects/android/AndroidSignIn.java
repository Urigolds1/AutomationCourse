package main.page_objects.android;


import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Elements;
import main.infrastructure.Users;
import main.page_objects.interfaces.MobileMenu;
import main.page_objects.interfaces.MobileSignIn;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AndroidSignIn implements MobileSignIn {
    private AndroidDriver<?> driver;
    private AndroidMenu sideMenu;

    public AndroidSignIn(AndroidDriver<?> driver) {
        this.driver = driver;
        this.sideMenu = new AndroidMenu(driver);
    }

    @Override
    public void signIn(Users.User user) {
        sideMenu.open();
        driver.findElement(Elements.ANDROID_SIDEMENU_SIGNIN_BUTTON).click();

        WebDriverWait waitForSignInFields = new WebDriverWait(driver, 10);
        waitForSignInFields.until(
                ExpectedConditions.presenceOfElementLocated(Elements.ANDROID_SIGN_IN_EMAIL_TEXT_FIELD));

        driver.findElement(Elements.ANDROID_SIGN_IN_EMAIL_TEXT_FIELD).sendKeys(user.email);
        driver.findElement(Elements.ANDROID_SIGN_IN_PASSWORD_TEXT_FIELD).sendKeys(user.password);

        driver.findElement(Elements.ANDROID_SIGN_IN_BUTTON).click();

        sideMenu.open();
        WebDriverWait waitForSignIn = new WebDriverWait(driver, 10);
        waitForSignIn.until(ExpectedConditions.presenceOfElementLocated(Elements.ANDROID_SIGN_IN_VERIFICATION));
    }

    @Override
    public void signOut() {
        sideMenu.open();
        sideMenu.clickOnItem(MobileMenu.SideMenuItems.SIGNOUT);

        driver.findElement(Elements.ANDROID_SIGN_OUT_CONFIRM).click();
    }
}
