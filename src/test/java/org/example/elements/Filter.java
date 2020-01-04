package org.example.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.MarketPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Filter {
    private static Logger log = LogManager.getLogger(Filter.class);

    private static SelenideElement filter = $x("//*[@data-zone-name=\"search-filters-aside\"]");
    private static SelenideElement price = $x("//legend[contains(text(),\"Цена\")]/following-sibling::div/ul");
    private static SelenideElement checkBox = $(By.id("onstock"));    //input[@name = 'В продаже']");
    private static SelenideElement clearCheckBoxFrom = $x("//button[@aria-label=\"Очистить поле Цена от\"]");
    private static SelenideElement clearCheckBoxTo = $x("//button[@aria-label=\"Очистить поле Цена до\"]");
    private static SelenideElement producer = $x("//legend[contains(text(),\"Производитель\")]/following-sibling::ul");
    private static SelenideElement stars = $x("//legend[contains(text(),\"Рейтинг магазина\")]/following-sibling::ul");
    private static SelenideElement delivery = $x("//legend[contains(text(),\"Способ доставки\")" + "]/following" +
            "-sibling::ul");
    private static SelenideElement payment = $x("//legend[contains(text(),\"Способы оплаты\")]/following-sibling::ul");
    private static SelenideElement shops = $x("//legend[contains(text(),\"Магазины\")]/following-sibling::ul");

    private static SelenideElement viewtype = $x("//input[@class = 'radio-button__control']");

    public Filter() {
        filter.shouldBe(Condition.visible);
        try {
            producer.shouldBe(Condition.visible);
            stars.shouldBe(Condition.visible);
            delivery.shouldBe(Condition.visible);
            payment.shouldBe(Condition.visible);
            shops.shouldBe(Condition.visible);

            log.debug("Все фильтры присутствуют.");
        } catch (ElementNotFound ex) {
            log.error("Не все фильтры присутствуют!");
        }
    }

    public static void setViewtype() {
        if (!viewtype.isSelected()) {
            log.trace("Активируем тип отображения");
            viewtype.click();
        }
    }

    public static void setCheckBox() {
        if (!checkBox.isSelected()) {
            log.trace("Активируем чекбок");
            checkBox.click();
        }
    }

    public void setPriceFrom() {
        String maxPrice = price.find(By.xpath(".//input[@name=\"Цена до\"]")).getAttribute("placeholder");
        price.find(By.xpath(".//input[@name=\"Цена от\"]")).setValue(maxPrice);
        log.trace("Установили цену От");
        new MarketPage();
        Selenide.sleep(2000);
    }

    public void setPriceTo() {
        String minPrice = price.find(By.xpath(".//input[@name=\"Цена от\"]")).getAttribute("placeholder");
        price.find(By.xpath(".//input[@name=\"Цена до\"]")).setValue(minPrice);
        log.trace("Устанавили цену До");
        new MarketPage();
        Selenide.sleep(2000);
    }

    public void clearCheckBoxFrom() {
        clearCheckBoxFrom.click();
        log.trace("Очистили цену От");
        new MarketPage();
        Selenide.sleep(2000);
    }

    public void clearCheckBoxTo() {
        clearCheckBoxTo.click();
        log.trace("Очистили цену До");
        new MarketPage();
        Selenide.sleep(2000);
    }
}
