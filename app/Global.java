import com.orange.contextviewer.OrangeClusterManagerHandler;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import views.html.errorPage;
import views.html.pageNotfound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static play.mvc.Results.internalServerError;


/**
 * Created with IntelliJ IDEA.
 * Classe qui sert à gérer les paramètres globaux de l'application.
 * <p/>
 * User: throdo
 * Date: 02/06/13
 * Time: 15:01
 */
public class Global extends GlobalSettings {

    private String applicationVersion;
    private String clusterConfigurationFile;

    /**
     * Get la version de l'application
     *
     * @return
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Set la version de l'application en cours.
     *
     * @param applicationVersion
     */
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    /**
     * Get clusterConfigurationFile
     *
     * @return clusterConfigurationFile
     */
    public String getClusterConfigurationFile() {
        return clusterConfigurationFile;
    }

    /**
     * Set clusterConfigurationFile
     *
     * @param clusterConfigurationFile Chemin d'accès au fichier de configuration des clusters au format Json
     */
    public void setClusterConfigurationFile(String clusterConfigurationFile) {
        this.clusterConfigurationFile = clusterConfigurationFile;
    }

    /**
     * Méthode qui est lancée au démarrage (start-up) de l'application (premier appel)
     *
     * @param app
     */
    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");

        Logger.debug("Lecture du fichier properties 'configuration.properties' de configuration");

        //Reading properties file in Java example
        Properties props = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("public/configuration/configuration.properties");

            //loading properties from properties file
            try {
                props.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //reading property
            applicationVersion = props.getProperty("application.version");
            clusterConfigurationFile = props.getProperty("configuration.fichier");
            Logger.debug("Version de l'application : " + applicationVersion);
            Logger.debug("Chemin d 'accès du fichier de configuration des clusters Couchbase : " + clusterConfigurationFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.warn("Impossible de trouver le fichier de configuration de l'application. On est en mode par défaut.");
            this.applicationVersion = "N/A";
            this.clusterConfigurationFile = "public/configuration/contextClusterDescription.conf";
        }

        Logger.debug("Récupération des informations sur les clusters disponibles à partir du fichier de configuration 'contextClusterDescription.conf'");
        OrangeClusterManagerHandler.getinstance();

    }

    /**
     * Méthode qui est lancée à la fermeture (shutdown) de l'application.
     *
     * @param app
     */
    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
        OrangeClusterManagerHandler.releaseInstance();
    }

    /**
     * Méthode qui intercepte toute action et qui est exécutée avant - sorte de filtre pour une action.
     *
     * @param request      Http request
     * @param actionMethod
     * @return
     */
    @Override
    public Action onRequest(Http.Request request, Method actionMethod) {
        Logger.debug("before each request... Request : " + request.toString() + " Action : " + actionMethod.getName());
        return super.onRequest(request, actionMethod);
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

