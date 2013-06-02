package controllers;

import com.orange.contextviewer.OrangeClusterManagerHandler;
import models.OrangeBucket;
import models.OrangeCluster;
import models.OrangeClusterManager;
import models.OrangeDocument;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.documentView;


/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class DocumentController extends Controller {
    public static Result index(String idCluster, String idBucket, String idDocument) {
        OrangeClusterManager orangeClusterManager = OrangeClusterManagerHandler.getinstance();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(idCluster);
        OrangeBucket orangeBucket = orangeCluster.getBucketMap().get(idBucket);
        OrangeDocument orangeDocument = orangeBucket.getOrangeDocumentContent(idDocument);
/*
        RestClientDAO restClientDAO = new RestClientDAO(orangeCluster.getUriList().get(0));
        restClientDAO.getDesignDocList(idBucket);
*/

        return ok(documentView.render("ContextViewer v0.1", idCluster, idBucket, orangeDocument));
    }
}
