package org.example.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.FoodStuffPage;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;

public class PopCategory {
    private static Logger log = LogManager.getLogger(PopCategory.class);
    private static ElementsCollection topCat = $$x("//div[@data-zone-name=\"new-category-snippet\"]//a");
    private static ElementsCollection topCatJpg = $$x("//*[@data-zone-name='Banner']");


    public static SelenideElement getRandom() {
        Random random = new Random();
        SelenideElement cat;

        if (topCat.size() > 0) {
            int i = random.nextInt(topCat.size());
            cat = topCat.get(i);
            String textItem = cat.find(By.tagName("p")).getText();

            for (SelenideElement el : FoodStuffPage.getProductsItem()) {
                if (textItem.contains(el.getText())) {
                    return cat;
                }
            }
        } else {
            int i = random.nextInt(topCatJpg.size());
            cat = topCatJpg.get(i);
            return cat;
        }
        log.trace("Выбрана случайная популярная категория {}.", cat.getText());

        return cat;
    }
}
