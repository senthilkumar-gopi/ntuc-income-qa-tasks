package utility;

import net.serenitybdd.screenplay.targets.Target;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.CSSValue;;import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UIHelper {
	
	public static String getElementAttribute(Actor actor, Target target, String attribute) {
		return target.resolveFor(theActorInTheSpotlight()).getAttribute(attribute);
	}
}
