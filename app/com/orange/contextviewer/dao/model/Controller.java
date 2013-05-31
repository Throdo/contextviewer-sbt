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
public class Controller {

    @XmlElement(name = "compact")
    private String compact;

    @XmlElement(name = "setUpdateMinChanges")
    private String setUpdateMinChanges;

    public String getCompact() {
        return compact;
    }

    public void setCompact(String compact) {
        this.compact = compact;
    }

    public String getSetUpdateMinChanges() {
        return setUpdateMinChanges;
    }

    public void setSetUpdateMinChanges(String setUpdateMinChanges) {
        this.setUpdateMinChanges = setUpdateMinChanges;
    }
}
