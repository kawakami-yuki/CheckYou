package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	public static Result fail(Call call, String flashKey, String flashMessage) {
        ctx().flash().put(flashKey, flashMessage);
        return redirect(call);
    }
	}
