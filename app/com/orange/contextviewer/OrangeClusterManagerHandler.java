package com.orange.contextviewer;

import models.OrangeClusterManager;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 02/06/13
 * Time: 16:37
 */
@SuppressWarnings("unused")
public class OrangeClusterManagerHandler {

    private static OrangeClusterManager instance;

    /**
     * Méthode qui retourne une instance si elle existe ou bien la crée.
     *
     * @return
     */
    public static OrangeClusterManager getInstance() {
        if (null == instance) {
            // Premier appel
            instance = new OrangeClusterManager();
        }
        return instance;
    }

    /**
     * Constructeur privé pour éviter l'instanciation directe.
     */
    private OrangeClusterManagerHandler() {
    }

    /**
     * Libère l'instance du Singleton
     */
    public static void releaseInstance() {
        OrangeClusterManagerHandler.instance = null;
    }
}
