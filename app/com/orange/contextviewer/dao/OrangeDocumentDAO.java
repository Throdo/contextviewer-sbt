package com.orange.contextviewer.dao;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 04/06/13
 * Time: 22:12
 */
public class OrangeDocumentDAO {

    public OrangeDocumentDAO() {
    }

    public String getContent(String idDocument) {

 /*       try {
            View view = this.couchbaseClient.getView(designDocName, documentContentViewName);
            Query query = new Query();
            query.setKey(idDocument);
            query.setIncludeDocs(true).setLimit(20);
            query.setStale(Stale.FALSE);
            ViewResponse result = this.couchbaseClient.query(view, query);

            for (ViewRow row : result) {
                String key = row.getKey(); // deal with the document/data
                String value = row.getValue(); // deal with the document/data
                row.getDocument();
                documentFound = new OrangeDocument(key, value);
                this.orangeDocuments.put(key, documentFound);
                Logger.debug("Document Key : " + key + " - Document Content : " + value + " trouvé !!!");

            }
        } catch (InvalidViewException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/

        return null;
    }
}
