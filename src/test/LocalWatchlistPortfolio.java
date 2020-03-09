package test;


import io.appium.java_client.MobileDriver;
import main.infrastructure.Devices;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.infrastructure.Instruments;
import main.page_objects.Portfolio;
import main.page_objects.interfaces.MobilePortfolio;
import main.infrastructure.Context;


public class LocalWatchlistPortfolio {
    private MobileDriver<?> driver;

    private MobilePortfolio portfolio;

    @BeforeClass
    public void LocalWatchlistPortfolioSetup() {
        driver = Context.getIPhoneDriver(Devices.SHEEP.type, Devices.SHEEP.udid);

        portfolio = new Portfolio().getPageObject(driver);
    }

    @Test(priority = 0)
    public void AddInstrumentToLocalWatchlistPortfolio() {
        portfolio.goTo();

        portfolio.addWatchlistInstrument(Instruments.FLWS, true);
    }

    @Test(priority = 1)
    public void RemoveInstrumentFromLocalWatchlistPortfolio() {
        portfolio.goTo();

        portfolio.removeWatchlistInstrument(Instruments.FLWS, true);
    }

    @Test(priority = 2)
    public void AddInstrumentsToLocalWatchlistPortfolio() {
        portfolio.goTo();

        Instruments.InstrumentItem[] instruments =
                new Instruments.InstrumentItem[] {Instruments.FLWS, Instruments.VOOG};
        portfolio.addWatchlistInstruments(instruments, true);
    }

    @Test(priority = 3)
    public void RemoveInstrumentsFromLocalWatchlistPortfolio() {
        portfolio.goTo();

        Instruments.InstrumentItem[] instruments =
                new Instruments.InstrumentItem[] {Instruments.VOOG, Instruments.FLWS};
        portfolio.removeWatchlistInstruments(instruments, true);
    }

    @AfterClass
    public void LocalWatchlistPortfolioTeardown() {
        driver.quit();
    }
}
