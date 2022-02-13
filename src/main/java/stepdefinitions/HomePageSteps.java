package stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;
import tasks.HomePageAction;
import utility.ActionsApi;
import utility.ConstantsApi;

public class HomePageSteps {
	
    private static Logger logger = LoggerFactory.getLogger(HomePageSteps.class);
    
	private EnvironmentVariables environmentVariables;
	public static String url;
	public static String gitUser;
	public static String gitRepo;
	@Managed
	WebDriver browser;
    
	@Before
	public void setTheStage() {
    	String webserviceBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("restapi.base.url");
		OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at(webserviceBaseUrl)));
		url = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		gitUser = System.getProperty("gituser");
		gitRepo = System.getProperty("gitrepo");
	}
	
    @After
	public void drawTheCurtain() {
		OnStage.drawTheCurtain();
	}

	@Then("{actor} ensure the Response is successful with responsecode as {int}")
	public void actorValidateResponseCode(Actor actor, Integer responseCode) {
		Integer actualResponseCode = SerenityRest.then().extract().response().getStatusCode();
		logger.info("Response Code : " + actualResponseCode);
		actor.attemptsTo(Ensure.that(actualResponseCode).isEqualTo(responseCode));
	}

	@When("{actor} get the Github User")
	public void actorGetsUser(Actor actor) {
//		String user = "mojombo";
		ActionsApi.get(actor, ConstantsApi.GET_USER_ENDPOINT+"/"+gitUser);
	}

	@When("{actor} get all repository of the Github User")
	public void actorGetsUserRepositories(Actor actor) {
//		String user = "mojombo";
		ActionsApi.get(actor, ConstantsApi.GET_USER_ENDPOINT+"/"+gitUser+"/repos");
	}
	
	@Then("{actor} validates the Username, Name, Created on for the Github User")
	public void actorValidateUserDetails(Actor actor) {
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("**********************OUTPUT********************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("Username = " +  SerenityRest.lastResponse().jsonPath().getString("login"));
		logger.info("Name = " +  SerenityRest.lastResponse().jsonPath().getString("name"));
		logger.info("Created on = " +  SerenityRest.lastResponse().jsonPath().getString("created_at"));
		logger.info("************************************************************");
		logger.info("***********************END**********************************");
		logger.info("************************************************************");
	}

	@Then("{actor} validate the Stars and Releases of all Repositories")
	public void actorValidateStars(Actor actor) {
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("**********************OUTPUT********************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		logger.info("************************************************************");
		List<String> lstRepositories = SerenityRest.lastResponse().jsonPath().getList("name");
		List<Integer> lstStars = SerenityRest.lastResponse().jsonPath().getList("watchers");
		for(int i=0;i<lstRepositories.size();i++) {
			String repo = lstRepositories.get(i);
			logger.info("Repository "+(i+1)+" = " + repo);
			logger.info("   Stars = " +  lstStars.get(i));
//			String user = "mojombo";
			ActionsApi.get(actor, ConstantsApi.GET_REPOS_ENDPOINT+"/"+gitUser+"/"+repo+"/releases");
			List<String> lstReleases = SerenityRest.lastResponse().jsonPath().get();
			logger.info("   Releases = " +  lstReleases.size());
		}
		logger.info("************************************************************");
		logger.info("***********************END**********************************");
		logger.info("************************************************************");
	}

	@When("{actor} opens the repository of the Github user")
	public void actorAccessTheApplication(Actor actor) {
		actor.wasAbleTo(HomePageAction.launchApplication());
		logger.info("Opened the Application");
	}
	
	@Then("{actor} verify the number of stars in UI with API")
	public void actorVerifyStars(Actor actor) {
		actorGetsUserRepositories(actor);
		actor.attemptsTo(HomePageAction.verifyStars(actor));
	}
}
