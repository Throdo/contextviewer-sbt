package com.orange.contextviewer.dao;

import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 21/05/13
 * Time: 15:00
 */
public class ClusterDAO {
    private String id;
    private String label;
    private String uri;
    private String loginAdm;
    private String passwdAdm;

    public ClusterDAO(String id, String label, String loginAdm, String passwdAdm, String uri) {
        Logger.debug("Entreé dans le constructeur : ClusterDAO(String id, String label, String loginAdm, String passwdAdm, String uri)");
        this.id = id;
        this.label = label;
        this.loginAdm = loginAdm;
        this.passwdAdm = passwdAdm;
        this.uri = uri;
        Logger.debug("Sortie du constructeur ClusterDAO(String id, String label, String loginAdm, String passwdAdm, String uri) avec comme propriétés : " + this.toString());
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClusterDAO)) return false;

        ClusterDAO that = (ClusterDAO) o;

        if (!id.equals(that.id)) return false;
        if (!label.equals(that.label)) return false;
        if (!loginAdm.equals(that.loginAdm)) return false;
        if (!passwdAdm.equals(that.passwdAdm)) return false;
        if (!uri.equals(that.uri)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + uri.hashCode();
        result = 31 * result + loginAdm.hashCode();
        result = 31 * result + passwdAdm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClusterDAO{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", uri='" + uri + '\'' +
                ", loginAdm='" + loginAdm + '\'' +
                ", passwdAdm='" + passwdAdm + '\'' +
                '}';
    }
}
