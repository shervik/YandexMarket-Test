package org.example.pages;

import com.codeborne.selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.elements.Filter;
import org.example.elements.ProducerItem;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.testng.Assert;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class MarketPage {
    private static Logger log = LogManager.getLogger(MarketPage.class);

    private static SelenideElement popup18 = $x("//*[text() = 'Да, мне есть 18 лет']/parent::*");
    private static SelenideElement foodList = $x("//*[contains(@class, 'layout__col_search-results_normal')]");
    private static ElementsCollection food = $$x("//*[contains(@class, 'n-snippet-card2 i-bem')]");
    private static String foodName = ".//div[@class='n-snippet-card2__header']//a";
    private static String foodBtn = ".//a[@role='button']";

    private static SelenideElement popup = $(By.className("popup-informer__content"));

    private static SelenideElement getRandom() {
        Random random = new Random();
        int i = random.nextInt(food.size());
        return food.get(i);
    }

    public MarketPage() {
        if (popup18.exists()) {
            log.warn("Проверка, есть ли 18 лет.");
            popup18.click();
        }

        foodList.shouldBe(Condition.visible);
        food.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
    }

    public void addWishList() {
        String foodWish = ".//a[contains(@class, 'wishlist-control')]";
        SelenideElement el = getRandom();
        el.scrollTo().hover();
        el.find(By.xpath(foodWish)).click();
        log.trace("Добавляем товар в избранное");
        Assert.assertTrue(popup.exists(), "Товар не добавился в избранное!");
    }

    public Filter setPrice() {
        return new Filter();
    }

    public Filter clearCheckBox() {
        return new Filter();
    }

    public boolean chooseProducer() {
        SelenideElement element = ProducerItem.getRandom();

        try {
            element.scrollTo().click();
        } catch (NullPointerException ex) {
            log.trace("Переход на следующий шаг.");
            return false;

        }

        new MarketPage();
        Selenide.sleep(2000);
        for (int i = 0; i < food.size(); i+=5) {
            String foodItem = food.get(i).find(By.xpath(foodName)).getText().toLowerCase();
            log.debug(foodItem);

            try {
                Assert.assertTrue(foodItem.contains(element.getText().toLowerCase()), "Товар не от выбранного производителя.");
            } catch (AssertionError ex) {
                log.error("Товар не от выбранного производителя.");
                break;
            }
        }
        return true;
    }

    public void selectFoodItem() {
        SelenideElement el = getRandom();
        log.trace("Выбрали случайный товар");
        String name = el.find(By.xpath(foodName)).getText().substring(0, 5);
        String price = el.find(By.xpath(".//div[@class='price']")).getText().replace("₽", "");

        SelenideElement offer = el.find(By.xpath(".//a[contains(@class, 'link_type_prices')]"));
        SelenideElement btn = el.find(By.xpath(foodBtn));

        if (offer.exists()) {
            offer.shouldBe(Condition.visible);
            offer.click();
            switchTo().window(1);
            Selenide.sleep(5000);
            log.trace("Перешли в предложения");
            pressBtn();
            switchTo().window(2);
            Selenide.sleep(5000);
        } else if (btn.exists()) {
            btn.shouldBe(Condition.visible);
            btn.click();
            switchTo().window(1);
            Selenide.sleep(5000);
            log.trace("Перешли в магазин");
        } else log.error("Никуда не перешли.");

        log.debug(price);
        if (getValue(price, price.trim())) {
            log.trace("Цена соответствует запомненной.");
        } else {
            log.error("Цена НЕ соответствует запомненной!");
        }

        log.debug(name);
        if (getValue(name, name.trim())) {
            log.trace("Название соответствует запомненному.");
        } else {
            log.error("Название НЕ соответствует запомненному!");
        }
    }

    private static boolean getValue(String value_space, String value_without_space) {
        String xpath1 = String.format("//*[contains(ceiling(text()), '%s')]/text()", value_space);
        String xpath2 = String.format("//*[contains(ceiling(text()), '%s')]/text()", value_without_space);

        try {
            $x(xpath1);
            $x(xpath2);
            return true;
        } catch (InvalidSelectorException ex) {
            return false;
        }
    }

    private static void pressBtn() {
        SelenideElement productBlock = $x("//*[@class='layout layout_type_maya']");
        productBlock.find(By.xpath(foodBtn)).click();
    }
}
