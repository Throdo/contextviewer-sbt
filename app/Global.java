import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import views.html.errorPage;
import views.html.pageNotfound;

import static play.mvc.Results.internalServerError;


/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 02/06/13
 * Time: 15:01
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        return internalServerError(
                errorPage.render(t)
        );
    }

    @Override
    public Result onBadRequest(Http.RequestHeader request, String error) {
        return Results.badRequest("Don't try to hack the URI!");
    }

    @Override
    public Result onHandlerNotFound(Http.RequestHeader request) {
        return Results.notFound(
                pageNotfound.render(request.uri())
        );
    }

}

