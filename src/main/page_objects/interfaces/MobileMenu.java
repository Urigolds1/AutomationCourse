package main.page_objects.interfaces;


public interface MobileMenu {
    enum MenuItems { PORTFOLIO, SETTINGS, CURRENCY_CONVERTER, SIGNOUT, ECONOMIC_CALEDAR }

    void open();
    void clickOnItem(MenuItems sideMenuItem);
    void clickOnEditions();
}
