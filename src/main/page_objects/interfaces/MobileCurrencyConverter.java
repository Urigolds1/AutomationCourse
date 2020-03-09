package main.page_objects.interfaces;

import main.infrastructure.Currencies;

public interface MobileCurrencyConverter {
    enum CurrencyField { TOP, BOTTOM }

    void goTo();
    void changeCurrency(Currencies.Currency currency, CurrencyField currencyField);
    void setCurrency(double amount, CurrencyField currencyField);
    void flipCurrencies(boolean test);
    double getCurrencyValue(CurrencyField currencyField);
}
