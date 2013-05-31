package com.orange.contextviewer.dao;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import play.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 31/05/13
 * Time: 08:15
 */

public class RestClientDAO {

    private static final int consolePort = 8091;
    private static final int restPort = 8092;

    private String urlRestConsole; //8091
    private String urlRestAPI; //8092

    public RestClientDAO(URI uri) {
        this.urlRestConsole = this.addPortToURI(uri, consolePort);
        this.urlRestAPI = this.addPortToURI(uri, restPort);
    }

    public Map<String, String> getDesignDocList(String bucket) {
        // URL pour l'appel REST :
        // http://127.0.0.1:8091/pools/default/buckets/bucketName/ddocs
        // ex : http://127.0.0.1:8091/pools/default/buckets/beer-sample/ddocs
        String placeHolder = this.urlRestConsole + "default/buckets/%s/ddocs";
        String serviceRestToCall = String.format(placeHolder, bucket);
        Logger.debug("Url du Service Rest pour obtenir la liste des designdoc : " + serviceRestToCall);
        Logger.debug(callRestService(serviceRestToCall));

        return null;
    }

    private String callRestService(String url) {

        String output = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;

        try {
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");

            response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            while ((output = br.readLine()) != null) {
                Logger.debug(output);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return output;
    }

    private String addPortToURI(URI uri, int port) {
        URI uriModified = null;
        String protocol = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();

        try {
            uriModified = new URI(protocol, null, host, port, path, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return uriModified.toString();
    }

}
