package models;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import play.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 21/05/13
 * Time: 14:51
 */
public class OrangeBucket {

    //TODO A mettre dans un properties de configuration
    private final static String designDocName = "display";
    private final static String allKeysViewName = "all_keys";
    private final static String allKeyViewCode = "function (doc, meta) {\n" +
            "  emit(meta.id, null);\n" +
            "}";

    private final static String documentContentViewName = "document_content";
    private final static String documentContentViewCode = "function (doc, meta) {\n" +
            "  if (meta.id)\n" +
            "  {\n" +
            "     emit(meta.id, doc);\n" +
            "  }\n" +
            "}";

    // Propriété(s) de la Classe
    private String id;
    private String label;
    private CouchbaseClient couchbaseClient;
    private Map<String, OrangeDocument> orangeDocuments;
    private DesignDocument displayDesignDocument;
    private ViewDesign allKeyView;
    //TODO C'est dégueulasse de mettre le status à connected ou notConnected. Utiliser un booléen et laisser la partie view le transformer dans la bonne classe CSS.
    private String status;

    // Constructeur(s)
    public OrangeBucket(String id, String label, CouchbaseClient couchbaseClient) {
        this.id = id;
        this.label = label;
        this.couchbaseClient = couchbaseClient;
        // A changer cf. le "à faire" ci-dessus
        this.status = (setDisplayDesignDocument() ? "connected" : "notConnected");
        this.orangeDocuments = new HashMap<String, OrangeDocument>();
    }

    // Getter(s) & Setter(s)
    public DesignDocument getDisplayDesignDocument() {
        return displayDesignDocument;
    }

    public void setDisplayDesignDocument(DesignDocument displayDesignDocument) {
        this.displayDesignDocument = displayDesignDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean setDisplayDesignDocument() {
        DesignDocument designDocument = null;
        boolean excutionCode = false;

        try {
            designDocument = this.couchbaseClient.getDesignDocument(designDocName);
            Logger.debug("Contenu du Design Document : " + designDocument.toJson());
            this.displayDesignDocument = designDocument;
            excutionCode = true;
        } catch (InvalidViewException e) {
            Logger.info("Impossible de trouver le DesignDocument 'display'. Il faut le créer !");
            try {
                createDesignDocument(allKeyViewCode);
            } catch (Exception e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        if (excutionCode) {
            List<ViewDesign> views = (List<ViewDesign>) designDocument.getViews();
            ViewDesign viewDesign = new ViewDesign(allKeysViewName, allKeyViewCode);
            if (views.contains(viewDesign)) {
                int index = views.indexOf(viewDesign);
                this.allKeyView = views.get(index);
            }
        }
        return excutionCode;
    }

    private void createDocumentContentView() throws Exception {

        ViewDesign documentContentView = new ViewDesign(
                documentContentViewName,
                documentContentViewCode);

        this.displayDesignDocument.setView(documentContentView);

    }

    private void createDesignDocument(String jsonCodeView) throws Exception {
        DesignDocument designDocument;

        ViewDesign allKeys = new ViewDesign(
                allKeysViewName,
                jsonCodeView);

        designDocument = new DesignDocument(designDocName);
        designDocument.setView(allKeys);

        if (!this.couchbaseClient.createDesignDoc(designDocument)) {
            throw new Exception("Impossible de créer le DesignDocument " + designDocName + "!");
        }

        // TODO améliorer ce code pourri. Il faut un certain temps pour que le DesignDocument soit créer. Système de Synchronisation à mettre en place.
        boolean foundDesignDoc = false;
        int cpt = 0;
        while (!foundDesignDoc) {
            try {
                cpt++;
                this.couchbaseClient.getDesignDocument(designDocName);
                foundDesignDoc = true;
                Logger.debug("Création du DesignDocument " + designDocName + "!");
                this.createDocumentContentView();
            } catch (Exception e1) {
                Thread.sleep(500);
                if (cpt > 5) {
                    throw new Exception("Impossible de créer le DesignDocument " + designDocName + "!");
                }
            }
        }
    }

    public void fillBucketWithDocuments() {
        // Create connection if needed

        try {
            View view = this.couchbaseClient.getView(designDocName, allKeysViewName);
            Query query = new Query();
            query.setIncludeDocs(true).setLimit(20);
            query.setStale(Stale.FALSE);
            ViewResponse result = this.couchbaseClient.query(view, query);

            for (ViewRow row : result) {
                String key = row.getKey(); // deal with the document/data
                String value = row.getValue(); // deal with the document/data

                OrangeDocument document = new OrangeDocument(key, null);
                this.orangeDocuments.put(key, document);

            }
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Map<String, OrangeDocument> getOrangeDocuments() {
        return orangeDocuments;
    }

    public void setOrangeDocuments(Map<String, OrangeDocument> orangeDocuments) {
        this.orangeDocuments = orangeDocuments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CouchbaseClient getCouchbaseClient() {
        return couchbaseClient;
    }

    public void setCouchbaseClient(CouchbaseClient couchbaseClient) {
        this.couchbaseClient = couchbaseClient;
    }

    public void refreshBucketInformations() {
        this.status = (setDisplayDesignDocument() ? "connected" : "notConnected");
    }

    private OrangeDocument fillDocumentContent(String idDocument) {
        OrangeDocument documentFound = null;

        try {
            View view = this.couchbaseClient.getView(designDocName, documentContentViewName);
            Query query = new Query();
            query.setKey(idDocument);
            query.setIncludeDocs(true).setLimit(20);
            query.setStale(Stale.FALSE);
            ViewResponse result = this.couchbaseClient.query(view, query);

            for (ViewRow row : result) {
                String key = row.getKey(); // deal with the document/data
                String value = row.getValue(); // deal with the document/data

                DesignDocument document = (DesignDocument) row.getDocument();

                documentFound = new OrangeDocument(key, value);
                this.orangeDocuments.put(key, documentFound);

                Logger.debug("Document Key : " + key + " - Document Content : " + value + " trouvé !!!");
                Logger.debug("Document Content en Json : " + document.toJson());

            }
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return documentFound;
    }

    public OrangeDocument getOrangeDocumentContent(String idDocument) {
        OrangeDocument foundDocument;
        if (this.orangeDocuments.containsKey(idDocument)) {
            foundDocument = this.orangeDocuments.get(idDocument);
            if (foundDocument.getContent() == null) {
                foundDocument = this.fillDocumentContent(idDocument);
            }
        } else {
            foundDocument = this.fillDocumentContent(idDocument);
        }

        return foundDocument;  //To change body of created methods use File | Settings | File Templates.
    }
}
