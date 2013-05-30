package models;

import com.orange.contextviewer.dao.ClusterDAO;
import com.orange.contextviewer.dao.ClustersDAO;
import play.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * <p/>
 * Date: 19/05/13
 * Time: 10:22
 */
public class OrangeClusterManager {

    private Map<String, OrangeCluster> clusterMap = new HashMap<String, OrangeCluster>();

    public Map<String, OrangeCluster> getClusterMap() {
        return clusterMap;
    }

    public void setClusterMap(Map<String, OrangeCluster> clusterMap) {
        this.clusterMap = clusterMap;
    }

    public OrangeClusterManager() {
        Logger.debug("Entreé dans le constructeur : OrangeClusterManager()");
        ClustersDAO clustersDAO = new ClustersDAO();

        for (ClusterDAO c : clustersDAO.getClusterDAOList()) {
            OrangeCluster orangeCluster = new OrangeCluster(c.getId(), c.getLabel(), c.getUri(), c.getLoginAdm(), c.getPasswdAdm());
            this.clusterMap.put(orangeCluster.getId(), orangeCluster);
        }
        Logger.debug("Sortie du constructeur OrangeClusterManager() avec comme propriétés : " + this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrangeClusterManager)) return false;

        OrangeClusterManager that = (OrangeClusterManager) o;

        if (clusterMap != null ? !clusterMap.equals(that.clusterMap) : that.clusterMap != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clusterMap != null ? clusterMap.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrangeClusterManager{" +
                "clusterMap=" + clusterMap.toString() +
                '}';
    }

}
