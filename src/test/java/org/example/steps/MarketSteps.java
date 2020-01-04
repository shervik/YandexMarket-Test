package org.example.steps;

import cucumber.api.java.ru.*;
import org.example.elements.Filter;
import org.example.pages.MarketPage;

public class MarketSteps {
    MarketPage marketPage;
    Filter filter;
    boolean stepsB;

    @Дано("открыта страница с выбором товара")
    public void открытаСтраницаСВыборомТовара() {
        Filter.setViewtype();
        marketPage = new MarketPage();
        filter = new Filter();
    }

    @Допустим("добавить товар в избранное")
    public void добавитьТоварВИзбранное() {
        marketPage.addWishList();
    }

    @Также("активировать значок В продаже")
    public void активироватьЗначокВПродаже() {
        Filter.setCheckBox();
    }

    @И("установить цену От")
    public void установитьЦенуОт() {
        marketPage.setPrice().setPriceFrom();
    }

    @И("установить цену До")
    public void установитьЦенуДо() {
        marketPage.clearCheckBox().clearCheckBoxFrom();
        marketPage.setPrice().setPriceTo();
        marketPage.clearCheckBox().clearCheckBoxTo();
    }

    @И("выбрать рандомного производителя")
    public void выбратьРандомногоПроизводителя() {
        stepsB = marketPage.chooseProducer();
    }

    @Затем("выбрать рандомный товар")
    public void выбратьРандомныйТовар() {
        marketPage.selectFoodItem();
    }
}
