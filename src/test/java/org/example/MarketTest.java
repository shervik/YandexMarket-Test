package org.example;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        glue = {"org.example.steps", "org.example.hooks"},
        features = "src/test/resources/feature"
//        tags = {"@no_popcategory"}
)

public class MarketTest extends AbstractTestNGCucumberTests {
}
