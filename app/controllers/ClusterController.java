package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class ClusterController extends Controller {
    public static Result index(String id) {
/*        OrangeClusterManager orangeClusterManager = new OrangeClusterManager();
        Logger.debug(orangeClusterManager.toString());
        return ok(index.render("ContextViewer v0.1",orangeClusterManager.getClusterMap()));*/
        return ok(views.html.clusterView.render("ContextViewer v0.2", id));
    }
}
