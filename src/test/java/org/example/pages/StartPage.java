package org.example.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.elements.Category;
import org.example.elements.Region;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    private static Logger log = LogManager.getLogger(StartPage.class);

    private static SelenideElement searchInput = $(By.id("header-search"));

    private static SelenideElement location = $x("//*[contains(@class, 'header2-region')]");
    private static SelenideElement locChangeBtn = $x("//*[contains(@class, 'n-region-notification__change')]");
    private static SelenideElement locOKBtn = $x("//*[contains(@class, 'n-region-notification__ok')]");

    private static SelenideElement bannerBlock = $x("//img[@alt='Баннер']//..");
    private static SelenideElement offerBlock = $x("//*[starts-with(text(),'Приглядитесь')]/ancestor::div[@class='theme_light']");
    private static ElementsCollection recommendBlock = $$x("//h3/following-sibling::*/ancestor::*[contains(@class, 'titleElemWrapper')]/ancestor::*[contains(@class, 'section')]");
    private static SelenideElement magazineBlock = $x("//*[text() = \"Журнал Маркета\"]/ancestor::div[@class='theme_light']");
    private static SelenideElement reviewsBlock = $x("//*[starts-with(text(),'Отзывы')]/ancestor::div[@class='theme_light']");
    private static SelenideElement brandsBlock = $x("//*[text() = \"Популярные бренды\"]/ancestor::div[@class='theme_light']");
    private static SelenideElement footer = $x("//div[@data-zone-name=\"footer\"]");
    private static SelenideElement aside = $x("//*[contains(@data-apiary-widget-name, '@MarketNode/') and contains(@data-apiary-widget-id, '/content/cms/2/11128182')]/parent::*");

    private static SelenideElement categoryBtn = $x("//span[text() = 'Все категории']");

    private static void scrollTo (SelenideElement webElement) {
        while (!webElement.isDisplayed()) {
            Selenide.executeJavaScript("window.scrollBy(0,100)");
        }
    }
    
    public StartPage() {
        try {
            searchInput.shouldBe(Condition.visible);
            location.shouldBe(Condition.visible);

            scrollTo(brandsBlock);

            bannerBlock.exists();
            offerBlock.exists();
            recommendBlock.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(2));
            magazineBlock.exists();
            reviewsBlock.exists();
            brandsBlock.exists();
            footer.shouldBe(Condition.visible);
            aside.shouldBe(Condition.visible);

            log.debug("Все блоки видны на странице Яндекс.");
        } catch (ElementNotFound ex) {
            log.error("Не все блоки присутствуют на странице Яндекс!");
        }
    }

    public Region setLocation() {
        log.trace("Изменяем город.");

        if ((locChangeBtn.isDisplayed())) {
            locChangeBtn.click();
        } else {
            location.click();
        }
        return new Region();
    }

    public void locationIsOk() {
        log.trace("Подтверждаем выбранный автоматически город.");
        locOKBtn.click();
    }

    public Category openCategory() {
        log.trace("Открываем категории Маркета");
        categoryBtn.click();
        return new Category();
    }

}
