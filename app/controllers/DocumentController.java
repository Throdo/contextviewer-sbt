package controllers;

import com.orange.contextviewer.ApplicationConfigurationHandler;
import com.orange.contextviewer.OrangeClusterManagerHandler;
import models.OrangeBucket;
import models.OrangeCluster;
import models.OrangeClusterManager;
import models.OrangeDocument;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.documentView;

import static play.data.Form.form;


/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:03
 */
public class DocumentController extends Controller {
    public static Result index(String idCluster, String idBucket, String idDocument) {
        String version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();

        OrangeClusterManager orangeClusterManager = OrangeClusterManagerHandler.getInstance();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(idCluster);
        OrangeBucket orangeBucket = orangeCluster.getBucketMap().get(idBucket);
        OrangeDocument orangeDocument = orangeBucket.getOrangeDocumentContent(idDocument);

        return ok(documentView.render(version, idCluster, idBucket, orangeDocument));
    }

    public static Result addDocument(String idCluster, String idBucket, String idDocument, String valueDocument) {
        String version = ApplicationConfigurationHandler.getinstance().getApplicationVersion();

        OrangeClusterManager orangeClusterManager = OrangeClusterManagerHandler.getInstance();
        OrangeCluster orangeCluster = orangeClusterManager.getClusterMap().get(idCluster);
        OrangeBucket orangeBucket = orangeCluster.getBucketMap().get(idBucket);
        OrangeDocument orangeDocument = orangeBucket.setOrangeDocumentContent(idDocument, valueDocument);

        Form<OrangeDocument> userForm = form(OrangeDocument.class);

        return ok(documentView.render(version, idCluster, idBucket, orangeDocument));
    }

}
