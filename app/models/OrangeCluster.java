package models;

import com.couchbase.client.ClusterManager;
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.CouchbaseConnectionFactory;
import play.Logger;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 10:33
 */
public class OrangeCluster {
    private String id;
    private String label;
    private String loginAdm;
    private String passwdAdm;
    private String status;
    private List<URI> uriList = new ArrayList<URI>();
    private ClusterManager clusterManager;
    private Map<String, OrangeBucket> bucketMap = new HashMap<String, OrangeBucket>();

    public Map<String, OrangeBucket> getBucketMap() {
        return bucketMap;
    }

    public void setBucketMap(Map<String, OrangeBucket> bucketMap) {
        this.bucketMap = bucketMap;
    }

    public List<URI> getUriList() {
        return uriList;
    }

    public void setUriList(List<URI> uriList) {
        this.uriList = uriList;
    }

    public String getStatus() {
        return status;
    }

    public ClusterManager getClusterManager() {
        return clusterManager;
    }

    public void setClusterManager() {
        Logger.debug("Entreé dans la méthode : setClusterManager()");
        this.clusterManager = new ClusterManager(this.uriList, this.getLoginAdm(), this.getPasswdAdm());
        try {
            this.clusterManager.listBuckets();
        } catch (RuntimeException re) {
            /* On catche une exception Runtime si le serveur défini n'est pas joignable
             * Http Error: 404 Reason: Object Not Found Details: No reason given
             */
            Logger.error(re.getClass() + " : " + re.getMessage());
            this.clusterManager = null;
        }
        this.setStatus();
        Logger.debug("Sortie de la méthode : setClusterManager() avec status = " + this.status);
    }

    public void setStatus() {
        if (this.clusterManager != null) {
            this.status = "connected";
        } else {
            this.status = "notConnected";
        }

    }

    public CouchbaseClient getCouchbaseClient(String bucketName) {
        Logger.debug("Entreé dans la méthode : getCouchbaseClient(bucketName " + bucketName + ")");

        CouchbaseClient couchbaseClientHandle = null;

        try {
            CouchbaseConnectionFactory cf = new
                    CouchbaseConnectionFactory(this.uriList, bucketName, "");
            couchbaseClientHandle = new CouchbaseClient(cf);
        } catch (IOException ex) {
            Logger.info(ex.getMessage());
        }
        Logger.debug("Sortie de la méthode : getCouchbaseClient() avec status = " + this.status);

        return couchbaseClientHandle;
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

    public String getLoginAdm() {
        return loginAdm;
    }

    public void setLoginAdm(String loginAdm) {
        this.loginAdm = loginAdm;
    }

    public String getPasswdAdm() {
        return passwdAdm;
    }

    public void setPasswdAdm(String passwdAdm) {
        this.passwdAdm = passwdAdm;
    }

    public OrangeCluster(String id, String label, String uri, String loginAdm, String passwdAdm) {
        Logger.debug("Entreé dans le constructeur : OrangeCluster(id : '" + id + "', label : '" + label + "', uri : '" + uri + "', loginAdm : '" + loginAdm + "', passwdAdm : '" + passwdAdm + "')");
        this.id = id;
        this.label = label;
        this.uriList.add(URI.create(uri));
        this.loginAdm = loginAdm;
        this.passwdAdm = passwdAdm;
        this.setClusterManager();
        if (this.clusterManager != null) {
            List<String> list = this.clusterManager.listBuckets();
            for (String s : list) {

                this.bucketMap.put(s, new OrangeBucket(s, s, getCouchbaseClient(s)));
            }
        }
        Logger.debug("Sortie du constructeur : OrangeCluster(String id, String label, String uri, String loginAdm, String passwdAdm) avec comme propriétés : " + this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrangeCluster)) return false;

        OrangeCluster that = (OrangeCluster) o;

        if (!clusterManager.equals(that.clusterManager)) return false;
        if (!id.equals(that.id)) return false;
        if (!label.equals(that.label)) return false;
        if (!loginAdm.equals(that.loginAdm)) return false;
        if (!passwdAdm.equals(that.passwdAdm)) return false;
        if (!status.equals(that.status)) return false;
        if (!uriList.equals(that.uriList)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + loginAdm.hashCode();
        result = 31 * result + passwdAdm.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + uriList.hashCode();
        result = 31 * result + clusterManager.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrangeCluster{" +
                "bucketMap=" + bucketMap +
                ", id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", loginAdm='" + loginAdm + '\'' +
                ", passwdAdm='" + passwdAdm + '\'' +
                ", status='" + status + '\'' +
                ", uriList=" + uriList +
                ", clusterManager=" + clusterManager +
                '}';
    }
}
