package org.example.steps;

import com.codeborne.selenide.Selenide;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.Тогда;
import org.example.elements.PopCategory;
import org.example.elements.SubCategoryBasic;
import org.example.elements.SubCategoryBuy;
import org.example.pages.FoodStuffPage;

public class FoodStuffSteps {
    @Дано("открыта страница Продукты")
    public void открытаСтраницаПродукты() {
        Selenide.open("https://market.yandex.ru/catalog--produkty-napitki/54434");
    }

    @Допустим("выбирается рандомная подкатегорияП")
    public void выбираетсяРандомнаяПодкатегорияП() {
        SubCategoryBasic.getRandom().scrollTo().click();
    }

    @Тогда("открывается страница подкатегории")
    public void открываетсяСтраницаПодкатегории() {
        new FoodStuffPage();
    }

    @Затем("выбрать рандомную популярную категорию")
    public void выбратьРандомнуюПопулярнуюКатегорию() {
        PopCategory.getRandom().click();
    }

    @Допустим("выбирается рандомная подкатегорияТ")
    public void выбираетсяРандомнаяПодкатегорияТ() {
        SubCategoryBuy.getRandom().scrollTo().click();
    }
}
