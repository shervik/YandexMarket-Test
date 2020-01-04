package org.example.elements;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;

public enum SubCategoryBuy {
    ВСЕ_ДЛЯ_ВЫПЕЧКИ($x("//a[text()='Все для выпечки']/parent::div[@data-zone-name=\"link\"]/a")),
    КОНСЕРВАЦИЯ($x("//a[text()='Консервация']/parent::div[@data-zone-name=\"link\"]/a")),
    ФРУКТЫ_ОВОЩИ($x("//a[text()='Фрукты, овощи и грибы']/parent::div[@data-zone-name=\"link\"]/a")),
    МЯСО($x("//a[text()='Мясная гастрономия']/parent::div[@data-zone-name=\"link\"]/a")),
    РЫБА($x("//a[text()='Рыбная гастрономия']/parent::div[@data-zone-name=\"link\"]/a")),
    ЗАМОРОЗКА($x("//a[text()='Замороженные продукты']/parent::div[@data-zone-name=\"link\"]/a")),
    ХЛЕБ($x("//a[text()='Хлеб и хлебобулочные изделия']/parent::div[@data-zone-name=\"link\"]/a")),
    СОУСЫ($x("//a[text()='Соусы, кетчупы']/parent::div[@data-zone-name=\"link\"]/a")),
    СНЭКИ($x("//a[text()='Снэки']/parent::div[@data-zone-name=\"link\"]/a")),
    ОРЕХИ($x("//a[text()='Орехи, семена, сухофрукты']/parent::div[@data-zone-name=\"link\"]/a")),
    КУЛИНАРИЯ($x("//a[text()='Кулинария']/parent::div[@data-zone-name=\"link\"]/a")),
    КУХНИ_МИРА($x("//a[text()='Лапша, макаронные изделия']/parent::div[@data-zone-name=\"link\"]/a"));

    private SelenideElement el;
    private static Logger log = LogManager.getLogger(SubCategoryBuy.class);

    SubCategoryBuy(com.codeborne.selenide.SelenideElement el) {
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
