# Animal Farm
Welcome to the mobile automation project of the QA team at investing.com!
Our project wraps Appium (mobile automation tool based on Selenium) in order to provide an infrastructure that is used to build and run automated tests on the mobile applications of Investing.com on both iOS and Android physical devices.

![bullandbear](https://ci6.googleusercontent.com/proxy/NWLx6M5mGr5IqQaRGOqkhNwgkXcBXu91ZhhBWfTjW-Us14Hnmao828tS4Gc-MhLisIVluYBAoIxvPXRP7z_Oay7KEGtV=s0-d-e1-ft#https://i-invdn-com.akamaized.net/inv_sig_logo.jpg)![appiumlogo](https://www.indiumsoft.com/Blog/wp-content/uploads/2017/09/appium-logo.png)

## Installation
The project works best in IntelliJ.
Dependencies: __Appium 7.0.0__, __TestNG__. Both should be installed using __*Maven*__.

## Project Structure
The project's main files lies in the __src/__ directory and are divided into __*main*__ and __*test*__. All the __*infrastructure*__, as well as __*page object*__ resides inside the *main* directory. *Page objects* have different directories for __*iOS*__ components and __*Android*__ components, as well as a universal directory for the __*interfaces*__ that provides the scaffolds for those __*page objects*__ of both the platforms. The __*test*__ directory contains all the ___TestNG___ classes, and is the place where the tests are being ran from.

## Quick Start - Writing a New Test
Tests are merely a series of calls to methods that are implemented in *page objects*. To write a new test, we simply create a new Java class under the *test* directory, and adding the following:

```java
public class LocalWatchlistPortfolio {
    private MobileDriver<?> driver;
    
    MobilePortfolio portfolio;

    @BeforeClass
    public void ExampleTestSetup() {
        driver = Context.getIPhoneDriver(
            "iPhone 6+",
            "0f5f8670d6bd177d43f864e6bf3dca430fa0a73b"
        );
        
        portfolio = new Portfolio().getPageObject(driver);
    }

    @Test
    public void ExampleTestOfMobileFeature() {
        portfolio.goTo();
        portfolio.addWatchlistInstrument(Instruments.VXUS, true);
    }

    @AfterClass
    public void ExampleTestTeardown() {
        driver.quit();
    }
}
```
As you can see in the snippet above, the test should consist of a __MobileDriver__ instance (imported from Appium), a __@BeforeClass__ method that sets the driver to the required context of the test, that is the device we would like to run the test on, at least one __@Test__ method, which is the test itself and finally an __@AfterClass__ method to teardown the driver and conclude the test

In order to access a page object, for instance the Portfolio page object, we create a new instance of it and passing it our driver:
```java
MobilePortfolio portfolio = new Portfolio().getPageObject(driver);
```
We can use it hereafter to operate automatic actions:
```java
portfolio.addWatchlistInstrument(Instruments.VXUS, true);
```
Here we initiated the portfolio page object and then called a method that will add the __Vanguard Total International Stocks ETF__, as well as assert that this instrument was indeed added to the watchlist.

## Quick Start - Writing a Page Object
Page objects are the components used to wrap the actions a certain page (or screen, or activity - depends on your terminology) provides, so it can be later called inside a *test* class. Simply put, those are the building blocks of our automated tests.

Behind the scene, a page object is made of 4 different parts - an __*interface*__, an __*iOS implementation*__ and an __*Android implementation*__ where the two latters are implementations of the former. Another part is the __*factory*__ class that will return the right implementation depending on the __MobileDriver__ it is given.

We'll use Portfolio as an example:
The interface, named __MobilePortfolio__, is there to define the scaffold that must be used when implementing this page object on either iOS or Android. This is a crucial step, as the end tests are cross-platform E2E tests that will use the exact same flow, with the exact same method names. Only the actual implementation of such methods is different between iOS and Android and will be determined by the given driver using a factory design pattern.
```java
public interface MobilePortfolio {
    void goTo();

    // Adding the given instrument to the portfolio and asserting.
    void addWatchlistInstrument(InstrumentItem instrument, boolean test);

    // Removing the given instrument from the portfolio and asserting.
    void removeWatchlistInstrument(InstrumentItem instrument, boolean test);
}
```
The implementation for iOS, named __IOSPortfolio__:
```java
public class IOSPortfolio implements MobilePortfolio {
    private IOSDriver<?> driver;

    public IOSPortfolio(IOSDriver<?> driver) {
        this.driver = driver;
    }
    
    @Override
    public void goTo() {
        // implementation goes here
        ...
    }

    @Override
    public void addWatchlistInstrument(InstrumentItem instrument, boolean test) {
        // implementation goes here
        ...
    }

    @Override
    public void removeWatchlistInstrument(InstrumentItem instrument, boolean test) {
        // implementation goes here
        ...
    }
}
```

The Android implementation, named __AndroidPortfolio__:
```java
public class AndroidPortfolio implements MobilePortfolio {
    public AndroidDriver<?> driver;

    public AndroidPortfolio(AndroidDriver<?> driver) {
        this.driver = driver;
    }
    
    @Override
    public void goTo() {
        // implementation goes here
        ...
    }

    @Override
    public void addWatchlistInstrument(Instruments.InstrumentItem instrument, boolean test) {
        // implementation goes here
        ...
    }

    @Override
    public void removeWatchlistInstrument(Instruments.InstrumentItem instrument, boolean test) {
        // implementation goes here
        ...
    }

    private void clickOnAdditionButton(boolean isSignedIn) {
        // any internal class helpers should be declared private
        ...
    }
}
```

And finally, the factory that concludes it all and returns the desired implementation according to the given device being tested, called simply __Portfolio__:
```java
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
```
## Quick Start - Add a New Device
Devices are defined with all their desired capabilities (for further information on those please refer Appium's documentation) inside the Context class that resides under the __*infrastructure*__ directory.
For instance, an iPhone 6 device would be defined as follows:
```java
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
            return new IOSDriver<>(new URL("http://127.0.0.1:5000/wd/hub"), capabilities);
        }
        catch (MalformedURLException exception) {
            System.out.println(String.format("Couldn't create iPhone driver, failed at: %s", exception.getMessage()));
            return null;
        }
    }
```
Add your physical device, with it's type and UDID, as well as a unique animal name, to the Devices interface:
```java
Device SHEEP = new Device("2305e21cec8a158d66b01513945328d2da581412", "iPhone 8+");
```

Please make sure your UDID is correct as this is the reference to your physical device which is connected to the machine you're running the tests from. To use your new device in a test class:
```java
driver = Context.getIPhoneDriver(Devices.SHEEP.type, Devices.SHEEP.udid);
```

## Coding Conventions
This project follows the coding conventions that are agreed upon in the QA team and outlined [here](https://confluencessl.forexpros.com/pages/viewpage.action?title=Coding+Conventions&spaceKey=QA).

## Contributions
Introducing new features or bug fixes into this repository is done according to the semi-gitflow agreed upon in the QA team and detailed [here](https://confluencessl.forexpros.com/display/QA/Automation%27s+Git+Workflow).
