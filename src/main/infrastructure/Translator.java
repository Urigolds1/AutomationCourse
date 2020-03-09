package main.infrastructure;

import java.util.HashMap;

public class Translator {
    public enum Language {
        English,
        Hebrew
    }

    public String SETTINGS;
    public String PORTFOLIO;
    public String LANGUAGES;

    public Translator(Language language) {
        switch (language) {
            case English:
                PORTFOLIO = "IOSPortfolio";
                SETTINGS = "IOSSettings";
                LANGUAGES = "Languages";
                break;

            case Hebrew:
                PORTFOLIO = "תיק השקעות";
                SETTINGS = "הגדרות";
                LANGUAGES = "שפה";
                break;
        }
    }
}
