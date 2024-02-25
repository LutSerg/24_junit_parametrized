package com.asteroster;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MuztorgWebTest {

    static Stream<Arguments> instrumentsCatalogAndSubmenues() {
        return Stream.of(
                Arguments.of("Гитары", List.of("Акустические гитары", "Электрогитары",
                        "Классические гитары", "Бас-гитары", "Акустические бас-гитары", "Гиталеле",
                        "Усилители для гитар", "Процессоры и педали эффектов", "Тюнеры и метрономы",
                        "Струны", "Гитарные аксессуары и комплектующие", "Чехлы и кейсы", "Custom shop")),
                Arguments.of("Клавишные инструменты", List.of("Синтезаторы", "Цифровые пианино",
                        "Цифровые органы", "Акустические клавишные инструменты", "MIDI-клавиатуры", "MIDI контроллеры",
                        "Оригинальные аксессуары для клавишных инструментов"))
        );
    }

    @MethodSource()
    @Tag("muztorg")
    @ParameterizedTest(name = "В разделе {0} находится подменю, котором содержатся подразделы {1}")
    void instrumentsCatalogAndSubmenues(String menuItem, List<String> submenu) {
        open("https://www.muztorg.ru/");
        $(".catalog-menu").$(byText(menuItem)).click();
        $(".catalog-submenu")
                .$$(".catalog-submenu__list li").shouldHave(CollectionCondition.texts(submenu));
    }
}
