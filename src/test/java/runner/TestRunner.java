package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import listeners.ExtentITestListener;

@io.cucumber.testng.CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty"},
        monochrome = true
)

@Listeners(ExtentITestListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {
}
