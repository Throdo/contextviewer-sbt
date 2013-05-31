package com.orange.contextviewer.dao.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 15:19
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewsObject {

    @XmlElement(name = "map")
    private String map;

    @XmlElement(name = "reduce")
    private String reduce;

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getReduce() {
        return reduce;
    }

    public void setReduce(String reduce) {
        this.reduce = reduce;
    }
}
