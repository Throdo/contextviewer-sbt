package controllers;

import com.orange.contextviewer.ApplicationConfigurationHandler;
import com.orange.contextviewer.OrangeClusterManagerHandler;
import models.OrangeClusterManager;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class ContextViewer extends Controller {
    public static Result index(boolean refresh) {
        String version;
        OrangeClusterManager orangeClusterManager;

        if (refresh) {
            version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();
            orangeClusterManager = OrangeClusterManagerHandler.getInstance();
        } else {
            ApplicationConfigurationHandler.releaseInstance();
            OrangeClusterManagerHandler.releaseInstance();
            version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();
            orangeClusterManager = OrangeClusterManagerHandler.getInstance();
        }

        Logger.debug(orangeClusterManager.toString());
        return ok(index.render(version, orangeClusterManager.getClusterMap()));
    }
}
