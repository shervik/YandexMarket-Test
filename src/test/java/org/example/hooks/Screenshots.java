package org.example.hooks;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Screenshots {
    private static Logger log = LogManager.getLogger(Screenshots.class);

    @After
    public void doSomethingAfter(Scenario scenario) {
        try {
            TakesScreenshot ts = (TakesScreenshot) WebDriverRunner.getWebDriver();
            byte[] sourcePath = ts.getScreenshotAs(OutputType.BYTES);
            scenario.embed(sourcePath, "image/png");
        } catch (WebDriverException e) {
            log.fatal("Невозможно сделать скриншот.");
        }
    }
}
