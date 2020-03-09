package main.page_objects.android;

import io.appium.java_client.android.AndroidDriver;
import main.infrastructure.Currencies;
import main.page_objects.interfaces.MobileCurrencyConverter;

public class AndroidCurrencyConverter implements MobileCurrencyConverter {
    AndroidDriver<?> driver;

    public AndroidCurrencyConverter(AndroidDriver<?> driver) { this.driver = driver; }

    @Override
    public void goTo() {

    }

    @Override
    public void changeCurrency(Currencies.Currency currency, CurrencyField currencyField) {

    }

    @Override
    public void setCurrency(double amount, CurrencyField currencyField) {

    }


    @Override
    public void flipCurrencies(boolean test) {

    }

    @Override
    public double getCurrencyValue(CurrencyField currencyField) {
        return 0;
    }
}
