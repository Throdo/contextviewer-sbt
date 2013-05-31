package com.orange.contextviewer.dao.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 15:05
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignDocumentResponse {
    @XmlElement(name = "rows")
    private List<DesignDocument> items;

    public List<DesignDocument> getItems() {
        return items;
    }

    public void setItems(final List<DesignDocument> items) {
        this.items = items;
    }

}
