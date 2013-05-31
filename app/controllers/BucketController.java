package controllers;

import models.OrangeBucket;
import models.OrangeCluster;
import models.OrangeClusterManager;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.bucketView;


/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class BucketController extends Controller {
    public static Result index(String idCluster, String idBucket) {
        OrangeClusterManager orangeClusterManager = new OrangeClusterManager();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(idCluster);
        OrangeBucket orangeBucket = orangeCluster.getBucketMap().get(idBucket);

/*
        RestClientDAO restClientDAO = new RestClientDAO(orangeCluster.getUriList().get(0));
        restClientDAO.getDesignDocList(idBucket);
*/

        return ok(bucketView.render("ContextViewer v0.1", orangeBucket));
    }
}
