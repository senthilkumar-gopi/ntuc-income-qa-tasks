package pageobjects;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class HomePage extends PageObject {

	public static final Target SPAN_STARS = Target.the("Stars")
			.located(By.id("repo-stars-counter-star"));
}
