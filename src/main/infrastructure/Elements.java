package main.infrastructure;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;


public interface Elements {
    /*
     * iOS Elements
     */
    By IOS_GENERAL_BACK_BUTTON = By.id("icn back");
    By IOS_INIT_SIGN_UP_BUTTON = By.id("Sign up for Free");

    By IOS_PORTFOLIO_INSTRUMENT_ADD_BUTTON = By.id("Add");
    By IOS_PORTFOLIO_EDIT_BUTTON = By.id("icn portfolio edit");
    By IOS_PORTFOLIO_INSTRUMENTS_CONTAINER = By.className("XCUIElementTypeTable");

    By IOS_INSTRUMENT_SEARCH_BOX = By.id("Search");
    By IOS_INSTRUMENT_APPLY_EDIT_BUTTON = By.id("icn tick");
    By IOS_INSTRUMENT_SEARCH_RESULT = By.className("XCUIElementTypeCell");
    By IOS_INSTRUMENT_ITEMS = By.className("XCUIElementTypeCell");
    By IOS_INSTRUMENT_CONFIRM_DELETE_BUTTON = By.id("X");

    By IOS_SETTINGS_ITEMS = By.className("XCUIElementTypeCell");

    By IOS_SIDEMENU_SIGNUP_BUTTON = By.id("Sign Up");
    By IOS_SIDEMENU_SIGNIN_BUTTON = By.id("Sign In");
    By IOS_SIDEMENU_OPEN_BUTTON = By.id("icn mainnav menu");
    By IOS_SIDEMENU_ITEMS = By.className("XCUIElementTypeCell");
    By IOS_SIDEMENU_ICONS = By.className("XCUIElementTypeImage");

    By IOS_EDITION_ITEMS = By.className("XCUIElementTypeCell");

    By IOS_SIGN_IN_EMAIL_TEXT_FIELD = By.className("XCUIElementTypeTextField");
    By IOS_SIGN_IN_PASSWORD_TEXT_FIELD = By.className("XCUIElementTypeSecureTextField");
    By IOS_SIGN_IN_BUTTON = By.id("Sign in with Email");
    By IOS_SIGN_IN_VERIFICATION = By.id("Sign Out");
    By IOS_SIGN_OUT_CONFIRM = By.id("Yes");

    By IOS_CURRENCY_CONVERTER_CURRENCIES_DROPDOWNS = By.id("Combo Field");
    By IOS_CURRENCY_CONVERTER_SEARCH_BOX = By.id("Search");
    By IOS_CURRENCY_CONVERTER_BUTTONS = By.className("XCUIElementTypeButton");
    By IOS_CURRENCY_CONVERTER_TEXT_FIELDS = By.className("XCUIElementTypeTextField");

    By IOS_ECONOMIC_CALENDAR_FILTER_BUTTON = By.id("icon filter on");
    By IOS_ECONOMIC_CALENDAR_SEARCH_BUTTON = By.id("icn mainnav search");
    By IOS_ECONOMIC_CALENDAR_SEARCH_BOX = By.id("Search event name");
    By IOS_ECONOMIC_CALENDAR_EVENT_ITEMS = By.className("XCUIElementTypeCell");
    By IOS_ECONOMIC_CALENDAR_FILTER_IMPORTANCE_ITEMS = By.className("XCUIElementTypeImage");
    By IOS_ECONOMIC_CALENDAR_FILTER_ITEMS = By.className("XCUIElementTypeCell");
    By IOS_ECONOMIC_CALENDAR_COUNTRY_ITEMS = By.className("XCUIElementTypeCell");
    By IOS_ECONOMIC_CALENDAR_COUNTRY_CHECK = By.id("More info");
    By IOS_ECONOMIC_CALENDAR_COUNTRY_ALL_BUTTON = By.id("TODO");
    int IOS_ECONOMIC_CALENDAR_FILTER_DEFAULT = 1;
    int IOS_ECONOMIC_CALENDAR_FILTER_ALL = 2;
    int IOS_ECONOMIC_CALENDAR_FILTER_CUSTOM = 3;

