package controllers;

import com.orange.contextviewer.ApplicationConfigurationHandler;
import com.orange.contextviewer.OrangeClusterManagerHandler;
import models.OrangeCluster;
import models.OrangeClusterManager;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.clusterView;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class ClusterController extends Controller {
    public static Result index(String id) {
        String version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();

        OrangeClusterManager orangeClusterManager = OrangeClusterManagerHandler.getinstance();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(id);

        return ok(clusterView.render(version, orangeCluster));
    }
}
