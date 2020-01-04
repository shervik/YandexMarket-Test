package org.example.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.StartPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Region {
    private Logger log = LogManager.getLogger(Region.class);

    private static SelenideElement wrapper = $x("//div[@class='modal__wrapper']");
    private static SelenideElement locationInput = $x(".//input[@placeholder='Укажите другой регион']");
    private static SelenideElement listEl = $x("//*[@class='region-suggest__list suggest2__content " +
            "suggest2__content_theme_normal']");
    private static SelenideElement location =
            $x("//*[contains(@class, 'header2-region')" + "]/span[@class='header2" + "-menu__text']");
    private static SelenideElement button = $x("//span[@class='button__text']/..");


    public Region() {
        wrapper.shouldBe(Condition.visible);
    }

    public void changeLocation(String s) {
        locationInput.val(s);
        listEl.click();
        button.click();

        log.trace("Город изменен на {}", s);
        Selenide.sleep(3000);

        Assert.assertEquals(s, location.getText(), "Город не изменился!");

        log.trace("Страница обновляется.");
    }

    public StartPage autoLocation() {
        log.trace("Автоматически определяем локацию.");
        $(By.linkText("Определять автоматически")).click();
        return new StartPage();
    }
}
