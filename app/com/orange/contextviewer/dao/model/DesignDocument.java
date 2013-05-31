package com.orange.contextviewer.dao.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 15:05
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignDocument {
    @XmlElement(name = "doc")
    private Document doc;

    @XmlElement(name = "controllers")
    private Controller controllers;

    public Document getDoc() {
        return doc;
    }

    public void setDoc(final Document doc) {
        this.doc = doc;
    }

    public Controller getControllers() {
        return controllers;
    }

    public void setControllers(final Controller controllers) {
        this.controllers = controllers;
    }


}
