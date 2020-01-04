package org.example.elements;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;

public enum SubCategoryBasic {
    ЧАЙ_КОФЕ_КАКАО($x("//a[text()='Чай, кофе, какао']/parent::div[@data-zone-name=\"link\"]/a")),
    КОНДИТЕРКА($x("//a[text()='Кондитерские изделия']/parent::div[@data-zone-name=\"link\"]/a")),
    БЕЗАЛКОГОЛЬНЫЕ($x("//a[text()='Безалкогольные напитки']/parent::div[@data-zone-name=\"link\"]/a")),
    ЗДОРОВОЕ_ПИТАНИЕ($x("//a[text()='Здоровое питание']/parent::div[@data-zone-name=\"link\"]/a")),
    МОЛОКО($x("//a[text()='Молочная гастрономия']/parent::div[@data-zone-name=\"link\"]/a")),
    АЛКОГОЛЬ($x("//a[text()='Алкоголь']/parent::div[@data-zone-name=\"link\"]/a")),
    МАКАРОНЫ($x("//a[text()='Макароны, крупы, бакалея']/parent::div[@data-zone-name=\"link\"]/a"));

    private SelenideElement el;
    private static Logger log = LogManager.getLogger(SubCategoryBasic.class);

    SubCategoryBasic(SelenideElement el) {
        this.el = el;
    }

    public SelenideElement getValue() {
        return el;
    }

    public static SelenideElement getRandom() {
        Random random = new Random();
        SelenideElement cat = values()[random.nextInt(values().length)].getValue();
        log.trace("Выбрана случайная подкатегория {}.", cat.getText());
        return cat;
    }
}
