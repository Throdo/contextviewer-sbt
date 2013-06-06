package com.orange.contextviewer.model;

import play.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 06/06/13
 * Time: 22:17
 */
public class ApplicationConfiguration {
    private String applicationVersion;
    private String clusterConfigurationFile;

    public ApplicationConfiguration() {

        this.applicationVersion = "N/A";
        this.clusterConfigurationFile = "public/configuration/contextClusterDescription.conf";

        //Reading properties file in Java example
        Properties props = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("public/configuration/configuration.properties");

            //loading properties from properties file
            try {
                props.load(fis);
                //reading property
                applicationVersion = props.getProperty("application.version");
                clusterConfigurationFile = props.getProperty("configuration.fichier");
                Logger.debug("Version de l'application : " + applicationVersion);
                Logger.debug("Chemin d 'accès du fichier de configuration des clusters Couchbase : " + clusterConfigurationFile);

            } catch (IOException e) {
                e.printStackTrace();
                Logger.warn("Impossible de lire les properties dans le fichier de configuration de l'application. On est en mode par défaut.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.warn("Impossible de trouver le fichier de configuration de l'application. On est en mode par défaut.");
        }
    }

    /**
     * Get la version de l'application
     *
     * @return
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Set la version de l'application en cours.
     *
     * @param applicationVersion
     */
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    /**
     * Get clusterConfigurationFile
     *
     * @return clusterConfigurationFile
     */
    public String getClusterConfigurationFile() {
        return clusterConfigurationFile;
    }

    /**
     * Set clusterConfigurationFile
     *
     * @param clusterConfigurationFile Chemin d'accès au fichier de configuration des clusters au format Json
     */
    public void setClusterConfigurationFile(String clusterConfigurationFile) {
        this.clusterConfigurationFile = clusterConfigurationFile;
    }

}
