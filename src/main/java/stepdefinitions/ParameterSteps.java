package stepdefinitions;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class ParameterSteps {
	
	@ParameterType(".*")
	public Actor actor(String actor) {
		return OnStage.theActorCalled(actor);
	}
}
