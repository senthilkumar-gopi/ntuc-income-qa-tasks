package acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/java/features",
		plugin = {"pretty",
				"html:target/HtmlReports/report.html"
		},
		snippets = CAMELCASE,
        glue = "stepdefinitions",
        monochrome = true,
        tags="@TC_02"
)
public class CucumberTestSuite {}

