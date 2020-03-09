package main.infrastructure;


import org.openqa.selenium.By;

public interface Instruments {
    class InstrumentItem {
        public String name;
        public String symbol;
        public String type;

        InstrumentItem(String name, String symbol, String type) {
            this.name = name;
            this.symbol = symbol;
            this.type = type;
        }
    }

    static By getIOSFormattedId(String action, InstrumentItem instrumentItem) {
        return By.id(String.format("%s %s, %s  |  %s",
                action, instrumentItem.name, instrumentItem.type, instrumentItem.symbol));
    }

    InstrumentItem VXUS =
            new InstrumentItem("Vanguard Total International Stock", "VXUS", "etf");

    InstrumentItem FLWS =
            new InstrumentItem("1-800 FLOWERS.COM", "FLWS", "Equities");

    InstrumentItem VOOG =
            new InstrumentItem("Vanguard S&P 500 Growth", "VOOG", "etf");

    InstrumentItem PG =
            new InstrumentItem("Procter & Gamble Company", "PG", "Equities");

    InstrumentItem ALHE =
            new InstrumentItem("Alony Hetz Properties and Investments Ltd", "ALHE", "Equities");
}
