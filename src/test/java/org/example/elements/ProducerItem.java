package org.example.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;

public class ProducerItem {
    private static Logger log = LogManager.getLogger(ProducerItem.class);
    private static ElementsCollection producerList = $$x("//legend[contains(text(),'Производитель')]/following-sibling::ul//li/div/a");

    public static SelenideElement getRandom() {
        Random random = new Random();
        try {
            int i = random.nextInt(producerList.size());
            SelenideElement ranPr = producerList.get(i);
            log.trace("Выбран случайный производитель {}.", ranPr.getText());
            return ranPr;
        } catch (IllegalArgumentException ex) {
            log.warn("Нет в фильтре производителя!");
            return null;
        }
    }
}