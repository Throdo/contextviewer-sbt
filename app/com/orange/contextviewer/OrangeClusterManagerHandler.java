package com.orange.contextviewer;

import models.OrangeClusterManager;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 02/06/13
 * Time: 16:37
 */
public class OrangeClusterManagerHandler {

    private static OrangeClusterManager instance;

    public static OrangeClusterManager getinstance() {
        if (null == instance) { // Premier appel
            instance = new OrangeClusterManager();
        }
        return instance;
    }

    private OrangeClusterManagerHandler() {
    }

    public static void releaseInstance() {
        OrangeClusterManagerHandler.instance = null;
    }
}
