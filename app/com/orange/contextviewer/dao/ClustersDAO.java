package com.orange.contextviewer.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.orange.contextviewer.ApplicationConfigurationHandler;
import play.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 20/05/13
 * Time: 10:40
 */
@SuppressWarnings("unused")
public class ClustersDAO {

    private List<ClusterDAO> clusterDAOList = new ArrayList<ClusterDAO>();

    public List<ClusterDAO> getClusterDAOList() {
        return clusterDAOList;
    }

    public void setClusterDAOList(List<ClusterDAO> clusterDAOList) {
        this.clusterDAOList = clusterDAOList;
    }

    public ClustersDAO() {
        Logger.debug("Entreé dans le constructeur : ClustersDAO()");
        try {
            Type collectionType = new TypeToken<List<ClusterDAO>>() {
            }.getType();
            Gson gson = new Gson();


            BufferedReader bufferedReader = new BufferedReader(new FileReader(ApplicationConfigurationHandler.getinstance().getClusterConfigurationFile()));
            this.clusterDAOList = gson.fromJson(bufferedReader, collectionType);
            Logger.debug(this.clusterDAOList.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.debug("Sortie du constructeur ClustersDAO() avec comme propriétés : " + this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClustersDAO)) return false;

        ClustersDAO that = (ClustersDAO) o;

        if (!clusterDAOList.equals(that.clusterDAOList)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clusterDAOList.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ClusterDAO elem : this.clusterDAOList) {
            sb.append(elem.toString());
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length()); // on supprime le dernier "," qui est en trop

        return "ClustersDAO{" +
                "clusterDAOList=" + sb +
                '}';
    }
}
