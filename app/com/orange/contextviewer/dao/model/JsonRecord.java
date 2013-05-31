package com.orange.contextviewer.dao.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 15:14
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRecord {

    @XmlElement(name = "views")
    private Map<String, ViewsObject> viewsObjectMap;

    @XmlElement(name = "language")
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, ViewsObject> getViewsObjectMap() {
        return viewsObjectMap;
    }

    public void setViewsObjectMap(Map<String, ViewsObject> viewsObjectMap) {
        this.viewsObjectMap = viewsObjectMap;
    }
}
