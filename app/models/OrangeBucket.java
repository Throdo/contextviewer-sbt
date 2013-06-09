package models;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import play.Logger;

import java.util.HashMap;
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
    private View allKeyView;
    private View documentContentView;
    private String status;

    // Constructeur(s)
    //TODO C'est dégueulasse de mettre le status à connected ou notConnected. Utiliser un booléen et laisser la partie view le transformer dans la bonne classe CSS.
    public OrangeBucket(String id, String label, CouchbaseClient couchbaseClient) {
        this.id = id;
        this.label = label;
        this.couchbaseClient = couchbaseClient;

        getBucketInformation();

        this.orangeDocuments = new HashMap<String, OrangeDocument>();
    }

    public View getDocumentContentView() {
        return documentContentView;
    }

    public void setDocumentContentView(View documentContentView) {
        this.documentContentView = documentContentView;
    }

    public View getAllKeyView() {
        return allKeyView;
    }

    public void setAllKeyView(View allKeyView) {
        this.allKeyView = allKeyView;
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

    public static String getDesignDocName() {
        return designDocName;
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

    /**
     * Retourne true si le DesignDocument existe dans le cluster, false sinon.
     *
     * @return boolean
     */
    private boolean isDisplayDesignDocument(String designDocName) {
        boolean returnValue = false;

        try {
            this.couchbaseClient.getDesignDocument(designDocName);
            returnValue = true;
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return returnValue;
    }

    /**
     * Méthode qui remplie les propriétés de l'objet Bucket par rapport à l'existant dans le cluster
     * Si les propriétés n'existent pas alors il les crée.
     * <p/>
     * Test le DesignDocument et le crée si il n'existe pas.
     * Puis s'occupe des vues (All_Keys et Document_Content)
     */
    private void getBucketInformation() {
        if (getDisplayDesignDocument() == null) {
            this.displayDesignDocument = createDisplayDesignDocument(designDocName);
        }

        if (this.displayDesignDocument != null) {
            if (getAllKeyView() == null) {
                this.allKeyView = createAllKeysView();
            }

            if (getDocumentContentView() == null) {
                this.documentContentView = createDocumentContentView();
            }

            Logger.debug("Contenu du Design Document : " + this.displayDesignDocument.toJson());

            this.status = "connected";
        } else {
            this.status = "notConnected";
        }
    }


    /**
     * Retourne true si la vue en paramètre existe dans le cluster, false sinon.
     *
     * @return boolean
     */
    private boolean isViewExists(String viewName) {
        boolean returnValue = false;

        try {
            this.couchbaseClient.getView(designDocName, viewName);
            returnValue = true;
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return returnValue;
    }

    /**
     * Retourne true si la vue Document_Content existe dans le cluster, false sinon.
     *
     * @return boolean
     */
    private boolean isDocumentContextView() {
        return (isViewExists(documentContentViewName));
    }

    /**
     * Retourne true si la vue All_Key existe dans le cluster, false sinon.
     *
     * @return boolean
     */
    private boolean isAllKeyView() {
        return (isViewExists(allKeysViewName));
    }

    /**
     * Crée le DesignDocument si il n'existe pas. Retourne le DesignDocument si il existe
     *
     * @return DesignDocument si la création est ok, null sinon.
     */
    private DesignDocument createDisplayDesignDocument(String designDocName) {

        DesignDocument designDocument;

        Logger.debug("Entrée dans la méthode createDisplayDesignDocument().");

        if (isDisplayDesignDocument(designDocName)) {
            designDocument = this.couchbaseClient.getDesignDocument(designDocName);
        } else {
            designDocument = new DesignDocument(designDocName);

            ViewDesign viewDesign = new ViewDesign(allKeysViewName, allKeyViewCode);
            designDocument.setView(viewDesign);

            if (this.couchbaseClient.createDesignDoc(designDocument)) {
                designDocument = this.couchbaseClient.getDesignDocument(designDocName);
            } else {
                Logger.warn("Impossible de créer le DesignDocument " + designDocName);
                designDocument = null;
            }
        }

/*      TODO améliorer ce code pourri. Il faut un certain temps pour que le DesignDocument soit créer. Système de Synchronisation à mettre en place.
        boolean foundDesignDoc = false;
        int cpt = 0;
        while (!foundDesignDoc) {
            try {
                cpt++;
                this.displayDesignDocument = this.couchbaseClient.getDesignDocument(designDocName);
                foundDesignDoc = true;
                Logger.debug("Création du DesignDocument " + designDocName + "!");
                this.createDocumentContentView();
            } catch (Exception e1) {
                Thread.sleep(500);
                if (cpt > 5) {
                    throw new Exception("Impossible de créer le DesignDocument " + designDocName + "!");
                }
            }
        }*/

        Logger.debug("Sortie de la méthode createDisplayDesignDocument().");

        return designDocument;
    }

    private View createView(String viewName, String viewCode) {
        View view = null;

        if (isViewExists(viewName)) {
            view = this.couchbaseClient.getView(designDocName, viewName);
        } else {
            DesignDocument designDocument = new DesignDocument(designDocName);

            ViewDesign viewDesign = new ViewDesign(viewName, viewCode);
            designDocument.setView(viewDesign);

            //TODO améliorer ce code pourri. Il faut un certain temps pour que le DesignDocument soit créer. Système de Synchronisation à mettre en place.
            boolean foundDesignDoc = false;
            int cpt = 0;
            while (!foundDesignDoc) {
                try {
                    cpt++;
                    view = this.couchbaseClient.getView(designDocName, viewName);
                    foundDesignDoc = true;
                    Logger.debug("Création de la vue " + viewName + "!");
                } catch (Exception e1) {
                    try {
                        Thread.sleep(500);
                        if (cpt > 5) {
                            Logger.error("Impossible de créer le DesignDocument " + designDocName + "!");
                            foundDesignDoc = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        }

        return view;
    }

    private View createAllKeysView() {
        return (createView(allKeysViewName, allKeyViewCode));
    }

    private View createDocumentContentView() {
        return (createView(documentContentViewName, documentContentViewCode));

    }

    public void refreshBucketInformations() {
        getBucketInformation();
    }

    /**
     * Méthode qui récupère toutes les clés des documents stockés dans le Bucket en utilisant la vue All_Keys qui doit être créee.
     */
    public void getAllDocumentKeys() {
        try {
            Query query = new Query();
            query.setIncludeDocs(true).setLimit(20);
            query.setStale(Stale.FALSE);
            ViewResponse result = this.couchbaseClient.query(this.allKeyView, query);

            for (ViewRow row : result) {
                String key = row.getKey(); // deal with the document/data

                OrangeDocument document = new OrangeDocument(key, null);
                this.orangeDocuments.put(key, document);
            }
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private OrangeDocument getDocumentContentFromId(String idDocument) {
        OrangeDocument documentFound = null;

        try {
            Query query = new Query();
            query.setKey(idDocument);
            query.setIncludeDocs(true).setLimit(20);
            query.setStale(Stale.FALSE);
            ViewResponse result = this.couchbaseClient.query(this.allKeyView, query);

            for (ViewRow row : result) {
                String key = row.getKey(); // deal with the document/data
                String value = row.getValue(); // deal with the document/data

                documentFound = new OrangeDocument(key, value);
                this.orangeDocuments.put(key, documentFound);

                Logger.debug("Document Key : " + key + " - Document Content : " + value + " trouvé !!!");
            }
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return documentFound;
    }

    public OrangeDocument setOrangeDocumentContent(String idDocument, String valueDocument) {

        OrangeDocument orangeDocument = new OrangeDocument(idDocument, valueDocument);
        this.getCouchbaseClient().set(idDocument, valueDocument);

        return orangeDocument;  //To change body of created methods use File | Settings | File Templates.
    }

    public OrangeDocument getOrangeDocumentContent(String idDocument) {

        OrangeDocument foundDocument = new OrangeDocument(idDocument, (String) this.getCouchbaseClient().get(idDocument));

        return foundDocument;  //To change body of created methods use File | Settings | File Templates.
    }
}
