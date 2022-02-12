package utility;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class ActionsApi {
	
	public static void post(Actor actor, String resource, Object requestBody) {
		actor.attemptsTo(Post.to(resource).
				withRequest(request -> {
					request.body(requestBody);
					request.header("Content-Type", "application/json");
					return request;
				}));
	}
	
	public static void get(Actor actor, String resource) {
		actor.attemptsTo(Get.resource(resource));
	}
}
