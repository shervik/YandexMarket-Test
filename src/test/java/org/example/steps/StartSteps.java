package org.example.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.То;
import org.example.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class StartSteps {
    StartPage startPage;

    @Дано("страница Яндекс Маркета")
    public void страницаЯндексМаркета() {
        open("https://market.yandex.ru/");
        startPage = new StartPage();
    }

    @То("сменить {string}")
    public void сменить(String city) {
        startPage.setLocation().changeLocation(city);
        new StartPage();
    }

    @То("проверяем страницу Яндекс Маркета")
    public void проверяемСтраницуЯндексМаркета() {
        new StartPage();
    }

    @Затем("выбираем определение местоположения автоматически")
    public void выбираемОпределениеМестоположенияАвтоматически() {
        startPage.setLocation().autoLocation().locationIsOk();
    }

    @Если("выбираем категорию Продукты")
    public void выбираемКатегориюПродукты() {
        new StartPage().openCategory().selectItem();
    }
}
