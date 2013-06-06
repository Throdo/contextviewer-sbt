package controllers;

import com.orange.contextviewer.ApplicationConfigurationHandler;
import com.orange.contextviewer.OrangeClusterManagerHandler;
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
        String version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();

        OrangeClusterManager orangeClusterManager = OrangeClusterManagerHandler.getinstance();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(idCluster);
        OrangeBucket orangeBucket = orangeCluster.getBucketMap().get(idBucket);

        orangeBucket.fillBucketWithDocuments();

        return ok(bucketView.render(version, idCluster, orangeBucket));
    }
}
