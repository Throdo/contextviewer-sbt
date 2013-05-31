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
public class Document {
    @XmlElement(name = "meta")
    private Meta doc;

    @XmlElement(name = "json")
    private JsonRecord controllers;

    public JsonRecord getControllers() {
        return controllers;
    }

    public void setControllers(JsonRecord controllers) {
        this.controllers = controllers;
    }

    public Meta getDoc() {
        return doc;
    }

    public void setDoc(Meta doc) {
        this.doc = doc;
    }
}