    /*
     * Android Elements
     */
    By ANDROID_ACTION_ITEM_1 = By.id("com.fusionmedia.investing:id/action_item_1");
    By ANDROID_ACTION_ITEM_2 = By.id("com.fusionmedia.investing:id/action_item_2");
    By ANDROID_ACTION_ITEM_3 = By.id("com.fusionmedia.investing:id/action_item_3");
    By ANDROID_ACTION_ITEM_4 = By.id("com.fusionmedia.investing:id/action_item_4");
    By ANDROID_ACTION_ITEM_5 = By.id("com.fusionmedia.investing:id/action_item_5");

    By ANDROID_MAIN_LAYOUT = By.id("com.fusionmedia.investing:id/main_layout");
    By ANDROID_INIT_SIGN_UP_BUTTON = By.id("com.fusionmedia.investing:id/sign_up_button");
    By ANDROID_GENERAL_BACK_BUTTON = ANDROID_ACTION_ITEM_1;
    By ANDROID_BACK_BUTTON = By.id("com.android.systemui:id/back");
    By ANDROID_OPEN_LAUNCHER_BUTTON = By.xpath("//*[@content-desc='Apps list']");

    By ANDROID_INSTRUMENT_ADDITION_BACK_BUTTON = ANDROID_ACTION_ITEM_1;
    By ANDROID_INSTRUMENT_SEARCH_BOX = By.id("com.fusionmedia.investing:id/menuSearchEditText");
    By ANDROID_INSTRUMENT_FIRST_SEARCH_RESULT = MobileBy.AndroidUIAutomator(
            "new UiSelector().resourceId(\"com.fusionmedia.investing:id/search_data_layout\").instance(0)");
    By ANDROID_INSTRUMENT_ITEM = By.className("android.view.ViewGroup");
    By ANDROID_INSTRUMENT_ITEM_NAME = By.id("com.fusionmedia.investing:id/instrumentName");
    By ANDROID_INSTRUMENT_ITEM_REMOVE_BUTTON = By.id("com.fusionmedia.investing:id/removeHandleIcon");

    By ANDROID_PORTFOLIO_WATCHLIST_EDIT_BUTTON = ANDROID_ACTION_ITEM_3;
    By ANDROID_PORTFOLIO_WATCHLIST_EDIT_SAVE_BUTTON = ANDROID_ACTION_ITEM_3;

    By ANDROID_MAIN_TABS_CONTAINER = By.id("com.fusionmedia.investing:id/tabs_container");
    By ANDROID_MAIN_TABS_ITEMS = By.id("com.fusionmedia.investing:id/icon");
    int ANDROID_MAIN_TABS_MARKETS = 0;
    int ANDROID_MAIN_TABS_NEWS = 1;
    int ANDROID_MAIN_TABS_CALENDAR = 2;
    int ANDROID_MAIN_TABS_PORTFOLIO = 3;
    int ANDROID_MAIN_TABS_MORE = 4;
    By ANDROID_MORE_MENU_LANGUAGES = By.id("com.fusionmedia.investing:id/language_prefs_flag");
    By ANDROID_MORE_MENU_SETTINGS_BUTTON = By.id("com.fusionmedia.investing:id/settings");
    By ANDROID_MORE_MENU_SIGNIN_BUTTON = By.id("com.fusionmedia.investing:id/signInMenuText");
    By ANDROID_MORE_MENU_SIGNOUT_BUTTON = By.id("com.fusionmedia.investing:id/signOut");

    By ANDROID_SIGN_IN_EMAIL_TEXT_FIELD = By.id("com.fusionmedia.investing:id/email");
    By ANDROID_SIGN_IN_PASSWORD_TEXT_FIELD = By.id("com.fusionmedia.investing:id/password");
    By ANDROID_SIGN_IN_BUTTON = By.id("com.fusionmedia.investing:id/send_login_button");
    By ANDROID_SIGN_IN_VERIFICATION = By.id("com.fusionmedia.investing:id/loginImage");
    By ANDROID_SIGN_OUT_CONFIRM = By.id("android:id/button2");

    By ANDROID_SETTINGS_LANGUAGE = By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Language']]");
    By ANDROID_SETTINGS_CONTAINER = By.id("com.fusionmedia.investing:id/settings_recycler_view");
    By ANDROID_SETTINGS_ITEM = By.className("android.widget.RelativeLayout");

    By ANDROID_EDITIONS_LIST = By.id("com.fusionmedia.investing:id/languages");
    By ANDROID_EDITION_ITEM_TITLE = By.id("com.fusionmedia.investing:id/title");
    By ANDROID_EDITION_ITEM = By.className("android.widget.RelativeLayout");
}
