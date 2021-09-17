package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/smoke.feature",
        glue= {"stepdefinitions"},
        plugin = { "pretty", "html:target/cucumber-reports" },
        monochrome = true
)
public class TestsRunner extends AbstractTestNGCucumberTests {

}