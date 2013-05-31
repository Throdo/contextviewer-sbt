package com.orange.contextviewer.dao.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 15:13
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "rev")
    private String rev;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
}
