package org.example.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class Category {
    private static Logger log = LogManager.getLogger(Category.class);

    private static SelenideElement category = $x("//span[text()='Продукты']/preceding-sibling::img/parent::a/parent::*");
    private static SelenideElement navigation = category.find(By.xpath("./parent::*"));
    private static SelenideElement banners = $x("//*[@data-apiary-widget-name='@MarketNode/TopMenuTabs']//*[@data-apiary-widget-name=\"@MarketNode/Showcase\"]");
    private static SelenideElement productItem = $x("//*[text()='Продукты']/preceding-sibling::*//..");

    public Category() {
        category.shouldBe(Condition.exist);
        navigation.shouldBe(Condition.exist);
        banners.isDisplayed();
    }

    public void selectItem() {
        productItem.scrollTo().click();
        log.trace("Выбрали категорию Продукты");
    }
}
