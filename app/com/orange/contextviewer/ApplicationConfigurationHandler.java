package com.orange.contextviewer;

import com.orange.contextviewer.model.ApplicationConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 06/06/13
 * Time: 22:13
 */
public class ApplicationConfigurationHandler {


    private static ApplicationConfiguration instance;

    /**
     * Méthode qui retourne une instance si elle existe ou bien la crée.
     *
     * @return instance
     */
    public static ApplicationConfiguration getinstance() {
        if (null == instance) {
            // Premier appel
            instance = new ApplicationConfiguration();
        }
        return instance;
    }

    /**
     * Constructeur privé pour éviter l'instanciation directe.
     */
    private ApplicationConfigurationHandler() {
    }

    /**
     * Libère l'instance du Singleton
     */
    public static void releaseInstance() {
        ApplicationConfigurationHandler.instance = null;
    }


}
