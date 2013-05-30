package controllers;

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
    public static Result index() {
        OrangeClusterManager orangeClusterManager = new OrangeClusterManager();
        Logger.debug(orangeClusterManager.toString());
        return ok(index.render("ContextViewer v0.1",orangeClusterManager.getClusterMap()));
    }
}
