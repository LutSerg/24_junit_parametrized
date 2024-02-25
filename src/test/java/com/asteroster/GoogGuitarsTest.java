package com.asteroster;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class GoogGuitarsTest {
    @BeforeEach
    void setUp() {
        open("https://good-guitars.ru/");
    }

    @ValueSource(strings = {
            "Гитары", "Усиление", "Аксессуары", "Услуги"
    })
    @Tag("goodguitars")
    @ParameterizedTest(name = "При наведении курсора на раздел {0} появляется выпадающее меню")
    void hoverMenuAvailableTest(String menuItem) {
        $("#primary-nav").$(byText(menuItem)).hover();
        $$(".megamenu-column-inner").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Гитары, https://good-guitars.ru/allguitars/",
            "Усиление, https://good-guitars.ru/amps",
            "Аксессуары, https://good-guitars.ru/accessories",
            "Услуги, https://good-guitars.ru/uslugi/"
    })
    @Tag("goodguitars")
    @ParameterizedTest(name = "При попадании в раздел {0} ссылка в браузере имеет значение {1}")
    void checkMenuLinks(String menuItem, String menuLink) {
        $("#primary-nav").$(byText(menuItem)).click();
        webdriver().shouldHave(url(menuLink));

    }
}
