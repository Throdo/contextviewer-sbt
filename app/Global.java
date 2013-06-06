import com.orange.contextviewer.OrangeClusterManagerHandler;
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

    /**
     * Méthode qui est lancé au démarrage de l'application (premier appel)
     *
     * @param app
     */
    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        OrangeClusterManagerHandler.getinstance();
    }

    /**
     * Méthode qui est lancé à la fermeture de l'application.
     *
     * @param app
     */
    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
        OrangeClusterManagerHandler.releaseInstance();
    }

    /**
     * Méthode appelé dans le cas d'une erreur lors du traitement. Code d'erreur HTTP 500.
     * Cette méthode intercepte toute les exceptions et évite d'afficher la stack Java.
     *
     * @param request
     * @param t
     * @return
     */
    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        return internalServerError(
                errorPage.render(t)
        );
    }

    /**
     * Méthode qui permet d'afficher une page d'erreur spéciale lors d'une saisie d'une action qui n'existe pas dans les routes.
     *
     * @param request
     * @param error
     * @return
     */
    @Override
    public Result onBadRequest(Http.RequestHeader request, String error) {
        return Results.badRequest("Don't try to hack the URI!");
    }

    /**
     * Méthode qui permet d'afficher une page spéciale lors d'une erreur HTTP 404.
     *
     * @param request
     * @return
     */
    @Override
    public Result onHandlerNotFound(Http.RequestHeader request) {
        return Results.notFound(
                pageNotfound.render(request.uri())
        );
    }

}

