package org.example.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FoodStuffPage {
    private static Logger log = LogManager.getLogger(FoodStuffPage.class);

    private static SelenideElement topCategory = $x("//*[text() = \"Популярные " +
            "категории\"]/ancestor::div[@class='theme_light']");
    private static SelenideElement topGoods = $x("//*[starts-with(text(), \"Популярный\")" +
            "]/ancestor::div[@class='theme_light']");
    private static ElementsCollection discounts = $$x("//*[contains(text(), 'скидк')]/ancestor::div[@class='theme_light']");
    private static SelenideElement topReviews = $x("//*[text() = 'Популярные отзывы']/ancestor::div[@class='theme_light']");
    private static SelenideElement footer = $x("//div[@data-zone-name=\"footer\"]");

    private static ElementsCollection productsItem = $$x("//a/parent::div[@data-zone-name=\"link\"]");

    public static ElementsCollection getProductsItem() {
        return productsItem;
    }

    private static void scrollTo (SelenideElement webElement) {
        while (!webElement.isDisplayed()) {
            Selenide.executeJavaScript("window.scrollBy(0,100)");
        }
    }

    public FoodStuffPage() {
        try {
            topCategory.shouldBe(Condition.visible);

            scrollTo(discounts.last());

            topGoods.shouldBe(Condition.visible);
            discounts.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
            topReviews.shouldBe(Condition.visible);
            footer.exists();

            log.debug("Все блоки видны на странице.");
        } catch (ElementNotFound ex) {
            log.error("Не все блоки присутствуют на странице!");
        }
    }
}
