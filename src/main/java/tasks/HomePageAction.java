package tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.HomePage;
import stepdefinitions.HomePageSteps;
import utility.UIHelper;

import java.util.List;

public class HomePageAction {

	private static Logger logger = LoggerFactory.getLogger(HomePageAction.class);

	public static Performable launchApplication() {
		String user = "mojombo";
		String repo = "god";
		return Task.where("Opens the application", Open.browserOn().the(HomePage.class),
				Open.url(HomePageSteps.url+"/"+user+"/"+repo));
	}
	
	public static Performable verifyStars(Actor actor) {
		String title = BrowseTheWeb.as(actor).getDriver().getTitle();
		String repo = "god";
		String expectedStars = String.valueOf(getStarsInAPI(actor));
		return Task.where("Verify Stars in UI with API",
				Ensure.that(title).contains(repo),
				WaitUntil.the(HomePage.SPAN_STARS, WebElementStateMatchers.isVisible()),
				Ensure.that(UIHelper.getElementAttribute(actor, HomePage.SPAN_STARS, "title").replace(",","")).isEqualTo(expectedStars));
	}

	public static int getStarsInAPI(Actor actor) {
		String repo = "god";
		Integer stars = 0;
		List<String> lstRepositories = SerenityRest.lastResponse().jsonPath().getList("name");
		List<Integer> lstStars = SerenityRest.lastResponse().jsonPath().getList("watchers");
		for(int i=0;i<lstRepositories.size();i++) {
			String name = lstRepositories.get(i);
			if(name.equalsIgnoreCase(repo)) {
				stars = lstStars.get(i);
				logger.info("Stars = " + lstStars.get(i));
				break;
			}
		}
		return stars;
	}
}
