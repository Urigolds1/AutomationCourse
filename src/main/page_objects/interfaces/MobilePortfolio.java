package main.page_objects.interfaces;

import main.infrastructure.Instruments.InstrumentItem;

public interface MobilePortfolio {
    void goTo();

    // Adding the given instrument to the portfolio and asserting.
    void addWatchlistInstrument(InstrumentItem instrument, boolean test);

    // Removing the given instrument from the portfolio and asserting.
    void removeWatchlistInstrument(InstrumentItem instrument, boolean test);

    void addWatchlistInstruments(InstrumentItem[] instruments, boolean test);

    void removeWatchlistInstruments(InstrumentItem[] instruments, boolean test);
}
