package main.infrastructure;

public interface Currencies {
    class Currency {
        public String name;
        public String abbreviation;

        public Currency(String name, String abbreviation) {
            this.name = name;
            this.abbreviation = abbreviation;
        }
    }

    Currency ILS = new Currency("Israeli Shekel", "ILS");
    Currency USD = new Currency("US Dollar", "USD");
    Currency EUR = new Currency("Euro", "EUR");
}
